package org.springblade.modules.iot.productcommandrequest.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotproductcommandrequestserviceimplProductCommandRequestServiceImpl.java.mapper.ProductCommandRequestMapper;

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
import org.springblade.modules.iot.productcommandrequest.entity.ProductCommandRequest;
import org.springblade.modules.iot.productcommandrequest.service.ProductCommandRequestService;
import org.springblade.modules.iot.productcommandrequest.vo.result.ProductCommandRequestResultVO;
import org.springblade.modules.iot.productcommandrequest.vo.save.ProductCommandRequestSaveVO;
import org.springblade.modules.iot.productcommandrequest.vo.update.ProductCommandRequestUpdateVO;
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
 * 浜у搧妯″瀷鏈嶅姟鍛戒护灞炴€ц姹傚弬鏁?
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
public class ProductCommandRequestServiceImpl extends BaseServiceImpl<ProductCommandRequestMapper, ProductCommandRequest> implements ProductCommandRequestService {

    private final ProductServiceService productServiceService;
    /**
     * 娉ㄥ叆鍙 {@link ProductQueryService}(鐙珛 bean,闆朵笅娓?Service 渚濊禆),
     * 鍒囧簱缁忚繃 Service AOP 杈圭晫,涓旂被鍥惧ぉ鐒朵负 DAG,浠庢牴鏈閬垮弽鍚戜緷璧栧惊鐜€?
     */
    private final ProductQueryService productQueryService;
    private final ProductEventPublisher productEventPublisher;

    /**
     * 淇濆瓨浜у搧妯″瀷璁惧涓嬪彂鏈嶅姟鍛戒护灞炴€?
     *
     * @param saveVO
     * @return
     */
    @Override
    public ProductCommandRequest saveProductCommandRequest(ProductCommandRequestSaveVO saveVO) {
        log.info("saveProductCommandRequest saveVO:{}", saveVO);
        //鏍￠獙鍙傛暟
        checkedProductCommandRequestSaveVO(saveVO);
        //鏋勫缓鍙傛暟
        ProductCommandRequest productCommandRequest = builderProductCommandRequestSaveVO(saveVO);
        //鏇存柊
        superManager.save(productCommandRequest);
        publishChange(ProductVersionChangeTypeEnum.CREATE, null, productCommandRequest, "鏂板鍛戒护璇锋眰鍙傛暟銆? + productCommandRequest.getParameterName() + "銆?);
        return productCommandRequest;
    }

    /**
     * 淇敼浜у搧妯″瀷璁惧涓嬪彂鏈嶅姟鍛戒护灞炴€?
     *
     * @param updateVO
     * @return
     */
    @Override
    public ProductCommandRequest updateProductCommandRequest(ProductCommandRequestUpdateVO updateVO) {
        log.info("updateProductCommandRequest updateVO:{}", updateVO);
        //鏍￠獙鍙傛暟
        checkedProductCommandRequestUpdateVO(updateVO);
        ProductCommandRequest before = superManager.getById(updateVO.getId());
        //鏋勫缓鍙傛暟
        ProductCommandRequest productCommandRequest = BeanUtil.toBeanIgnoreError(updateVO, ProductCommandRequest.class);
        //鏇存柊
        superManager.updateById(productCommandRequest);
        ProductCommandRequest after = superManager.getById(updateVO.getId());
        publishChange(ProductVersionChangeTypeEnum.UPDATE, before, after, "缂栬緫鍛戒护璇锋眰鍙傛暟銆? + (after != null ? after.getParameterName() : updateVO.getParameterName()) + "銆?);
        return productCommandRequest;
    }

    @Override
    public Boolean deleteProductCommandRequest(Long id) {
        ArgumentAssert.notNull(id, "id Cannot be null");
        ProductCommandRequest productCommandRequest = superManager.getById(id);
        if (null == productCommandRequest) {
            throw BizException.wrap("The productCommandRequest does not exist");
        }
        boolean result = superManager.removeById(id);
        publishChange(ProductVersionChangeTypeEnum.DELETE, productCommandRequest, null, "鍒犻櫎鍛戒护璇锋眰鍙傛暟銆? + productCommandRequest.getParameterName() + "銆?);
        return result;
    }

    @Override
    public List<ProductCommandRequestResultVO> selectCommandRequests(List<Long> commandIds) {
        return BeanUtil.toBeanList(superManager.selectCommandRequests(commandIds), ProductCommandRequestResultVO.class);
    }

