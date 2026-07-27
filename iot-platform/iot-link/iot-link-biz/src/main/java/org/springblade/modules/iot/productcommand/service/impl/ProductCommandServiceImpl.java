package org.springblade.modules.iot.productcommand.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReUtil;
import java.util.Optional;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.product.constant.ThingModelCodeRule;
import org.springblade.modules.iot.product.event.publisher.ProductEventPublisher;
import org.springblade.modules.iot.product.event.source.ProductModelChangedSource;
import org.springblade.modules.iot.product.service.ProductQueryService;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;
import org.springblade.modules.iot.productcommand.entity.ProductCommand;
import org.springblade.modules.iot.productcommand.manager.ProductCommandManager;
import org.springblade.modules.iot.productcommand.service.ProductCommandService;
import org.springblade.modules.iot.productcommand.vo.result.ProductCommandResultVO;
import org.springblade.modules.iot.productcommand.vo.save.ProductCommandSaveVO;
import org.springblade.modules.iot.productcommand.vo.update.ProductCommandUpdateVO;
import org.springblade.modules.iot.productservice.service.ProductServiceService;
import org.springblade.modules.iot.productversionchangelog.enumeration.ProductChangeTargetTypeEnum;
import org.springblade.modules.iot.productversionchangelog.enumeration.ProductVersionChangeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 产品模型设备服务命令表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductCommandServiceImpl extends BaseServiceImpl<ProductCommandMapper, ProductCommand> implements ProductCommandService {

    private final ProductServiceService productServiceService;
    /**
     * 注入只读 {@link ProductQueryService}(独立 bean,零下游 Service 依赖),
     * 切库经过 Service AOP 边界,且类图天然为 DAG,从根本规避反向依赖循环。
     */
    private final ProductQueryService productQueryService;
    private final ProductEventPublisher productEventPublisher;

    /**
     * 保存产品模型设备服务命令
     *
     * @param saveVO
     * @return
     */
    @Override
    public ProductCommand saveProductCommand(ProductCommandSaveVO saveVO) {
        log.info("saveProductCommand saveVO:{}", saveVO);
        //校验参数
        checkedProductCommandSaveVO(saveVO);
        //构建参数
        ProductCommand productCommand = builderProductCommandSaveVO(saveVO);
        //更新
        superManager.save(productCommand);
        publishChange(ProductVersionChangeTypeEnum.CREATE, null, productCommand, "新增命令「" + productCommand.getCommandName() + "」");
        return productCommand;
    }

    /**
     * 修改产品模型设备服务命令
     *
     * @param updateVO
     * @return
     */
    @Override
    public ProductCommand updateProductCommand(ProductCommandUpdateVO updateVO) {
        log.info("updateProductCommand updateVO:{}", updateVO);
        //校验参数
        checkedProductCommandUpdateVO(updateVO);
        ProductCommand before = superManager.getById(updateVO.getId());
        //构建参数
        ProductCommand productCommand = BeanUtil.toBeanIgnoreError(updateVO, ProductCommand.class);
        //更新
        superManager.updateById(productCommand);
        ProductCommand after = superManager.getById(updateVO.getId());
        publishChange(ProductVersionChangeTypeEnum.UPDATE, before, after, "编辑命令「" + (after != null ? after.getCommandName() : updateVO.getCommandName()) + "」");
        return productCommand;
    }

    @Override
    public Boolean deleteProductCommand(Long id) {
        ArgumentAssert.notNull(id, "id Cannot be null");
        ProductCommand productCommand = superManager.getById(id);
        if (null == productCommand) {
            throw BizException.wrap("The productCommand does not exist");
        }
        boolean result = superManager.removeById(id);
        publishChange(ProductVersionChangeTypeEnum.DELETE, productCommand, null, "删除命令「" + productCommand.getCommandName() + "」");
        return result;
    }

    @Override
    public List<ProductCommand> findAllByServiceIds(List<Long> serviceIds) {
        return superManager.findAllByServiceIds(serviceIds);
    }

    /**
     * 新增 校验参数
     *
     * @param saveVO
     */
    private void checkedProductCommandSaveVO(ProductCommandSaveVO saveVO) {
        ArgumentAssert.notNull(saveVO.getServiceId(), "serviceId Cannot be null");
        //校验产品模型服务是否存在
        ArgumentAssert.notNull(productServiceService.findOneByProductServiceId(saveVO.getServiceId()), "productService not found");
        ArgumentAssert.notBlank(saveVO.getCommandCode(), "commandCode Cannot be null");
        //校验编码命名规范
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, saveVO.getCommandCode())) {
            throw BizException.wrap(ThingModelCodeRule.PATTERN_MSG);
        }
        //校验CODE
        if (CollUtil.isNotEmpty(superManager.checkCode(saveVO.getServiceId(), saveVO.getCommandCode()))) {
            throw BizException.wrap("commandCode already exists");
        }
        ArgumentAssert.notBlank(saveVO.getCommandName(), "commandName Cannot be null");
    }

    /**
     * 新增 构建参数
     *
     * @param saveVO
     * @return
     */
    private ProductCommand builderProductCommandSaveVO(ProductCommandSaveVO saveVO) {
        saveVO.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        return BeanUtil.toBeanIgnoreError(saveVO, ProductCommand.class);
    }

    private void publishChange(ProductVersionChangeTypeEnum changeType, ProductCommand before, ProductCommand after, String summary) {
        ProductCommand ref = after != null ? after : before;
        if (ref == null) {
            return;
        }
        Optional.ofNullable(productServiceService.findOneByProductServiceId(ref.getServiceId()))
                .map(ps -> productQueryService.findOneByProductId(ps.getProductId()))
                .map(ProductResultVO::getProductIdentification)
                .ifPresent(pid -> productEventPublisher.publishProductModelChangedEvent(
                        ProductModelChangedSource.builder()
                                .productIdentification(pid)
                                .changeType(changeType)
                                .targetType(ProductChangeTargetTypeEnum.COMMAND)
                                .before(before == null ? null : BeanUtil.toBeanIgnoreError(before, ProductCommandResultVO.class))
                                .after(after == null ? null : BeanUtil.toBeanIgnoreError(after, ProductCommandResultVO.class))
                                .changeSummary(summary)
                                .build()));
    }

    /**
     * 修改 校验参数
     *
     * @param updateVO
     */
    private void checkedProductCommandUpdateVO(ProductCommandUpdateVO updateVO) {
        ArgumentAssert.notNull(updateVO.getId(), "id Cannot be null");
        ArgumentAssert.notNull(updateVO.getServiceId(), "serviceId Cannot be null");
        //校验产品模型是否存在
        ArgumentAssert.notNull(productServiceService.findOneByProductServiceId(updateVO.getServiceId()), "productService not found");
        ArgumentAssert.notBlank(updateVO.getCommandCode(), "commandCode Cannot be null");
        //校验编码命名规范
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, updateVO.getCommandCode())) {
            throw BizException.wrap(ThingModelCodeRule.PATTERN_MSG);
        }
        ArgumentAssert.notBlank(updateVO.getCommandName(), "commandName Cannot be null");

        //校验CODE
        List<ProductCommand> productCommands = superManager.checkCode(updateVO.getServiceId(), updateVO.getCommandCode());
        productCommands.stream()
                .filter(productCommand -> !productCommand.getId().equals(updateVO.getId()))
                .findAny()
                .ifPresent(productProperty -> {
                    throw BizException.wrap("commandCode already exists");
                });
    }

}

