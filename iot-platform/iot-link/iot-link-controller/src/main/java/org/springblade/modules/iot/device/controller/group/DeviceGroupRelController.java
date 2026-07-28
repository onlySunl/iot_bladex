package org.springblade.modules.iot.device.controller.group;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mvc.controller.BaseController;
import org.springblade.common.base.request.PageParams;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.common.database.mybatis.conditions.query.QueryWrap;
import org.springblade.common.interfaces.echo.EchoService;
import org.springblade.modules.iot.datascope.DataScopeHelper;
import org.springblade.modules.iot.device.entity.group.DeviceGroupRel;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.service.group.DeviceGroupRelService;
import org.springblade.modules.iot.device.service.group.DeviceGroupService;
import org.springblade.modules.iot.device.vo.query.DevicePageQuery;
import org.springblade.modules.iot.device.vo.query.group.DeviceGroupPageQuery;
import org.springblade.modules.iot.device.vo.query.group.DeviceGroupRelPageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.device.vo.result.group.DeviceGroupRelResultVO;
import org.springblade.modules.iot.device.vo.result.group.DeviceGroupResultVO;
import org.springblade.modules.iot.device.vo.save.group.DeviceGroupRelSaveVO;
import org.springblade.modules.iot.device.vo.update.group.DeviceGroupRelUpdateVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 设备分组关系表
 * </p>
 *
 * @author mqttsnet
 * @since 2025-06-23 14:06:46
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/deviceGroupRel")
@Tag(name = "设备分组资源关系")
public class DeviceGroupRelController extends BaseController<DeviceGroupRelService, Long, DeviceGroupRel
        , DeviceGroupRelSaveVO, DeviceGroupRelUpdateVO, DeviceGroupRelPageQuery, DeviceGroupRelResultVO> {
    private final EchoService echoService;

    private final DeviceService deviceService;

    private final DeviceGroupService deviceGroupService;

    @Override
    public QueryWrap<DeviceGroupRel> handlerWrapper(DeviceGroupRel model, PageParams<DeviceGroupRelPageQuery> params) {
        QueryWrap<DeviceGroupRel> queryWrap = super.handlerWrapper(model, params);
        // 开启数据权限
        DataScopeHelper.startDataScope("device_group_rel");
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
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public void handlerResult(IPage<DeviceGroupRelResultVO> page) {
        super.handlerResult(page);

        List<Long> groupIds = Optional.ofNullable(page.getRecords())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(DeviceGroupRelResultVO::getGroupId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();


        List<String> deviceIdentifications = Optional.ofNullable(page.getRecords())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(DeviceGroupRelResultVO::getDeviceIdentification)
                .filter(Objects::nonNull)
                .distinct()
                .toList();


        CompletableFuture<Map<Long, DeviceGroupResultVO>> groupFuture = groupIds.isEmpty() ?
                CompletableFuture.completedFuture(Collections.emptyMap()) :
                executeWithContext(() ->
                        deviceGroupService.getDeviceGroupResultVOList(
                                DeviceGroupPageQuery.builder()
                                        .ids(groupIds)
                                        .build()
                        )
                ).thenApply(groups ->
                        Optional.ofNullable(groups)
                                .orElseGet(Collections::emptyList)
                                .stream()
                                .filter(Objects::nonNull)
                                .filter(g -> g.getId() != null)
                                .collect(Collectors.toMap(
                                        DeviceGroupResultVO::getId,
                                        Function.identity(),
                                        (first, second) -> first
                                ))
                );

        CompletableFuture<Map<String, DeviceResultVO>> deviceFuture =
                deviceIdentifications.isEmpty() ?
                        CompletableFuture.completedFuture(Collections.emptyMap()) :
                        executeWithContext(() ->
                                deviceService.getDeviceResultVOList(
                                        DevicePageQuery.builder()
                                                .deviceIdentificationList(deviceIdentifications)
                                                .build()
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

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(deviceFuture, groupFuture);
        allFutures.join();

        Map<String, DeviceResultVO> deviceMap = deviceFuture.join();
        Map<Long, DeviceGroupResultVO> groupMap = groupFuture.join();

        Optional.ofNullable(page.getRecords()).ifPresent(records ->
                records.forEach(item -> {
                    Optional.ofNullable(item.getDeviceIdentification())
                            .map(deviceMap::get)
                            .ifPresent(device -> {
                                item.setDeviceId(device.getId());
                                item.setDeviceName(device.getDeviceName());
                                item.setNodeType(device.getNodeType());
                                item.setDeviceStatus(device.getDeviceStatus());
                                item.setProductIdentification(device.getProductIdentification());
                            });
                    Optional.ofNullable(item.getGroupId())
                            .map(groupMap::get)
                            .ifPresent(group -> {
                                item.setGroupName(group.getGroupName());
                            });
                })
        );
    }


}