    /**
     * 鏂板 鏍￠獙鍙傛暟
     *
     * @param saveVO
     */
    private void checkedProductCommandRequestSaveVO(ProductCommandRequestSaveVO saveVO) {
        ArgumentAssert.notNull(saveVO.getServiceId(), "serviceId Cannot be null");
        //鏍￠獙浜у搧妯″瀷鏈嶅姟鏄惁瀛樺湪
        ArgumentAssert.notNull(productServiceService.findOneByProductServiceId(saveVO.getServiceId()), "productService not found");
        ArgumentAssert.notNull(saveVO.getCommandId(), "commandId Cannot be null");
        ArgumentAssert.notBlank(saveVO.getDatatype(), "datatype Cannot be null");
        ArgumentAssert.notBlank(saveVO.getRequired(), "required Cannot be null");
        ArgumentAssert.notBlank(saveVO.getParameterCode(), "parameterCode Cannot be null");
        //鏍￠獙缂栫爜鍛藉悕瑙勮寖
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, saveVO.getParameterCode())) {
            throw BizException.wrap(ThingModelCodeRule.PATTERN_MSG);
        }
        //鏍￠獙CODE
        if (CollUtil.isNotEmpty(superManager.checkCode(saveVO.getServiceId(), saveVO.getCommandId(), saveVO.getParameterCode()))) {
            throw BizException.wrap("parameterCode already exists");
        }
        ArgumentAssert.notBlank(saveVO.getParameterName(), "parameterName Cannot be null");
    }

    /**
     * 鏂板 鏋勫缓鍙傛暟
     *
     * @param saveVO
     * @return
     */
    private ProductCommandRequest builderProductCommandRequestSaveVO(ProductCommandRequestSaveVO saveVO) {
        saveVO.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        return BeanUtil.toBeanIgnoreError(saveVO, ProductCommandRequest.class);
    }

    private void publishChange(ProductVersionChangeTypeEnum changeType, ProductCommandRequest before, ProductCommandRequest after, String summary) {
        ProductCommandRequest ref = after != null ? after : before;
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
                                .before(before == null ? null : BeanUtil.toBeanIgnoreError(before, ProductCommandRequestResultVO.class))
                                .after(after == null ? null : BeanUtil.toBeanIgnoreError(after, ProductCommandRequestResultVO.class))
                                .changeSummary(summary)
                                .build()));
    }

    /**
     * 淇敼 鏍￠獙鍙傛暟
     *
     * @param updateVO
     */
    private void checkedProductCommandRequestUpdateVO(ProductCommandRequestUpdateVO updateVO) {
        ArgumentAssert.notNull(updateVO.getId(), "id Cannot be null");
        ArgumentAssert.notNull(updateVO.getServiceId(), "serviceId Cannot be null");
        //鏍￠獙浜у搧妯″瀷鏄惁瀛樺湪
        ArgumentAssert.notNull(productServiceService.findOneByProductServiceId(updateVO.getServiceId()), "productService not found");
        ArgumentAssert.notNull(updateVO.getCommandId(), "commandId Cannot be null");
        ArgumentAssert.notBlank(updateVO.getDatatype(), "datatype Cannot be null");
        ArgumentAssert.notBlank(updateVO.getRequired(), "required Cannot be null");
        ArgumentAssert.notBlank(updateVO.getParameterCode(), "parameterCode Cannot be null");
        //鏍￠獙缂栫爜鍛藉悕瑙勮寖
        if (!ReUtil.isMatch(ThingModelCodeRule.PATTERN, updateVO.getParameterCode())) {
            throw BizException.wrap(ThingModelCodeRule.PATTERN_MSG);
        }
        ArgumentAssert.notBlank(updateVO.getParameterName(), "parameterName Cannot be null");
        //鏍￠獙CODE
        List<ProductCommandRequest> productCommandRequests = superManager.checkCode(updateVO.getServiceId(), updateVO.getCommandId(), updateVO.getParameterCode());
        productCommandRequests.stream()
                .filter(productCommandRequest -> !productCommandRequest.getId().equals(updateVO.getId()))
                .findAny()
                .ifPresent(productProperty -> {
                    throw BizException.wrap("parameterCode already exists");
                });
    }

}

