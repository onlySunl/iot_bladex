package org.springblade.modules.iot.productproperty.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotproductpropertyserviceimplProductPropertyServiceImpl.java.mapper.ProductPropertyMapper;

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
import org.springblade.modules.iot.productproperty.entity.ProductProperty;
import org.springblade.modules.iot.productproperty.enumeration.DataTypeEnum;
import org.springblade.modules.iot.productproperty.service.ProductPropertyService;
import org.springblade.modules.iot.productproperty.vo.result.ProductPropertyResultVO;
import org.springblade.modules.iot.productproperty.vo.save.ProductPropertySaveVO;
import org.springblade.modules.iot.productproperty.vo.update.ProductPropertyUpdateVO;
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
 * 浜у搧妯″瀷鏈嶅姟灞炴€ц〃
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
     * 娉ㄥ叆鍙 {@link ProductQueryService}(鐙珛 bean,闆朵笅娓?Service 渚濊禆),
     * 鍒囧簱缁忚繃 Service AOP 杈圭晫,涓旂被鍥惧ぉ鐒朵负 DAG,浠庢牴鏈閬垮弽鍚戜緷璧栧惊鐜€?
     */
    private final ProductQueryService productQueryService;
    private final ProductEventPublisher productEventPublisher;

    /**
     * 淇濆瓨浜у搧妯″瀷鏈嶅姟灞炴€?
     *
     * @param saveVO
     * @return
     */
    @Override
    public ProductProperty saveProductProperty(ProductPropertySaveVO saveVO) {
        log.info("saveProductProperty saveVO:{}", saveVO);
        //鏍￠獙鍙傛暟
        checkedProductPropertySaveVO(saveVO);
        //鏋勫缓鍙傛暟
        ProductProperty productProperty = builderProductPropertySaveVO(saveVO);
        //鏇存柊
        superManager.save(productProperty);
        publishChange(ProductVersionChangeTypeEnum.CREATE, null, productProperty, "鏂板灞炴€с€? + productProperty.getPropertyName() + "銆?);
        return productProperty;
    }

    /**
     * 淇敼浜у搧妯″瀷鏈嶅姟灞炴€?
     *
     * @param updateVO
     * @return
     */
    @Override
    public ProductProperty updateProductProperty(ProductPropertyUpdateVO updateVO) {
        log.info("updateProductProperty updateVO:{}", updateVO);
        //鏍￠獙鍙傛暟
        checkedProductPropertyUpdateVO(updateVO);
        ProductProperty before = superManager.getById(updateVO.getId());
        //鏋勫缓鍙傛暟
        ProductProperty productProperty = BeanUtil.toBeanIgnoreError(updateVO, ProductProperty.class);
        //鏇存柊
        superManager.updateById(productProperty);
        ProductProperty after = superManager.getById(updateVO.getId());
        publishChange(ProductVersionChangeTypeEnum.UPDATE, before, after, "缂栬緫灞炴€с€? + (after != null ? after.getPropertyName() : updateVO.getPropertyName()) + "銆?);
        return productProperty;
    }

    @Override
    public Boolean deleteProductProperty(Long id) {
        ArgumentAssert.notNull(id, "id Cannot be null");
        ProductProperty productProperty = superManager.getById(id);
        if (null == productProperty) {
            throw BizException.wrap("The productProperty does not exist");
        }
        boolean result = superManager.removeById(id);
        publishChange(ProductVersionChangeTypeEnum.DELETE, productProperty, null, "鍒犻櫎灞炴€с€? + productProperty.getPropertyName() + "銆?);
        return result;
    }

    @Override
    public List<ProductProperty> findAllByServiceId(Long serviceId) {
        return superManager.findAllByServiceId(serviceId);
    }

    @Override
    public List<ProductProperty> findAllByServiceIds(List<Long> serviceIds) {
        return superManager.findAllByServiceIds(serviceIds);
    }

    /**
     * 鏂板 鏍￠獙鍙傛暟
     *
     * @param saveVO
     */
    private void checkedProductPropertySaveVO(ProductPropertySaveVO saveVO) {
        ArgumentAssert.notNull(saveVO.getServiceId(), "serviceId Cannot be null");
        //鏍￠獙浜у搧妯″瀷鏈嶅姟鏄惁瀛樺湪
        ArgumentAssert.notNull(productServiceService.findOneByProductServiceId(saveVO.getServiceId()), "productService not found");
        ArgumentAssert.notBlank(saveVO.getPropertyCode(), "propertyCode Cannot be null");
        //鏍￠獙缂栫爜鍛藉悕瑙勮寖
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, saveVO.getPropertyCode())) {
            throw BizException.wrap(ThingModelCodeRule.PATTERN_MSG);
        }
        //鏍￠獙CODE
        if (CollUtil.isNotEmpty(superManager.checkCode(saveVO.getServiceId(), saveVO.getPropertyCode()))) {
            throw BizException.wrap("propertyCode already exists");
        }
        ArgumentAssert.notBlank(saveVO.getPropertyName(), "propertyName Cannot be null");
        ArgumentAssert.notBlank(saveVO.getDatatype(), "datatype Cannot be null");
        if (!DataTypeEnum.TYPE_COLLECTION.contains(saveVO.getDatatype())) {
            throw BizException.wrap("datatype does not exist");
        }
    }

    /**
     * 鏂板 鏋勫缓鍙傛暟
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
     * 淇敼 鏍￠獙鍙傛暟
     *
     * @param updateVO
     */
    private void checkedProductPropertyUpdateVO(ProductPropertyUpdateVO updateVO) {
        ArgumentAssert.notNull(updateVO.getId(), "id Cannot be null");
        ArgumentAssert.notNull(updateVO.getServiceId(), "serviceId Cannot be null");
        ArgumentAssert.notBlank(updateVO.getPropertyCode(), "propertyCode Cannot be null");
        //鏍￠獙缂栫爜鍛藉悕瑙勮寖
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, updateVO.getPropertyCode())) {
            throw BizException.wrap(ThingModelCodeRule.PATTERN_MSG);
        }
        ArgumentAssert.notBlank(updateVO.getPropertyName(), "propertyName Cannot be null");
        ArgumentAssert.notBlank(updateVO.getDatatype(), "datatype Cannot be null");
        if (!DataTypeEnum.TYPE_COLLECTION.contains(updateVO.getDatatype())) {
            throw BizException.wrap("datatype does not exist");
        }
        //鏍￠獙CODE
        List<ProductProperty> productProperties = superManager.checkCode(updateVO.getServiceId(), updateVO.getPropertyCode());
        productProperties.stream()
                .filter(productProperty -> !productProperty.getId().equals(updateVO.getId()))
                .findAny()
                .ifPresent(productProperty -> {
                    throw BizException.wrap("propertyCode already exists");
                });
    }

}

