package org.springblade.modules.iot.device.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.tool.api.R;
import org.springblade.core.mp.base.BaseController;
import org.springblade.core.boot.request.PageParam;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.common.database.mybatis.conditions.query.QueryWrap;
import org.springblade.common.interfaces.echo.EchoService;
import org.springblade.modules.iot.datascope.DataScopeHelper;
import org.springblade.modules.iot.device.entity.DeviceAclRule;
import org.springblade.modules.iot.device.service.DeviceAclRuleService;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.vo.query.DeviceAclRulePageQuery;
import org.springblade.modules.iot.device.vo.query.DevicePageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceAclRuleResultVO;
import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.device.vo.save.DeviceAclRuleSaveVO;
import org.springblade.modules.iot.device.vo.update.DeviceAclRuleUpdateVO;
import org.springblade.modules.iot.product.service.ProductService;
import org.springblade.modules.iot.product.vo.query.ProductPageQuery;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * 设备访问控制(ACL)规则表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-11 19:57:46
 * @create [2025-06-11 19:57:46] [mqttsnet] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/deviceAclRule")
@Tag(name = "设备访问控制(ACL)规则")
public class DeviceAclRuleController extends SuperController<DeviceAclRuleService, Long, DeviceAclRule
        , DeviceAclRuleSaveVO, DeviceAclRuleUpdateVO, DeviceAclRulePageQuery, DeviceAclRuleResultVO> {
    private final EchoService echoService;

    private final ProductService productService;

    private final DeviceService deviceService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public QueryWrap<DeviceAclRule> handlerWrapper(DeviceAclRule model, PageParams<DeviceAclRulePageQuery> params) {
        QueryWrap<DeviceAclRule> queryWrap = super.handlerWrapper(model, params);
        // 开启数据权限
        DataScopeHelper.startDataScope("device_acl_rule");
        return queryWrap;
    }

    /**
     * 在上下文中执行异步任务
     *
     * @param task 需要执行的任务
     * @param <T>  返回类型
     * @return {@link CompletableFuture<T>} 任务执行结果
     */
    private <T> CompletableFuture<T> executeWithContext(Supplier<T> task) {
        Map<String, String> localMap = ContextUtil.getLocalMap();
        return CompletableFuture.supplyAsync(() -> {
            ContextUtil.setLocalMap(localMap);
            try {
                return task.get();
            } finally {
                ContextUtil.remove();
            }
        });
    }

    @Override
    public void handlerResult(IPage<DeviceAclRuleResultVO> page) {
        super.handlerResult(page);

        // 提取产品标识和设备标识
        List<String> productIds = Optional.ofNullable(page.getRecords())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(DeviceAclRuleResultVO::getProductIdentification)
                .filter(StrUtil::isNotBlank)
                .distinct()
                .toList();

        List<String> deviceIds = Optional.ofNullable(page.getRecords())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(DeviceAclRuleResultVO::getDeviceIdentification)
                .filter(StrUtil::isNotBlank)
                .distinct()
                .toList();

        // 并行查询产品信息
        CompletableFuture<Map<String, ProductResultVO>> productFuture =
                CollectionUtils.isEmpty(productIds) ?
                        CompletableFuture.completedFuture(Collections.emptyMap()) :
                        executeWithContext(() ->
                                productService.getProductResultVOList(
                                        ProductPageQuery.builder().productIdentificationList(productIds).build()
                                )
                        ).thenApply(products ->
                                Optional.ofNullable(products)
                                        .orElseGet(Collections::emptyList)
                                        .stream()
                                        .filter(Objects::nonNull)
                                        .filter(p -> p.getProductIdentification() != null)
                                        .collect(Collectors.toMap(
                                                ProductResultVO::getProductIdentification,
                                                Function.identity(),
                                                (first, second) -> first
                                        ))
                        );

        // 并行查询设备信息
        CompletableFuture<Map<String, DeviceResultVO>> deviceFuture =
                CollectionUtils.isEmpty(deviceIds) ?
                        CompletableFuture.completedFuture(Collections.emptyMap()) :
                        executeWithContext(() ->
                                deviceService.getDeviceResultVOList(
                                        DevicePageQuery.builder().deviceIdentificationList(deviceIds).build()
                                )
                        ).thenApply(devices ->
                                Optional.ofNullable(devices)
                                        .orElseGet(Collections::emptyList)
                                        .stream()
                                        .filter(Objects::nonNull)
                                        .filter(d -> d.getDeviceIdentification() != null)
                                        .collect(Collectors.toMap(
                                                DeviceResultVO::getDeviceIdentification,
                                                Function.identity(),
                                                (first, second) -> first
                                        ))
                        );

        // 等待所有查询完成
        Map<String, ProductResultVO> productMap = productFuture.join();
        Map<String, DeviceResultVO> deviceMap = deviceFuture.join();

        // 填充结果数据
        Optional.ofNullable(page.getRecords()).ifPresent(records ->
                records.forEach(item -> {
                    // 设置产品信息
                    Optional.ofNullable(item.getProductIdentification())
                            .map(productMap::get)
                            .ifPresent(item::setProductResultVO);

                    // 设置设备信息
                    Optional.ofNullable(item.getDeviceIdentification())
                            .map(deviceMap::get)
                            .ifPresent(item::setDeviceResultVO);
                })
        );
    }

    @Override
    public R<DeviceAclRuleResultVO> getDetail(Long id) {
        return Optional.ofNullable(super.getDetail(id))
                .filter(R::getIsSuccess)
                .map(R::getData)
                .map(item -> {
                    // 并行查询产品信息
                    CompletableFuture<Optional<ProductResultVO>> productFuture =
                            Optional.ofNullable(item.getProductIdentification())
                                    .filter(StrUtil::isNotBlank)
                                    .map(productId ->
                                            executeWithContext(() ->
                                                    productService.getProductResultVOList(
                                                            ProductPageQuery.builder()
                                                                    .productIdentificationList(List.of(productId))
                                                                    .build()
                                                    )
                                            ).thenApply(products ->
                                                    Optional.ofNullable(products)
                                                            .orElseGet(Collections::emptyList)
                                                            .stream()
                                                            .filter(Objects::nonNull)
                                                            .findFirst()
                                            )
                                    )
                                    .orElse(CompletableFuture.completedFuture(Optional.empty()));

                    // 并行查询设备信息
                    CompletableFuture<Optional<DeviceResultVO>> deviceFuture =
                            Optional.ofNullable(item.getDeviceIdentification())
                                    .filter(StrUtil::isNotBlank)
                                    .map(deviceId ->
                                            executeWithContext(() ->
                                                    deviceService.getDeviceResultVOList(
                                                            DevicePageQuery.builder()
                                                                    .deviceIdentificationList(List.of(deviceId))
                                                                    .build()
                                                    )
                                            ).thenApply(devices ->
                                                    Optional.ofNullable(devices)
                                                            .orElseGet(Collections::emptyList)
                                                            .stream()
                                                            .filter(Objects::nonNull)
                                                            .findFirst()
                                            )
                                    )
                                    .orElse(CompletableFuture.completedFuture(Optional.empty()));

                    // 等待所有查询完成并设置结果
                    productFuture.join().ifPresent(item::setProductResultVO);
                    deviceFuture.join().ifPresent(item::setDeviceResultVO);

                    return item;
                })
                .map(R::success)
                .orElseGet(() -> R.fail("未找到详情数据!"));
    }

}


