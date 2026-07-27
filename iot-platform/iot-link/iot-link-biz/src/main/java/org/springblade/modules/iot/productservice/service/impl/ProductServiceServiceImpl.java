package org.springblade.modules.iot.productservice.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotproductserviceserviceimplProductServiceServiceImpl.java.mapper.ProductServiceMapper;

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
import org.springblade.modules.iot.productservice.entity.ProductServices;
import org.springblade.modules.iot.productservice.enumeration.ProductServiceStatusEnum;
import org.springblade.modules.iot.productservice.service.ProductServiceService;
import org.springblade.modules.iot.productservice.vo.result.ProductServiceResultVO;
import org.springblade.modules.iot.productservice.vo.save.ProductServiceSaveVO;
import org.springblade.modules.iot.productservice.vo.update.ProductServiceUpdateVO;
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
 * 浜у搧妯″瀷鏈嶅姟琛?
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
     * 娉ㄥ叆鍙 {@link ProductQueryService}(鐙珛 bean,闆朵笅娓?Service 渚濊禆),
     * 鍒囧簱缁忚繃 Service AOP 杈圭晫,涓旂被鍥惧ぉ鐒朵负 DAG,浠庢牴鏈閬垮弽鍚戜緷璧栧惊鐜€?
     */
    private final ProductQueryService productQueryService;
    private final ProductEventPublisher productEventPublisher;

    /**
     * 淇濆瓨浜у搧妯″瀷鏈嶅姟
     *
     * @param saveVO
     * @return
     */
    @Override
    public ProductServices saveProductService(ProductServiceSaveVO saveVO) {
        log.info("saveProductService saveVO:{}", saveVO);
        //鏍￠獙鍙傛暟
        checkedProductServiceSaveVO(saveVO);
        //鏋勫缓鍙傛暟
        ProductServices productService = builderProductServiceSaveVO(saveVO);
        //鏇存柊
        superManager.save(productService);
        publishChange(ProductVersionChangeTypeEnum.CREATE, null, productService, "鏂板鏈嶅姟銆? + productService.getServiceName() + "銆?);
        return productService;
    }

    /**
     * 淇敼浜у搧妯″瀷鏈嶅姟
     *
     * @param updateVO
     * @return
     */
    @Override
    public ProductServices updateProductService(ProductServiceUpdateVO updateVO) {
        log.info("updateProductService updateVO:{}", updateVO);
        //鏍￠獙鍙傛暟
        checkedProductServiceUpdateVO(updateVO);
        ProductServices before = superManager.getById(updateVO.getId());
        //鏋勫缓鍙傛暟
        ProductServices productServices = BeanUtil.toBeanIgnoreError(updateVO, ProductServices.class);
        //鏇存柊
        superManager.updateById(productServices);
        ProductServices after = superManager.getById(updateVO.getId());
        publishChange(ProductVersionChangeTypeEnum.UPDATE, before, after, "缂栬緫鏈嶅姟銆? + (after != null ? after.getServiceName() : updateVO.getServiceName()) + "銆?);
        return productServices;
    }

    /**
     * 鍒犻櫎浜у搧妯″瀷鏈嶅姟
     *
     * @param id
     * @return
     */
    @Override
    public Boolean deleteProductService(Long id) {
        ArgumentAssert.notNull(id, "id Cannot be null");
        ProductServices productService = superManager.getById(id);
        if (null == productService) {
            throw BizException.wrap("The productService does not exist");
        }
        boolean result = superManager.removeById(id);
        publishChange(ProductVersionChangeTypeEnum.DELETE, productService, null, "鍒犻櫎鏈嶅姟銆? + productService.getServiceName() + "銆?);
        return result;
    }

    @Override
    public ProductServices findOneByProductServiceId(Long serviceId) {
        return superManager.findOneByProductServiceId(serviceId);
    }

    @Override
    public List<ProductServices> selectProductServicesList(ProductServices find) {
        return superManager.selectProductServicesList(find);
    }

    /**
     * 鏂板 鏍￠獙鍙傛暟
     *
     * @param saveVO
     */
    private void checkedProductServiceSaveVO(ProductServiceSaveVO saveVO) {

        ArgumentAssert.notNull(saveVO.getProductId(), "productId Cannot be null");
        //鏍￠獙浜у搧妯″瀷鏄惁瀛樺湪
        ArgumentAssert.notNull(productQueryService.findOneByProductId(saveVO.getProductId()), "product not found");
        ArgumentAssert.notBlank(saveVO.getServiceCode(), "serviceCode Cannot be null");
        //鏍￠獙缂栫爜鍛藉悕瑙勮寖
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, saveVO.getServiceCode())) {
            throw BizException.wrap(ThingModelCodeRule.PATTERN_MSG);
        }
        //鏍￠獙CODE
        if (CollUtil.isNotEmpty(superManager.checkCode(saveVO.getProductId(), saveVO.getServiceCode()))) {
            throw BizException.wrap("serviceCode already exists");
        }
        ArgumentAssert.notBlank(saveVO.getServiceName(), "serviceName Cannot be null");
        ArgumentAssert.notBlank(saveVO.getServiceType(), "serviceType Cannot be null");
        //浜у搧妯″瀷鏈嶅姟鐘舵€?
        ArgumentAssert.notNull(saveVO.getServiceStatus(), "serviceStatus Cannot be null");
        ProductServiceStatusEnum.fromValue(saveVO.getServiceStatus()).orElseThrow(() -> BizException.wrap("serviceStatus is not exist"));

    }

    /**
     * 鏂板 鏋勫缓鍙傛暟
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
     * 淇敼 鏍￠獙鍙傛暟
     *
     * @param updateVO
     */
    private void checkedProductServiceUpdateVO(ProductServiceUpdateVO updateVO) {
        ArgumentAssert.notNull(updateVO.getId(), "id Cannot be null");
        ArgumentAssert.notNull(updateVO.getProductId(), "productId Cannot be null");
        //鏍￠獙浜у搧妯″瀷鏄惁瀛樺湪
        ArgumentAssert.notNull(productQueryService.findOneByProductId(updateVO.getProductId()), "product not found");
        ArgumentAssert.notBlank(updateVO.getServiceCode(), "serviceCode Cannot be null");
        //鏍￠獙缂栫爜鍛藉悕瑙勮寖
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, updateVO.getServiceCode())) {
            throw BizException.wrap(ThingModelCodeRule.PATTERN_MSG);
        }
        ArgumentAssert.notBlank(updateVO.getServiceName(), "serviceName Cannot be null");
        ArgumentAssert.notBlank(updateVO.getServiceType(), "serviceType Cannot be null");
        //浜у搧妯″瀷鐘舵€?
        ArgumentAssert.notNull(updateVO.getServiceStatus(), "serviceStatus Cannot be null");
        ProductServiceStatusEnum.fromValue(updateVO.getServiceStatus()).orElseThrow(() -> BizException.wrap("serviceStatus is not exist"));
        //鏍￠獙CODE
        List<ProductServices> productServicesList = superManager.checkCode(updateVO.getProductId(), updateVO.getServiceCode());
        productServicesList.stream()
                .filter(productServices -> !productServices.getId().equals(updateVO.getId()))
                .findAny()
                .ifPresent(productProperty -> {
                    throw BizException.wrap("serviceCode already exists");
                });

    }

}

