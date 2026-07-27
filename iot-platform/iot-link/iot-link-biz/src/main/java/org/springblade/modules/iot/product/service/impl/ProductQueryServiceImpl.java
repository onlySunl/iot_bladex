package org.springblade.modules.iot.product.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.common.base.request.PageParams;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.common.utils.BeanUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.device.vo.result.ProductOverviewResultVO;
import java.time.LocalDateTime;

import org.springblade.modules.iot.product.entity.Product;
import org.springblade.modules.iot.product.enumeration.ProductStatusEnum;
import org.springblade.modules.iot.product.enumeration.ProductTypeEnum;
import org.springblade.modules.iot.product.manager.ProductManager;
import org.springblade.modules.iot.product.service.ProductQueryService;
import org.springblade.modules.iot.product.vo.param.ProductParamVO;
import org.springblade.modules.iot.product.vo.query.ProductPageQuery;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;
import org.springblade.modules.iot.productcommand.entity.ProductCommand;
import org.springblade.modules.iot.productcommand.manager.ProductCommandManager;
import org.springblade.modules.iot.productcommand.vo.param.ProductCommandParamVO;
import org.springblade.modules.iot.productcommandrequest.manager.ProductCommandRequestManager;
import org.springblade.modules.iot.productcommandrequest.vo.param.ProductCommandRequestParamVO;
import org.springblade.modules.iot.productcommandresponse.manager.ProductCommandResponseManager;
import org.springblade.modules.iot.productcommandresponse.vo.param.ProductCommandResponseParamVO;
import org.springblade.modules.iot.productproperty.entity.ProductProperty;
import org.springblade.modules.iot.productproperty.manager.ProductPropertyManager;
import org.springblade.modules.iot.productproperty.vo.param.ProductPropertyParamVO;
import org.springblade.modules.iot.productservice.entity.ProductServices;
import org.springblade.modules.iot.productservice.enumeration.ProductServiceStatusEnum;
import org.springblade.modules.iot.productservice.manager.ProductServiceManager;
import org.springblade.modules.iot.productservice.vo.param.ProductServiceParamVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 产品基础信息只读查询 Service 实现。
 *
 * <p>仅持有 6 个 Manager(Product + 5 个子项目 Manager),零下游 Service 依赖,
 * 物理上不可能进入循环图。</p>
 *
 * @author mqttsnet
 * @since 2026-05-18
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductManager productManager;
    private final ProductServiceManager productServiceManager;
    private final ProductCommandManager productCommandManager;
    private final ProductPropertyManager productPropertyManager;
    private final ProductCommandRequestManager productCommandRequestManager;
    private final ProductCommandResponseManager productCommandResponseManager;

    @Override
    public ProductResultVO findOneByProductId(Long productId) {
        return BeanPlusUtil.toBeanIgnoreError(productManager.findOneByProductId(productId), ProductResultVO.class);
    }

    @Override
    public ProductResultVO findOneByProductIdentification(String productIdentification) {
        return BeanPlusUtil.toBeanIgnoreError(productManager.findOneByProductIdentification(productIdentification), ProductResultVO.class);
    }

    @Override
    public List<ProductResultVO> getProductResultVOList(ProductPageQuery query) {
        return BeanPlusUtil.toBeanList(productManager.getProductList(query), ProductResultVO.class);
    }

    @Override
    public Long findProductTotal() {
        return productManager.findProductTotal();
    }

    @Override
    public Long countPublishedProducts() {
        return productManager.countPublishedProducts();
    }

    @Override
    public Long countCanaryInProgressProducts() {
        return productManager.countCanaryInProgressProducts();
    }

    @Override
    public Long countThingModelServices() {
        // 走已注入的 ProductServiceManager(BaseService#count,继承自 IService),
        // 保持"Service → Manager"层级,不直接碰 mapper。
        return productServiceManager.count();
    }

    @Override
    public IPage<ProductResultVO> getPage(PageParams<ProductPageQuery> params) {
        IPage<Product> page = productManager.getPage(params);
        return BeanPlusUtil.toBeanPage(page, ProductResultVO.class);
    }

    @Override
    public ProductOverviewResultVO getProductOverview() {
        List<Product> productList = productManager.list();
        ProductOverviewResultVO resultVO = new ProductOverviewResultVO();
        resultVO.setProductsTotalCount(productList.size());

        AtomicLong ordinaryCount = new AtomicLong();
        AtomicLong gatewayCount = new AtomicLong();
        AtomicLong unknownCount = new AtomicLong();
        AtomicLong enabledCount = new AtomicLong();
        AtomicLong disabledCount = new AtomicLong();
        // 增长指标:今日 / 近7天 / 近30天 新增产品数(按 created_time,与上面同一份 productList 内统计)
        AtomicLong todayNewCount = new AtomicLong();
        AtomicLong weekNewCount = new AtomicLong();
        AtomicLong monthNewCount = new AtomicLong();
        LocalDateTime todayStart = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime weekStart = todayStart.minusDays(6);
        LocalDateTime monthStart = todayStart.minusDays(29);

        productList.forEach(product -> {
            if (Objects.equals(product.getProductType(), ProductTypeEnum.COMMON.getValue())) {
                ordinaryCount.incrementAndGet();
            } else if (Objects.equals(product.getProductType(), ProductTypeEnum.GATEWAY.getValue())) {
                gatewayCount.incrementAndGet();
            } else if (Objects.equals(product.getProductType(), ProductTypeEnum.UNKNOWN.getValue())) {
                unknownCount.incrementAndGet();
            }
            if (Objects.equals(product.getProductStatus(), ProductStatusEnum.ACTIVATED.getValue())) {
                enabledCount.incrementAndGet();
            } else if (Objects.equals(product.getProductStatus(), ProductStatusEnum.LOCKED.getValue())) {
                disabledCount.incrementAndGet();
            }
            LocalDateTime createdTime = product.getCreatedTime();
            if (createdTime != null) {
                if (!createdTime.isBefore(todayStart)) {
                    todayNewCount.incrementAndGet();
                }
                if (!createdTime.isBefore(weekStart)) {
                    weekNewCount.incrementAndGet();
                }
                if (!createdTime.isBefore(monthStart)) {
                    monthNewCount.incrementAndGet();
                }
            }
        });

        resultVO.setOrdinaryProductsCount(ordinaryCount.intValue());
        resultVO.setGatewayProductsCount(gatewayCount.intValue());
        resultVO.setUnknownProductsCount(unknownCount.intValue());
        resultVO.setEnabledCount(enabledCount.intValue());
        resultVO.setDisabledCount(disabledCount.intValue());
        resultVO.setTodayNewCount(todayNewCount.intValue());
        resultVO.setWeekNewCount(weekNewCount.intValue());
        resultVO.setMonthNewCount(monthNewCount.intValue());
        return resultVO;
    }

    @Override
    public ProductParamVO selectFullProductByProductIdentification(String productIdentification) {
        return selectFullProductByProductIdentification(productIdentification, false);
    }

    @Override
    public ProductParamVO selectFullProductByProductIdentification(String productIdentification,
                                                                   boolean includeInactiveServices) {
        Product product = Optional.ofNullable(productManager.findOneByProductIdentification(productIdentification))
                .orElseThrow(() -> BizException.wrap("Product not found: " + productIdentification));

        ProductParamVO productDetails = BeanPlusUtil.toBeanIgnoreError(product, ProductParamVO.class);

        ProductServices condition = new ProductServices();
        condition.setProductId(product.getId());
        // 仅启用服务时才下 service_status 过滤;含停用(物模型快照场景)则不限服务状态
        if (!includeInactiveServices) {
            condition.setServiceStatus(ProductServiceStatusEnum.ACTIVATED.getValue());
        }
        List<ProductServices> productServicesList = Optional.ofNullable(productServiceManager.selectProductServicesList(condition))
                .orElse(Collections.emptyList());

        List<Long> serviceIds = productServicesList.stream().map(ProductServices::getId).collect(Collectors.toList());

        List<ProductCommand> productCommandList = Optional.ofNullable(productCommandManager.findAllByServiceIds(serviceIds))
                .orElse(Collections.emptyList());
        List<ProductProperty> productPropertiesList = Optional.ofNullable(productPropertyManager.findAllByServiceIds(serviceIds))
                .orElse(Collections.emptyList());

        List<ProductServiceParamVO> services = productServicesList.stream().map(ps -> {
            ProductServiceParamVO svc = BeanPlusUtil.toBeanIgnoreError(ps, ProductServiceParamVO.class);

            List<ProductCommandParamVO> commands = productCommandList.stream()
                    .filter(command -> Objects.equals(command.getServiceId(), ps.getId()))
                    .map(command -> {
                        ProductCommandParamVO commandParamVO = BeanPlusUtil.toBeanIgnoreError(command, ProductCommandParamVO.class);

                        List<ProductCommandRequestParamVO> requests = productCommandRequestManager.selectCommandRequests(Collections.singletonList(command.getId()))
                                .stream()
                                .map(req -> BeanPlusUtil.toBeanIgnoreError(req, ProductCommandRequestParamVO.class))
                                .filter(req -> Objects.equals(req.getCommandId(), command.getId()))
                                .collect(Collectors.toList());
                        commandParamVO.setRequests(requests);

                        List<ProductCommandResponseParamVO> responses = productCommandResponseManager.selectCommandResponses(Collections.singletonList(command.getId()))
                                .stream()
                                .map(rsp -> BeanPlusUtil.toBeanIgnoreError(rsp, ProductCommandResponseParamVO.class))
                                .filter(rsp -> Objects.equals(rsp.getCommandId(), command.getId()))
                                .collect(Collectors.toList());
                        commandParamVO.setResponses(responses);

                        return commandParamVO;
                    })
                    .collect(Collectors.toList());
            svc.setCommands(commands);

            List<ProductPropertyParamVO> properties = productPropertiesList.stream()
                    .filter(property -> Objects.equals(property.getServiceId(), ps.getId()))
                    .map(pp -> BeanPlusUtil.toBeanIgnoreError(pp, ProductPropertyParamVO.class))
                    .collect(Collectors.toList());
            svc.setProperties(properties);

            return svc;
        }).collect(Collectors.toList());

        productDetails.setServices(services);
        return productDetails;
    }
}
