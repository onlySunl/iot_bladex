package org.springblade.modules.iot.productcommand.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotproductcommandserviceimplProductCommandServiceImpl.java.mapper.ProductCommandMapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReUtil;
import java.util.Optional;
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
 * 涓氬姟瀹炵幇绫?
 * 浜у搧妯″瀷璁惧鏈嶅姟鍛戒护琛?
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
     * 娉ㄥ叆鍙 {@link ProductQueryService}(鐙珛 bean,闆朵笅娓?Service 渚濊禆),
     * 鍒囧簱缁忚繃 Service AOP 杈圭晫,涓旂被鍥惧ぉ鐒朵负 DAG,浠庢牴鏈閬垮弽鍚戜緷璧栧惊鐜€?
     */
    private final ProductQueryService productQueryService;
    private final ProductEventPublisher productEventPublisher;

    /**
     * 淇濆瓨浜у搧妯″瀷璁惧鏈嶅姟鍛戒护
     *
     * @param saveVO
     * @return
     */
    @Override
    public ProductCommand saveProductCommand(ProductCommandSaveVO saveVO) {
        log.info("saveProductCommand saveVO:{}", saveVO);
        //鏍￠獙鍙傛暟
        checkedProductCommandSaveVO(saveVO);
        //鏋勫缓鍙傛暟
        ProductCommand productCommand = builderProductCommandSaveVO(saveVO);
        //鏇存柊
        superManager.save(productCommand);
        publishChange(ProductVersionChangeTypeEnum.CREATE, null, productCommand, "鏂板鍛戒护銆? + productCommand.getCommandName() + "銆?);
        return productCommand;
    }

    /**
     * 淇敼浜у搧妯″瀷璁惧鏈嶅姟鍛戒护
     *
     * @param updateVO
     * @return
     */
    @Override
    public ProductCommand updateProductCommand(ProductCommandUpdateVO updateVO) {
        log.info("updateProductCommand updateVO:{}", updateVO);
        //鏍￠獙鍙傛暟
        checkedProductCommandUpdateVO(updateVO);
        ProductCommand before = superManager.getById(updateVO.getId());
        //鏋勫缓鍙傛暟
        ProductCommand productCommand = BeanUtil.toBeanIgnoreError(updateVO, ProductCommand.class);
        //鏇存柊
        superManager.updateById(productCommand);
        ProductCommand after = superManager.getById(updateVO.getId());
        publishChange(ProductVersionChangeTypeEnum.UPDATE, before, after, "缂栬緫鍛戒护銆? + (after != null ? after.getCommandName() : updateVO.getCommandName()) + "銆?);
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
        publishChange(ProductVersionChangeTypeEnum.DELETE, productCommand, null, "鍒犻櫎鍛戒护銆? + productCommand.getCommandName() + "銆?);
        return result;
    }

    @Override
    public List<ProductCommand> findAllByServiceIds(List<Long> serviceIds) {
        return superManager.findAllByServiceIds(serviceIds);
    }

    /**
     * 鏂板 鏍￠獙鍙傛暟
     *
     * @param saveVO
     */
    private void checkedProductCommandSaveVO(ProductCommandSaveVO saveVO) {
        ArgumentAssert.notNull(saveVO.getServiceId(), "serviceId Cannot be null");
        //鏍￠獙浜у搧妯″瀷鏈嶅姟鏄惁瀛樺湪
        ArgumentAssert.notNull(productServiceService.findOneByProductServiceId(saveVO.getServiceId()), "productService not found");
        ArgumentAssert.notBlank(saveVO.getCommandCode(), "commandCode Cannot be null");
        //鏍￠獙缂栫爜鍛藉悕瑙勮寖
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, saveVO.getCommandCode())) {
            throw BizException.wrap(ThingModelCodeRule.PATTERN_MSG);
        }
        //鏍￠獙CODE
        if (CollUtil.isNotEmpty(superManager.checkCode(saveVO.getServiceId(), saveVO.getCommandCode()))) {
            throw BizException.wrap("commandCode already exists");
        }
        ArgumentAssert.notBlank(saveVO.getCommandName(), "commandName Cannot be null");
    }

    /**
     * 鏂板 鏋勫缓鍙傛暟
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
     * 淇敼 鏍￠獙鍙傛暟
     *
     * @param updateVO
     */
    private void checkedProductCommandUpdateVO(ProductCommandUpdateVO updateVO) {
        ArgumentAssert.notNull(updateVO.getId(), "id Cannot be null");
        ArgumentAssert.notNull(updateVO.getServiceId(), "serviceId Cannot be null");
        //鏍￠獙浜у搧妯″瀷鏄惁瀛樺湪
        ArgumentAssert.notNull(productServiceService.findOneByProductServiceId(updateVO.getServiceId()), "productService not found");
        ArgumentAssert.notBlank(updateVO.getCommandCode(), "commandCode Cannot be null");
        //鏍￠獙缂栫爜鍛藉悕瑙勮寖
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, updateVO.getCommandCode())) {
            throw BizException.wrap(ThingModelCodeRule.PATTERN_MSG);
        }
        ArgumentAssert.notBlank(updateVO.getCommandName(), "commandName Cannot be null");

        //鏍￠獙CODE
        List<ProductCommand> productCommands = superManager.checkCode(updateVO.getServiceId(), updateVO.getCommandCode());
        productCommands.stream()
                .filter(productCommand -> !productCommand.getId().equals(updateVO.getId()))
                .findAny()
                .ifPresent(productProperty -> {
                    throw BizException.wrap("commandCode already exists");
                });
    }

}

