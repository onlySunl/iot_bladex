package org.springblade.modules.iot.product.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotproductserviceimplProductServiceImpl.java.mapper.ProductMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.support.Query;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.cache.helper.LinkCacheDataHelper;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.vo.result.ProductOverviewResultVO;
import org.springblade.modules.iot.product.entity.Product;
import org.springblade.modules.iot.product.enumeration.ProductStatusEnum;
import org.springblade.modules.iot.product.enumeration.ProductTypeEnum;
import org.springblade.modules.iot.product.enumeration.ProtocolTypeEnum;
import org.springblade.modules.iot.product.event.publisher.ProductEventPublisher;
import org.springblade.modules.iot.product.event.source.ProductCacheEvictSource;
import org.springblade.modules.iot.product.event.source.ProductModelChangedSource;
import org.springblade.modules.iot.product.service.ProductService;
import org.springblade.modules.iot.product.vo.param.ProductParamVO;
import org.springblade.modules.iot.product.vo.query.ProductPageQuery;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;
import org.springblade.modules.iot.product.vo.save.ProductSaveVO;
import org.springblade.modules.iot.product.vo.update.ProductUpdateVO;
import org.springblade.modules.iot.productcommand.entity.ProductCommand;
import org.springblade.modules.iot.productcommand.service.ProductCommandService;
import org.springblade.modules.iot.productcommand.vo.param.ProductCommandParamVO;
import org.springblade.modules.iot.productcommand.vo.save.ProductCommandSaveVO;
import org.springblade.modules.iot.productcommandrequest.entity.ProductCommandRequest;
import org.springblade.modules.iot.productcommandrequest.service.ProductCommandRequestService;
import org.springblade.modules.iot.productcommandrequest.vo.param.ProductCommandRequestParamVO;
import org.springblade.modules.iot.productcommandrequest.vo.save.ProductCommandRequestSaveVO;
import org.springblade.modules.iot.productcommandresponse.entity.ProductCommandResponse;
import org.springblade.modules.iot.productcommandresponse.service.ProductCommandResponseService;
import org.springblade.modules.iot.productcommandresponse.vo.param.ProductCommandResponseParamVO;
import org.springblade.modules.iot.productcommandresponse.vo.save.ProductCommandResponseSaveVO;
import org.springblade.modules.iot.productproperty.entity.ProductProperty;
import org.springblade.modules.iot.productproperty.service.ProductPropertyService;
import org.springblade.modules.iot.productproperty.vo.param.ProductPropertyParamVO;
import org.springblade.modules.iot.productproperty.vo.save.ProductPropertySaveVO;
import org.springblade.modules.iot.productservice.entity.ProductServices;
import org.springblade.modules.iot.productservice.enumeration.ProductServiceStatusEnum;
import org.springblade.modules.iot.productservice.service.ProductServiceService;
import org.springblade.modules.iot.productservice.vo.param.ProductServiceParamVO;
import org.springblade.modules.iot.productservice.vo.save.ProductServiceSaveVO;
import org.springblade.modules.iot.producttopic.service.ProductTopicService;
import org.springblade.modules.iot.productversion.service.ProductVersionService;
import org.springblade.modules.iot.productversionchangelog.enumeration.ProductChangeTargetTypeEnum;
import org.springblade.modules.iot.productversionchangelog.enumeration.ProductVersionChangeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 涓氬姟瀹炵幇绫?
 * 浜у搧妯″瀷
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
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, Product> implements ProductService {

    private final ProductServiceService productServiceService;

    private final ProductPropertyService productPropertyService;

    private final ProductCommandService productCommandService;

    private final ProductCommandRequestService productCommandRequestService;

    private final ProductCommandResponseService productCommandResponseService;

    private final DeviceService deviceService;

    private final ProductTopicService productTopicService;

    private final LinkCacheDataHelper linkCacheDataHelper;

    private final ProductEventPublisher productEventPublisher;

    /**
     * 浜у搧 CRUD 鏃跺悓姝ュ埛鏂拌崏绋垮揩鐓?+ 浜у搧鍒犻櫎鏃剁骇鑱旇蒋鍒?product_version 琛屻€?
     * ProductVersionServiceImpl 渚濊禆 ProductQueryService(涓嶆槸 ProductService),鏃犲惊鐜緷璧栥€?
     */
    private final ProductVersionService productVersionService;

    @Override
    public IPage<ProductResultVO> getPage(Query params) {
        IPage<Product> page = superManager.getPage(params);
        return BeanUtil.toBeanPage(page, ProductResultVO.class);
    }

    /**
     * 鑾峰彇浜у搧妯″瀷鎬婚噺
     *
     * @return {@link Long} 浜у搧妯″瀷鏁版嵁鎬婚噺
     */
    @Override
    public Long findProductTotal() {
        return superManager.findProductTotal();
    }

    /**
     * 淇濆瓨浜у搧妯″瀷
     *
     * @param saveVO
     * @return
     */
    @Override
    public ProductSaveVO saveProduct(ProductSaveVO saveVO) {
        log.info("saveProduct saveVO:{}", saveVO);
        //鏍￠獙鍙傛暟
        checkedProductSaveVO(saveVO);
        //鏋勫缓鍙傛暟
        Product product = builderProductSaveVO(saveVO);
        //淇濆瓨浜у搧
        superManager.save(product);
        // 鍒濆鍖栦骇鍝乀opic
        initProductBaseTopics(product.getProductIdentification(), Boolean.FALSE);

        // 鍙戝竷浜у搧鐗╂ā鍨嬪彉鏇翠簨浠?
        productEventPublisher.publishProductModelChangedEvent(ProductModelChangedSource.builder()
                .productIdentification(product.getProductIdentification())
                .changeType(ProductVersionChangeTypeEnum.CREATE)
                .targetType(ProductChangeTargetTypeEnum.PRODUCT_INFO)
                .after(BeanUtil.toBeanIgnoreError(product, ProductResultVO.class))
                .changeSummary("鏂板浜у搧銆? + product.getProductName() + "銆?)
                .build());

        return saveVO;
    }

    /**
     * 淇敼浜у搧妯″瀷
     *
     * @param updateVO
     * @return
     */
    @Override
    public ProductUpdateVO updateProduct(ProductUpdateVO updateVO) {
        log.info("updateProduct updateVO:{}", updateVO);
        //鏍￠獙鍙傛暟
        checkedProductUpdateVO(updateVO);
        Product before = superManager.getById(updateVO.getId());
        //鏋勫缓鍙傛暟
        Product product = BeanUtil.toBeanIgnoreError(updateVO, Product.class);
        //鏇存柊
        superManager.updateById(BeanUtil.toBeanIgnoreError(updateVO, Product.class));
        Product after = superManager.getById(updateVO.getId());
        // 鍒濆鍖栦骇鍝乀opic
        initProductBaseTopics(product.getProductIdentification(), Boolean.TRUE);

        // 鍙戝竷浜у搧鐗╂ā鍨嬪彉鏇翠簨浠?
        productEventPublisher.publishProductModelChangedEvent(ProductModelChangedSource.builder()
                .productIdentification(product.getProductIdentification())
                .changeType(ProductVersionChangeTypeEnum.UPDATE)
                .targetType(ProductChangeTargetTypeEnum.PRODUCT_INFO)
                .before(BeanUtil.toBeanIgnoreError(before, ProductResultVO.class))
                .after(BeanUtil.toBeanIgnoreError(after, ProductResultVO.class))
                .changeSummary("缂栬緫浜у搧銆? + (after != null ? after.getProductName() : updateVO.getProductName()) + "銆?)
                .build());

        return updateVO;
    }

    /**
     * 鍒犻櫎浜у搧妯″瀷
     *
     * @param id 浜у搧ID
     * @return {@link Boolean} 鏄惁鍒犻櫎鎴愬姛
     */
    @Override
    public Boolean deleteProduct(Long id) {
        ArgumentAssert.notNull(id, "id Cannot be null");
        Product product = superManager.getById(id);
        if (null == product) {
            throw BizException.wrap("The product does not exist");
        }
        if (deviceService.isProductInUseByDevices(product.getProductIdentification())) {
            throw BizException.wrap("The product is bound to the device and cannot be deleted");
        }
        String productIdentification = product.getProductIdentification();
        // 绾ц仈杞垹 product_version 鎵€鏈夌増鏈(DRAFT + 鍘嗗彶 PUBLISHED/CANARY/SHADOW),
        // TD 鍘嗗彶璧勬簮鐢?purgeHistory 鐙珛娴佺▼澶勭悊,鏈柟娉曚粎娓呭熀纭€琛ㄥ叧绯汇€?
        int affectedVersions = productVersionService.softDeleteAllByProductIdentification(productIdentification);
        if (affectedVersions > 0) {
            log.info("[deleteProduct] cascade softDelete product_version productIdentification={} affected={}", productIdentification, affectedVersions);
        }
        Boolean removed = superManager.removeById(id);
        // 鍙戠紦瀛樺け鏁堜簨浠?浜у搧宸插垹,鐩戝惉鍣?AFTER_COMMIT 澶辨晥浜у搧鍩虹缂撳瓨銆?
        // 鐗╂ā鍨嬬紦瀛樻寜 (productIdentification, versionNo) 鍒囧垎,鐗堟湰蹇収涓嶅彲鍙?+ 7d TTL 鑷姩杩囨湡,
        // 浜у搧琚垹鍚庤€佺紦瀛樹笉浼氬啀琚懡涓?鍥犱负娌′汉鑳藉啀鏌ュ埌璇?product),涓嶉渶瑕佷富鍔ㄦ竻銆?
        productEventPublisher.publishProductCacheEvictEvent(
                ProductCacheEvictSource.builder().productIdentification(productIdentification)
                        .contextMap(AuthUtil.getLocalMap()).build());
        return removed;
    }

    /**
     * 鏌ヨ浜у搧绠＄悊瀹屾暣淇℃伅锛堝寘鍚湇鍔°€佸睘鎬с€佸懡浠わ級
     *
     * @param productIdentification 浜у搧鏍囪瘑
     * @return {@link ProductParamVO} 浜у搧绠＄悊瀹屾暣鍙傛暟VO
     * @throws com.mqttsnet.basic.exception.BizException 濡傛灉浜у搧涓嶅瓨鍦?
     */
    @Override
    public ProductParamVO selectFullProductByProductIdentification(String productIdentification) {
        // 鏌ヨ浜у搧锛屽鏋滀笉瀛樺湪鍒欐姏鍑哄紓甯?
        Product product = Optional.ofNullable(superManager.findOneByProductIdentification(productIdentification))
            .orElseThrow(() -> BizException.wrap("Product not found: " + productIdentification));

        // 杞崲鍩烘湰浜у搧淇℃伅
        ProductParamVO productDetails = BeanUtil.toBeanIgnoreError(product, ProductParamVO.class);

        // 鏌ヨ浜у搧鏈嶅姟鍒楄〃锛堝彧鏌ヨ宸叉縺娲荤殑鏈嶅姟锛?
        List<ProductServices> productServicesList = Optional.of(new ProductServices())
            .map(find -> {
                find.setProductId(product.getId());
                find.setServiceStatus(ProductServiceStatusEnum.ACTIVATED.getValue());
                return productServiceService.selectProductServicesList(find);
            })
            .orElse(Collections.emptyList());

        List<Long> serviceIds = productServicesList.stream().map(ProductServices::getId).collect(Collectors.toList());

        // 鏌ヨ鎵€鏈夋湇鍔＄殑鍛戒护鍜屽睘鎬?
        List<ProductCommand> productCommandList = Optional.ofNullable(productCommandService.findAllByServiceIds(serviceIds))
            .orElse(Collections.emptyList());

        List<ProductProperty> productPropertiesList = Optional.ofNullable(productPropertyService.findAllByServiceIds(serviceIds))
            .orElse(Collections.emptyList());

        // 缁勮鏈嶅姟淇℃伅锛堝寘鍚懡浠ゅ拰灞炴€э級
        List<ProductServiceParamVO> services = productServicesList.stream().map(ps -> {
            ProductServiceParamVO service = BeanUtil.toBeanIgnoreError(ps, ProductServiceParamVO.class);

            // 缁勮鏈嶅姟鐨勫懡浠ゅ垪琛?
            List<ProductCommandParamVO> commands = productCommandList.stream()
                .filter(command -> Objects.equals(command.getServiceId(), ps.getId()))  // Filter by Service ID
                .map(command -> {
                    ProductCommandParamVO commandParamVO = BeanUtil.toBeanIgnoreError(command,
                        ProductCommandParamVO.class);

                    // 缁勮鍛戒护鐨勮姹傚弬鏁?
                    List<ProductCommandRequestParamVO> filteredRequests =
                        productCommandRequestService.selectCommandRequests(Collections.singletonList(command.getId()))
                            .stream()
                            .map(request -> BeanUtil.toBeanIgnoreError(request, ProductCommandRequestParamVO.class))
                            .filter(request -> Objects.equals(request.getCommandId(), command.getId()))
                            .collect(Collectors.toList());
                    commandParamVO.setRequests(filteredRequests);

                    // 缁勮鍛戒护鐨勫搷搴斿弬鏁?
                    List<ProductCommandResponseParamVO> filteredResponses =
                        productCommandResponseService.selectCommandResponses(Collections.singletonList(command.getId()))
                            .stream()
                            .map(response -> BeanUtil.toBeanIgnoreError(response, ProductCommandResponseParamVO.class))
                            .filter(response -> Objects.equals(response.getCommandId(), command.getId()))
                            .collect(Collectors.toList());
                    commandParamVO.setResponses(filteredResponses);

                    return commandParamVO;
                })
                .collect(Collectors.toList());
            service.setCommands(commands);

            // 缁勮鏈嶅姟鐨勫睘鎬у垪琛?
            List<ProductPropertyParamVO> properties = productPropertiesList.stream()
                .filter(property -> Objects.equals(property.getServiceId(), ps.getId()))  // Filter by Service ID
                .map(pp -> BeanUtil.toBeanIgnoreError(pp, ProductPropertyParamVO.class))
                .collect(Collectors.toList());
            service.setProperties(properties);

            return service;
        }).collect(Collectors.toList());

        productDetails.setServices(services);
        return productDetails;
    }

    @Override
    public void importProductJson(MultipartFile file, String appId) {
        String originalFilename = file.getOriginalFilename();
        if (!"json".equalsIgnoreCase(FileUtil.getSuffix(originalFilename))) {
            throw BizException.wrap("the file suffix must be json");
        }
        try {
            String jsonContent = IoUtil.read(file.getInputStream(), StandardCharsets.UTF_8);
            // 瑙ｆ瀽浜у搧妯″瀷鏁版嵁
            ProductParamVO productParamVO = JSON.parseObject(jsonContent, ProductParamVO.class);
            if (StrUtil.isNotBlank(appId)) {
                productParamVO.setAppId(appId);
            }
            this.productJsonDataAnalysis(productParamVO);
        } catch (IOException e) {
            log.error("import product json error: {}", e.getMessage(), e);
            throw BizException.wrap("import product json failed!");
        }
    }

    @Override
    public ProductResultVO findOneByProductId(Long productId) {
        return BeanUtil.toBeanIgnoreError(superManager.getById(productId), ProductResultVO.class);
    }

    /**
     * 鏍规嵁浜у搧鏍囪瘑鏌ヨ浜у搧璇︽儏
     *
     * @param productIdentification 浜у搧鏍囪瘑
     * @return {@link ProductResultVO} 浜у搧璇︽儏
     */
    @Override
    public ProductResultVO findOneByProductIdentification(String productIdentification) {
        return BeanUtil.toBeanIgnoreError(superManager.findOneByProductIdentification(productIdentification), ProductResultVO.class);
    }

    @Override
    public List<ProductResultVO> findListByProductIdentificationList(List<String> productIdentificationList) {
        if (CollUtil.isEmpty(productIdentificationList)) {
            return Collections.emptyList();
        }
        return BeanUtil.copyToList(superManager.findListByProductIdentificationList(productIdentificationList), ProductResultVO.class);
    }

    @Override
    public void generateProductJson(ProductParamVO paramVO) {
        this.productJsonDataAnalysis(paramVO);
    }

    /**
     * 鑾峰彇浜у搧姒傚喌缁熻
     *
     * @return {@link ProductOverviewResultVO} 浜у搧姒傚喌缁熻
     */
    @Override
    public ProductOverviewResultVO getProductOverview() {
        List<Product> productList = superManager.list();
        ProductOverviewResultVO resultVO = new ProductOverviewResultVO();

        resultVO.setProductsTotalCount(productList.size());

        AtomicLong ordinaryCount = new AtomicLong();
        AtomicLong gatewayCount = new AtomicLong();
        AtomicLong unknownCount = new AtomicLong();
        AtomicLong enabledCount = new AtomicLong();
        AtomicLong disabledCount = new AtomicLong();

        productList.forEach(product -> {
            // 浜у搧绫诲瀷缁熻
            if (Objects.equals(product.getProductType(), ProductTypeEnum.COMMON.getValue())) {
                ordinaryCount.incrementAndGet();
            } else if (Objects.equals(product.getProductType(), ProductTypeEnum.GATEWAY.getValue())) {
                gatewayCount.incrementAndGet();
            } else if (Objects.equals(product.getProductType(), ProductTypeEnum.UNKNOWN.getValue())) {
                unknownCount.incrementAndGet();
            }

            // 浜у搧鐘舵€佺粺璁?
            if (Objects.equals(product.getProductStatus(), ProductStatusEnum.ACTIVATED.getValue())) {
                enabledCount.incrementAndGet();
            } else if (Objects.equals(product.getProductStatus(), ProductStatusEnum.LOCKED.getValue())) {
                disabledCount.incrementAndGet();
            }
        });

        resultVO.setOrdinaryProductsCount(ordinaryCount.intValue());
        resultVO.setGatewayProductsCount(gatewayCount.intValue());
        resultVO.setUnknownProductsCount(unknownCount.intValue());
        resultVO.setEnabledCount(enabledCount.intValue());
        resultVO.setDisabledCount(disabledCount.intValue());

        return resultVO;
    }

    @Override
    public Boolean initProductBaseTopics(String productIdentification, Boolean reInit) {
        log.info("寮€濮嬪垵濮嬪寲浜у搧鍩虹Topic - 浜у搧鏍囪瘑: {}, 鏄惁閲嶆柊鍒濆鍖? {}", productIdentification, reInit);
        ProductResultVO productResultVO = findOneByProductIdentification(productIdentification);
        ArgumentAssert.notNull(productResultVO, "浜у搧淇℃伅涓嶅瓨鍦?);
        try {
            productTopicService.initProductBaseTopics(productIdentification, ProductTypeEnum.valueOf(productResultVO.getProductType()), reInit);
            log.info("鎴愬姛鍒濆鍖栦骇鍝佸熀纭€Topic - 浜у搧鏍囪瘑: {}", productIdentification);
            return true;
        } catch (Exception e) {
            log.error("鍒濆鍖栦骇鍝佸熀纭€Topic澶辫触 - 浜у搧鏍囪瘑: {}", productIdentification, e);
            throw BizException.wrap("鍒濆鍖栦骇鍝佸熀纭€Topic澶辫触璇烽噸璇?);
        }
    }

    @Override
    public List<ProductResultVO> getProductResultVOList(ProductPageQuery query) {
        return BeanUtil.toBeanList(superManager.getProductList(query), ProductResultVO.class);
    }

    /**
     * 鏂板 鏍￠獙鍙傛暟
     *
     * @param saveVO
     */
    private void checkedProductSaveVO(ProductSaveVO saveVO) {
        ArgumentAssert.notNull(saveVO.getProductType(), "productType Cannot be null");
        if (!ProductTypeEnum.TYPE_COLLECTION.contains(saveVO.getProductType())) {
            throw BizException.wrap("productType is not exist");
        }
        ArgumentAssert.notBlank(saveVO.getAppId(), "appId Cannot be null");
        ArgumentAssert.notBlank(saveVO.getProductName(), "productName Cannot be null");
        ArgumentAssert.notBlank(saveVO.getManufacturerId(), "manufacturerId Cannot be null");
        ArgumentAssert.notBlank(saveVO.getManufacturerName(), "manufacturerName Cannot be null");
        ArgumentAssert.notBlank(saveVO.getModel(), "model Cannot be null");
        ArgumentAssert.notBlank(saveVO.getDataFormat(), "dataFormat Cannot be null");
        ArgumentAssert.notBlank(saveVO.getProtocolType(), "protocolType Cannot be null");
        Optional<ProtocolTypeEnum> protocolTypeOptional = ProtocolTypeEnum.fromValue(saveVO.getProtocolType());

        if (protocolTypeOptional.isEmpty()) {
            throw BizException.wrap("protocolType is not exist");
        }
        ArgumentAssert.notBlank(saveVO.getDeviceType(), "deviceType Cannot be null");
        //楠岃瘉浜у搧妯″瀷鏄惁瀛樺湪
        Product product = superManager.findOneByManufacturerIdAndModelAndDeviceType(saveVO.getManufacturerId(), saveVO.getModel(), saveVO.getDeviceType());
        if (ObjectUtil.isNotNull(product)) {
            throw BizException.wrap("product model already exists");
        }
        //浜у搧妯″瀷鐘舵€?
        ArgumentAssert.notNull(saveVO.getProductStatus(), "productStatus Cannot be null");
        ProductStatusEnum.fromValue(saveVO.getProductStatus()).orElseThrow(() -> BizException.wrap("productStatus is not exist"));

        // 娉?浜у搧鐗堟湰鍙?product_version)涓嶅啀鐢卞垱寤?缂栬緫琛ㄥ崟缁存姢銆?
        // 鐗堟湰鐢熷懡鍛ㄦ湡鐢?ProductVersionService 鐨勮崏绋?鍙戝竷娴佺▼鎺ョ 鈹€鈹€
        // 鍒涘缓鏃?activeVersionNo 涓虹┖,鍙戝竷鏃堕洩鑺辩敓鎴愮増鏈彿骞跺洖鍐欍€?
        // 浜у搧鍞竴鎬у凡鐢变笂鏂?manufacturerId+model+deviceType 鏍￠獙瑕嗙洊銆?
    }

    /**
     * 鏂板 鏋勫缓鍙傛暟
     *
     * @param saveVO
     * @return
     */
    private Product builderProductSaveVO(ProductSaveVO saveVO) {
        //浜у搧鏍囪瘑鐢熸垚瑙勫垯: 闆姳绠楁硶鐢熸垚
        saveVO.setProductIdentification(String.valueOf(SnowflakeIdUtil.nextId()));
        saveVO.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        return BeanUtil.toBeanIgnoreError(saveVO, Product.class);
    }

    /**
     * 淇敼 鏍￠獙鍙傛暟
     *
     * @param updateVO
     */
    private void checkedProductUpdateVO(ProductUpdateVO updateVO) {

        ArgumentAssert.notNull(updateVO.getId(), "id Cannot be null");
        ArgumentAssert.notNull(updateVO.getProductType(), "productType Cannot be null");
        if (!ProductTypeEnum.TYPE_COLLECTION.contains(updateVO.getProductType())) {
            throw BizException.wrap("productType is not exist");
        }
        ArgumentAssert.notBlank(updateVO.getAppId(), "appId Cannot be null");
        ArgumentAssert.notBlank(updateVO.getProductName(), "productName Cannot be null");
        ArgumentAssert.notBlank(updateVO.getManufacturerId(), "manufacturerId Cannot be null");
        ArgumentAssert.notBlank(updateVO.getManufacturerName(), "manufacturerName Cannot be null");
        ArgumentAssert.notBlank(updateVO.getModel(), "model Cannot be null");
        ArgumentAssert.notBlank(updateVO.getDataFormat(), "dataFormat Cannot be null");
        ArgumentAssert.notBlank(updateVO.getProtocolType(), "protocolType Cannot be null");
        Optional<ProtocolTypeEnum> protocolTypeOptional = ProtocolTypeEnum.fromValue(updateVO.getProtocolType());

        if (protocolTypeOptional.isEmpty()) {
            throw BizException.wrap("protocolType is not exist");
        }
        ArgumentAssert.notBlank(updateVO.getDeviceType(), "deviceType Cannot be null");
        //浜у搧妯″瀷鐘舵€?
        ArgumentAssert.notNull(updateVO.getProductStatus(), "productStatus Cannot be null");
        ProductStatusEnum.fromValue(updateVO.getProductStatus()).orElseThrow(() -> BizException.wrap("productStatus is not exist"));

        // 娉?浜у搧鐗堟湰鍙?product_version)涓嶅啀鐢卞垱寤?缂栬緫琛ㄥ崟缁存姢,
        // 鐗堟湰鐢熷懡鍛ㄦ湡鐢?ProductVersionService 鐨勮崏绋?鍙戝竷娴佺▼鎺ョ銆?
    }

    /**
     * 瑙ｆ瀽浜у搧妯″瀷鏁版嵁
     *
     * @param productVO 浜у搧妯″瀷鍙傛暟
     */
    private void productJsonDataAnalysis(ProductParamVO productVO) {
        log.info("productJsonDataAnalysis...productVO:{}", JSON.toJSONString(productVO));
        //鏈嶅姟灞炴€цВ鏋愬鐞?
        Product product = BeanUtil.toBeanIgnoreError(productVO, Product.class);
        //浜у搧鏍囪瘑鐢熸垚瑙勫垯: 闆姳绠楁硶鐢熸垚
        product.setProductIdentification(String.valueOf(SnowflakeIdUtil.nextId()));
        product.setProductStatus(ProductStatusEnum.LOCKED.getValue());
        product.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        //鏂板 鏍￠獙鍙傛暟
        checkedProductSaveVO(BeanUtil.toBeanIgnoreError(product, ProductSaveVO.class));
        boolean saveProductFlag = superManager.save(product);
        if (!saveProductFlag) {
            throw BizException.wrap("Product information storage fails");
        }
        //娣诲姞鏈嶅姟鏁版嵁
        List<ProductServiceParamVO> services = productVO.getServices();
        if (CollUtil.isEmpty(services)) {
            throw BizException.wrap("The product service information is empty. Please check the product model JSON file.");
        }
        ProductServiceParamVO productServiceParamVO;
        for (ProductServiceParamVO service : services) {
            productServiceParamVO = service;
            ProductServiceSaveVO productServices = BeanUtil.toBeanIgnoreError(productServiceParamVO, ProductServiceSaveVO.class);
            productServices.setProductId(product.getId());
            productServices.setServiceStatus(product.getProductStatus());
            productServices.setCreatedOrgId(AuthUtil.getCurrentDeptId());
            ProductServices productService = productServiceService.saveProductService(productServices);
            if (ObjectUtil.isNull(productService)) {
                throw BizException.wrap("Service capability Data storage fails");
            }
            //娣诲姞灞炴€ф暟鎹?
            List<ProductPropertyParamVO> properties = productServiceParamVO.getProperties();
            if (!properties.isEmpty()) {
                ProductPropertySaveVO propertySaveVO;
                for (ProductPropertyParamVO property : properties) {
                    propertySaveVO = BeanUtil.toBeanIgnoreError(property, ProductPropertySaveVO.class);
                    propertySaveVO.setServiceId(productService.getId());
                    propertySaveVO.setCreatedOrgId(AuthUtil.getCurrentDeptId());
                    ProductProperty productProperty = productPropertyService.saveProductProperty(propertySaveVO);
                    if (ObjectUtil.isNull(productProperty)) {
                        throw BizException.wrap("Property capability Data storage fails");
                    }
                }
            }
            //娣诲姞鍛戒护鏁版嵁
            List<ProductCommandParamVO> commands = productServiceParamVO.getCommands();
            if (!commands.isEmpty()) {
                ProductCommandParamVO productCommandParamVO;
                for (ProductCommandParamVO command : commands) {
                    productCommandParamVO = command;
                    ProductCommandSaveVO productCommand = BeanUtil.toBeanIgnoreError(productCommandParamVO, ProductCommandSaveVO.class);
                    productCommand.setServiceId(productService.getId());
                    productCommand.setCreatedOrgId(AuthUtil.getCurrentDeptId());
                    ProductCommand saveProductCommand = productCommandService.saveProductCommand(productCommand);
                    if (ObjectUtil.isNull(saveProductCommand)) {
                        throw BizException.wrap("command capability Data storage fails");
                    }
                    //浜у搧璇锋眰鏈嶅姟鍛戒护
                    List<ProductCommandRequestParamVO> requests = productCommandParamVO.getRequests();
                    if (!requests.isEmpty()) {
                        for (ProductCommandRequestParamVO request : requests) {
                            ProductCommandRequestSaveVO productCommandRequest = BeanUtil.toBeanIgnoreError(request, ProductCommandRequestSaveVO.class);
                            productCommandRequest.setServiceId(productService.getId());
                            productCommandRequest.setCommandId(saveProductCommand.getId());
                            productCommandRequest.setCreatedOrgId(AuthUtil.getCurrentDeptId());
                            ProductCommandRequest saveCommandRequestFlag = productCommandRequestService.saveProductCommandRequest(productCommandRequest);
                            if (ObjectUtil.isNull(saveCommandRequestFlag)) {
                                throw BizException.wrap("productCommandRequest capability Data storage fails");
                            }
                        }
                    }
                    //浜у搧鍝嶅簲鏈嶅姟鍛戒护
                    List<ProductCommandResponseParamVO> responses = productCommandParamVO.getResponses();
                    if (!responses.isEmpty()) {
                        for (ProductCommandResponseParamVO respons : responses) {
                            ProductCommandResponseSaveVO commandResponse = BeanUtil.toBeanIgnoreError(respons, ProductCommandResponseSaveVO.class);
                            commandResponse.setServiceId(productService.getId());
                            commandResponse.setCommandId(saveProductCommand.getId());
                            commandResponse.setCreatedOrgId(AuthUtil.getCurrentDeptId());
                            ProductCommandResponse saveCommandResponseFlag = productCommandResponseService.saveProductCommandResponse(commandResponse);
                            if (ObjectUtil.isNull(saveCommandResponseFlag)) {
                                throw BizException.wrap("productCommandResponse capability Data storage fails");
                            }
                        }
                    }
                }
            }
        }

        // 鍒濆鍖栦骇鍝乀opic
        initProductBaseTopics(product.getProductIdentification(), Boolean.TRUE);

        // 鍙戝竷浜у搧鐗╂ā鍨嬪彉鏇翠簨浠?
        productEventPublisher.publishProductModelChangedEvent(ProductModelChangedSource.builder()
                .productIdentification(product.getProductIdentification())
                .changeType(ProductVersionChangeTypeEnum.CREATE)
                .targetType(ProductChangeTargetTypeEnum.PRODUCT_INFO)
                .after(BeanUtil.toBeanIgnoreError(product, ProductResultVO.class))
                .changeSummary("鏂板浜у搧銆? + product.getProductName() + "銆?)
                .build());
    }

    // 鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€ 浜у搧鐗堟湰鎸囬拡鍒囨崲 service 鍏ュ彛 鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€
    //
    // 涓轰粈涔堢嫭绔嬩袱涓柟娉曡€屼笉鏄悎鎴愪竴涓甫 boolean / enum 鍙傛暟:
    //   * 鐏板害鍙戝竷闇€瑕?鎹曡幏 鍒囨崲鍓?activeVersionNo 鍐欏叆 previousFullVersionNo",澶栭儴浼犱笉浜?涔嬪墠鐨勫€?
    //     鐨勮涔?Service 鍐呴儴璇诲嚭鏉ユ渶瀹夊叏
    //   * 鍥炴粴鐨?娓呯┖ previousFullVersionNo"鍔ㄤ綔鍙睘浜庡洖婊氶摼璺?璺熷彂甯冨畬鍏ㄤ笉鍚屾
    //
    // 璺ㄥ煙璋冪敤鏂?productversion 鍩?璋冩湰 Service 鑰岄潪 ProductManager:
    //   * 璧?@DS(BASE_TENANT) 鍒囩鎴峰簱;Manager 鏃?@DS 浼?fallback 榛樿搴?
    //   * 绂佹璺ㄥ眰绾ц皟鐢?鈹€鈹€ 绫荤害鏉熷凡鍦ㄥ洟闃熻绾﹂噷鍙嶅鏄庣‘

    @Override
    public Product switchActiveVersionForPublish(String productIdentification, String newActiveVersion,
                                                 boolean recordCurrentAsPrevious) {
        ArgumentAssert.notBlank(productIdentification, "productIdentification must not be blank");
        ArgumentAssert.notBlank(newActiveVersion, "newActiveVersion must not be blank");

        Product product = Optional.ofNullable(superManager.findOneByProductIdentification(productIdentification))
                .orElseThrow(() -> BizException.wrap("Product not found: " + productIdentification));
        String previousActive = product.getActiveVersionNo();
        product.setActiveVersionNo(newActiveVersion);
        if (recordCurrentAsPrevious) {
            // 鐏板害鍙戝竷:鎶婂垏鎹㈠墠鐨勭増鏈彿璁板叆澶囧繕鎸囬拡,渚涘悗缁洖婊?/ 鐏板害璺敱 / 鏂拌澶囩粦绋冲畾鐗?
            product.setPreviousFullVersionNo(previousActive);
        }
        superManager.updateById(product);
        if (!recordCurrentAsPrevious) {
            // 鍏ㄩ噺鍙戝竷:浜у搧鑴辩鐏板害鎬?鏄惧紡娓呯┖ previousFullVersionNo銆倁pdateById 鍦?NOT_NULL 绛栫暐涓嬪啓涓嶆帀 null,
            // 蹇呴』 set(null) 寮哄埗 SET NULL 鈥斺€?鍚﹀垯鐏板害鏅嬪崌涓哄叏閲忓悗浜у搧闀挎湡娈嬬暀 previous:鏂拌澶囦細涓€鐩寸粦鑰佺ǔ瀹氱増
            // (瑙?DeviceServiceImpl#resolveBindVersionForNewDevice),"鐏板害涓?缁熻涔熶細璇垽
            superManager.clearPreviousFullVersion(productIdentification);
            product.setPreviousFullVersionNo(null);
        }
        // 鍙戠紦瀛樺け鏁堜簨浠?activeVersionNo 宸插彉,鐩戝惉鍣?AFTER_COMMIT 澶辨晥浜у搧鍩虹缂撳瓨
        productEventPublisher.publishProductCacheEvictEvent(
                ProductCacheEvictSource.builder().productIdentification(productIdentification)
                        .contextMap(AuthUtil.getLocalMap()).build());
        return product;
    }

    @Override
    public Product rollbackActiveVersion(String productIdentification, String targetVersion) {
        ArgumentAssert.notBlank(productIdentification, "productIdentification must not be blank");
        ArgumentAssert.notBlank(targetVersion, "targetVersion must not be blank");

        Product product = Optional.ofNullable(superManager.findOneByProductIdentification(productIdentification))
                .orElseThrow(() -> BizException.wrap("Product not found: " + productIdentification));
        product.setActiveVersionNo(targetVersion);
        superManager.updateById(product);
        // 鍥炴粴鍚庝骇鍝佽劚绂荤伆搴︽€?鏄惧紡娓呯┖ previousFullVersionNo銆倁pdateById 鍦?NOT_NULL 绛栫暐涓嬪啓涓嶆帀 null,
        // 蹇呴』 set(null) 寮哄埗 SET NULL 鈥斺€?鍚﹀垯娈嬬暀 previous 浼氳鏂拌澶囩粦鑰佺ǔ瀹氱増 +"鐏板害涓?缁熻璇垽
        superManager.clearPreviousFullVersion(productIdentification);
        product.setPreviousFullVersionNo(null);
        // 鍙戠紦瀛樺け鏁堜簨浠?activeVersionNo 宸插彉,鐩戝惉鍣?AFTER_COMMIT 澶辨晥浜у搧鍩虹缂撳瓨
        productEventPublisher.publishProductCacheEvictEvent(
                ProductCacheEvictSource.builder().productIdentification(productIdentification)
                        .contextMap(AuthUtil.getLocalMap()).build());
        return product;
    }
}
