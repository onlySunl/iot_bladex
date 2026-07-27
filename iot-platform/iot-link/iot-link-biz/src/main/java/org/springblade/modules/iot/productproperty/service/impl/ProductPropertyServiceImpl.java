package org.springblade.modules.iot.productproperty.service.impl;

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
import org.springblade.modules.iot.productproperty.entity.ProductProperty;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productproperty.enumeration.DataTypeEnum;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productproperty.manager.ProductPropertyManager;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productproperty.service.ProductPropertyService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productproperty.vo.result.ProductPropertyResultVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productproperty.vo.save.ProductPropertySaveVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productproperty.vo.update.ProductPropertyUpdateVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.productservice.service.ProductServiceService;
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
 * 产品模型服务属性表
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
public class ProductPropertyServiceImpl extends BaseServiceImpl<ProductPropertyMapper, ProductProperty> implements ProductPropertyService {

    private final ProductServiceService productServiceService;
    /**
     * 注入只读 {@link ProductQueryService}(独立 bean,零下游 Service 依赖),
     * 切库经过 Service AOP 边界,且类图天然为 DAG,从根本规避反向依赖循环。
     */
    private final ProductQueryService productQueryService;
    private final ProductEventPublisher productEventPublisher;

    /**
     * 保存产品模型服务属性
     *
     * @param saveVO
     * @return
     */
    @Override
    public ProductProperty saveProductProperty(ProductPropertySaveVO saveVO) {
        log.info("saveProductProperty saveVO:{}", saveVO);
        //校验参数
        checkedProductPropertySaveVO(saveVO);
        //构建参数
        ProductProperty productProperty = builderProductPropertySaveVO(saveVO);
        //更新
        baseMapper.save(productProperty);
        publishChange(ProductVersionChangeTypeEnum.CREATE, null, productProperty, "新增属性「" + productProperty.getPropertyName() + "」");
        return productProperty;
    }

    /**
     * 修改产品模型服务属性
     *
     * @param updateVO
     * @return
     */
    @Override
    public ProductProperty updateProductProperty(ProductPropertyUpdateVO updateVO) {
        log.info("updateProductProperty updateVO:{}", updateVO);
        //校验参数
        checkedProductPropertyUpdateVO(updateVO);
        ProductProperty before = baseMapper.getById(updateVO.getId());
        //构建参数
        ProductProperty productProperty = BeanUtil.toBeanIgnoreError(updateVO, ProductProperty.class);
        //更新
        baseMapper.updateById(productProperty);
        ProductProperty after = baseMapper.getById(updateVO.getId());
        publishChange(ProductVersionChangeTypeEnum.UPDATE, before, after, "编辑属性「" + (after != null ? after.getPropertyName() : updateVO.getPropertyName()) + "」");
        return productProperty;
    }

    @Override
    public Boolean deleteProductProperty(Long id) {
        ArgumentAssert.notNull(id, "id Cannot be null");
        ProductProperty productProperty = baseMapper.getById(id);
        if (null == productProperty) {
            throw new ServiceException("The productProperty does not exist");
        }
        boolean result = baseMapper.removeById(id);
        publishChange(ProductVersionChangeTypeEnum.DELETE, productProperty, null, "删除属性「" + productProperty.getPropertyName() + "」");
        return result;
    }

    @Override
    public List<ProductProperty> findAllByServiceId(Long serviceId) {
        return baseMapper.findAllByServiceId(serviceId);
    }

    @Override
    public List<ProductProperty> findAllByServiceIds(List<Long> serviceIds) {
        return baseMapper.findAllByServiceIds(serviceIds);
    }

    /**
     * 新增 校验参数
     *
     * @param saveVO
     */
    private void checkedProductPropertySaveVO(ProductPropertySaveVO saveVO) {
        ArgumentAssert.notNull(saveVO.getServiceId(), "serviceId Cannot be null");
        //校验产品模型服务是否存在
        ArgumentAssert.notNull(productServiceService.findOneByProductServiceId(saveVO.getServiceId()), "productService not found");
        ArgumentAssert.notBlank(saveVO.getPropertyCode(), "propertyCode Cannot be null");
        //校验编码命名规范
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, saveVO.getPropertyCode())) {
            throw new ServiceException(ThingModelCodeRule.PATTERN_MSG);
        }
        //校验CODE
        if (CollUtil.isNotEmpty(baseMapper.checkCode(saveVO.getServiceId(), saveVO.getPropertyCode()))) {
            throw new ServiceException("propertyCode already exists");
        }
        ArgumentAssert.notBlank(saveVO.getPropertyName(), "propertyName Cannot be null");
        ArgumentAssert.notBlank(saveVO.getDatatype(), "datatype Cannot be null");
        if (!DataTypeEnum.TYPE_COLLECTION.contains(saveVO.getDatatype())) {
            throw new ServiceException("datatype does not exist");
        }
    }

    /**
     * 新增 构建参数
     *
     * @param saveVO
     * @return
     */
    private ProductProperty builderProductPropertySaveVO(ProductPropertySaveVO saveVO) {
        saveVO.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        return BeanUtil.toBeanIgnoreError(saveVO, ProductProperty.class);
    }

    private void publishChange(ProductVersionChangeTypeEnum changeType, ProductProperty before, ProductProperty after, String summary) {
        ProductProperty ref = after != null ? after : before;
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
                                .targetType(ProductChangeTargetTypeEnum.PROPERTY)
                                .before(before == null ? null : BeanUtil.toBeanIgnoreError(before, ProductPropertyResultVO.class))
                                .after(after == null ? null : BeanUtil.toBeanIgnoreError(after, ProductPropertyResultVO.class))
                                .changeSummary(summary)
                                .build()));
    }

    /**
     * 修改 校验参数
     *
     * @param updateVO
     */
    private void checkedProductPropertyUpdateVO(ProductPropertyUpdateVO updateVO) {
        ArgumentAssert.notNull(updateVO.getId(), "id Cannot be null");
        ArgumentAssert.notNull(updateVO.getServiceId(), "serviceId Cannot be null");
        ArgumentAssert.notBlank(updateVO.getPropertyCode(), "propertyCode Cannot be null");
        //校验编码命名规范
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, updateVO.getPropertyCode())) {
            throw new ServiceException(ThingModelCodeRule.PATTERN_MSG);
        }
        ArgumentAssert.notBlank(updateVO.getPropertyName(), "propertyName Cannot be null");
        ArgumentAssert.notBlank(updateVO.getDatatype(), "datatype Cannot be null");
        if (!DataTypeEnum.TYPE_COLLECTION.contains(updateVO.getDatatype())) {
            throw new ServiceException("datatype does not exist");
        }
        //校验CODE
        List<ProductProperty> productProperties = baseMapper.checkCode(updateVO.getServiceId(), updateVO.getPropertyCode());
        productProperties.stream()
                .filter(productProperty -> !productProperty.getId().equals(updateVO.getId()))
                .findAny()
                .ifPresent(productProperty -> {
                    throw new ServiceException("propertyCode already exists");
                });
    }

}

