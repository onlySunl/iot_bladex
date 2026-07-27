package org.springblade.modules.iot.productservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import org.springblade.core.log.exception.ServiceException;
import cn.hutool.core.util.ReUtil;
import org.springblade.core.log.exception.ServiceException;
import java.util.Optional;
import org.springblade.core.log.exception.ServiceException;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.common.utils.BeanUtil;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.product.constant.ThingModelCodeRule;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.product.event.publisher.ProductEventPublisher;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.product.event.source.ProductModelChangedSource;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.product.service.ProductQueryService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productservice.entity.ProductServices;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productservice.enumeration.ProductServiceStatusEnum;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productservice.manager.ProductServiceManager;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productservice.service.ProductServiceService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productservice.vo.result.ProductServiceResultVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productservice.vo.save.ProductServiceSaveVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productservice.vo.update.ProductServiceUpdateVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productversionchangelog.enumeration.ProductChangeTargetTypeEnum;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productversionchangelog.enumeration.ProductVersionChangeTypeEnum;
import org.springblade.core.log.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springblade.core.log.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.transaction.annotation.Transactional;
import org.springblade.core.log.exception.ServiceException;

import java.util.List;
import org.springblade.core.log.exception.ServiceException;

/**
 * <p>
 * 业务实现类
 * 产品模型服务表
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
public class ProductServiceServiceImpl extends BaseServiceImpl<ProductServiceMapper, ProductServices> implements ProductServiceService {

    /**
     * 注入只读 {@link ProductQueryService}(独立 bean,零下游 Service 依赖),
     * 切库经过 Service AOP 边界,且类图天然为 DAG,从根本规避反向依赖循环。
     */
    private final ProductQueryService productQueryService;
    private final ProductEventPublisher productEventPublisher;

    /**
     * 保存产品模型服务
     *
     * @param saveVO
     * @return
     */
    @Override
    public ProductServices saveProductService(ProductServiceSaveVO saveVO) {
        log.info("saveProductService saveVO:{}", saveVO);
        //校验参数
        checkedProductServiceSaveVO(saveVO);
        //构建参数
        ProductServices productService = builderProductServiceSaveVO(saveVO);
        //更新
        baseMapper.save(productService);
        publishChange(ProductVersionChangeTypeEnum.CREATE, null, productService, "新增服务「" + productService.getServiceName() + "」");
        return productService;
    }

    /**
     * 修改产品模型服务
     *
     * @param updateVO
     * @return
     */
    @Override
    public ProductServices updateProductService(ProductServiceUpdateVO updateVO) {
        log.info("updateProductService updateVO:{}", updateVO);
        //校验参数
        checkedProductServiceUpdateVO(updateVO);
        ProductServices before = baseMapper.getById(updateVO.getId());
        //构建参数
        ProductServices productServices = BeanUtil.toBeanIgnoreError(updateVO, ProductServices.class);
        //更新
        baseMapper.updateById(productServices);
        ProductServices after = baseMapper.getById(updateVO.getId());
        publishChange(ProductVersionChangeTypeEnum.UPDATE, before, after, "编辑服务「" + (after != null ? after.getServiceName() : updateVO.getServiceName()) + "」");
        return productServices;
    }

    /**
     * 删除产品模型服务
     *
     * @param id
     * @return
     */
    @Override
    public Boolean deleteProductService(Long id) {
        ArgumentAssert.notNull(id, "id Cannot be null");
        ProductServices productService = baseMapper.getById(id);
        if (null == productService) {
            throw new ServiceException("The productService does not exist");
        }
        boolean result = baseMapper.removeById(id);
        publishChange(ProductVersionChangeTypeEnum.DELETE, productService, null, "删除服务「" + productService.getServiceName() + "」");
        return result;
    }

    @Override
    public ProductServices findOneByProductServiceId(Long serviceId) {
        return baseMapper.findOneByProductServiceId(serviceId);
    }

    @Override
    public List<ProductServices> selectProductServicesList(ProductServices find) {
        return baseMapper.selectProductServicesList(find);
    }

    /**
     * 新增 校验参数
     *
     * @param saveVO
     */
    private void checkedProductServiceSaveVO(ProductServiceSaveVO saveVO) {

        ArgumentAssert.notNull(saveVO.getProductId(), "productId Cannot be null");
        //校验产品模型是否存在
        ArgumentAssert.notNull(productQueryService.findOneByProductId(saveVO.getProductId()), "product not found");
        ArgumentAssert.notBlank(saveVO.getServiceCode(), "serviceCode Cannot be null");
        //校验编码命名规范
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, saveVO.getServiceCode())) {
            throw new ServiceException(ThingModelCodeRule.PATTERN_MSG);
        }
        //校验CODE
        if (CollUtil.isNotEmpty(baseMapper.checkCode(saveVO.getProductId(), saveVO.getServiceCode()))) {
            throw new ServiceException("serviceCode already exists");
        }
        ArgumentAssert.notBlank(saveVO.getServiceName(), "serviceName Cannot be null");
        ArgumentAssert.notBlank(saveVO.getServiceType(), "serviceType Cannot be null");
        //产品模型服务状态
        ArgumentAssert.notNull(saveVO.getServiceStatus(), "serviceStatus Cannot be null");
        ProductServiceStatusEnum.fromValue(saveVO.getServiceStatus()).orElseThrow(() -> new ServiceException("serviceStatus is not exist"));

    }

    /**
     * 新增 构建参数
     *
     * @param saveVO
     * @return
     */
    private ProductServices builderProductServiceSaveVO(ProductServiceSaveVO saveVO) {
        saveVO.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        return BeanUtil.toBeanIgnoreError(saveVO, ProductServices.class);
    }

    private void publishChange(ProductVersionChangeTypeEnum changeType, ProductServices before, ProductServices after, String summary) {
        ProductServices ref = after != null ? after : before;
        if (ref == null) {
            return;
        }
        Optional.ofNullable(productQueryService.findOneByProductId(ref.getProductId()))
                .map(ProductResultVO::getProductIdentification)
                .ifPresent(pid -> productEventPublisher.publishProductModelChangedEvent(
                        ProductModelChangedSource.builder()
                                .productIdentification(pid)
                                .changeType(changeType)
                                .targetType(ProductChangeTargetTypeEnum.SERVICE)
                                .before(before == null ? null : BeanUtil.toBeanIgnoreError(before, ProductServiceResultVO.class))
                                .after(after == null ? null : BeanUtil.toBeanIgnoreError(after, ProductServiceResultVO.class))
                                .changeSummary(summary)
                                .build()));
    }

    /**
     * 修改 校验参数
     *
     * @param updateVO
     */
    private void checkedProductServiceUpdateVO(ProductServiceUpdateVO updateVO) {
        ArgumentAssert.notNull(updateVO.getId(), "id Cannot be null");
        ArgumentAssert.notNull(updateVO.getProductId(), "productId Cannot be null");
        //校验产品模型是否存在
        ArgumentAssert.notNull(productQueryService.findOneByProductId(updateVO.getProductId()), "product not found");
        ArgumentAssert.notBlank(updateVO.getServiceCode(), "serviceCode Cannot be null");
        //校验编码命名规范
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, updateVO.getServiceCode())) {
            throw new ServiceException(ThingModelCodeRule.PATTERN_MSG);
        }
        ArgumentAssert.notBlank(updateVO.getServiceName(), "serviceName Cannot be null");
        ArgumentAssert.notBlank(updateVO.getServiceType(), "serviceType Cannot be null");
        //产品模型状态
        ArgumentAssert.notNull(updateVO.getServiceStatus(), "serviceStatus Cannot be null");
        ProductServiceStatusEnum.fromValue(updateVO.getServiceStatus()).orElseThrow(() -> new ServiceException("serviceStatus is not exist"));
        //校验CODE
        List<ProductServices> productServicesList = baseMapper.checkCode(updateVO.getProductId(), updateVO.getServiceCode());
        productServicesList.stream()
                .filter(productServices -> !productServices.getId().equals(updateVO.getId()))
                .findAny()
                .ifPresent(productProperty -> {
                    throw new ServiceException("serviceCode already exists");
                });

    }

}

