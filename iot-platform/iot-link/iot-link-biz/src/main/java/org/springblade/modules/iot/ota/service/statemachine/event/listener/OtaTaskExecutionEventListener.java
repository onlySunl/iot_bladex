package org.springblade.modules.iot.ota.service.statemachine.event.listener;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.modules.iot.ota.dto.OtaUpgradeFileResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradesResultDTO;
import org.springblade.modules.iot.ota.service.statemachine.event.OtaTaskExecutionEvent;
import org.springblade.modules.iot.ota.service.statemachine.event.handler.OtaTaskExecutionHandler;
import org.springblade.modules.iot.ota.service.statemachine.event.source.OtaTaskExecutionEventSource;
import org.springblade.modules.iot.utils.ota.OtaUpgradeFileUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * OTA任务执行事件监听器
 * <p>
 * 监听OTA任务执行事件并异步处理
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Slf4j
@Component
@AllArgsConstructor
public class OtaTaskExecutionEventListener {

    private final OtaTaskExecutionHandler otaTaskExecutionHandler;
    private final OtaUpgradeFileUtils otaUpgradeFileUtils;

    /**
     * 处理OTA任务执行事件
     *
     * @param event OTA任务执行事件
     * @return 处理结果
     */
    @EventListener
    public void handleOtaTaskExecutionEvent(OtaTaskExecutionEvent event) {
        Instant startTime = Instant.now();
        try {
            if (event.getSource() instanceof OtaTaskExecutionEventSource source) {
                processOtaTaskExecution(source, startTime);
            }
            log.warn("无效的OTA任务执行事件源类型: {}", event.getSource().getClass());
        } catch (Exception e) {
            log.error("处理OTA任务执行事件失败", e);
        }
    }

    /**
     * 处理OTA任务执行
     *
     * @param source    事件源
     * @param startTime 开始时间
     */
    private void processOtaTaskExecution(OtaTaskExecutionEventSource source, Instant startTime) {
        OtaUpgradeTasksResultDTO task = source.getUpgradeTask();
        OtaUpgradesResultDTO upgradePackage = source.getUpgradePackage();
        Map<Long, OtaUpgradeFileResultDTO> fileInfoMap = getUpgradePackageFileInfoMap(upgradePackage);
        List<String> deviceIdentificationList = source.getDeviceIdentificationList();
        log.info("开始处理OTA任务执行 - 任务ID: {}, 任务名称: {}, 设备数量: {}", task.getId(), task.getTaskName(), deviceIdentificationList.size());
        if (CollUtil.isEmpty(deviceIdentificationList)) {
            log.warn("设备列表为空，跳过OTA任务执行 - 任务ID: {}", task.getId());
            return;
        }
        deviceIdentificationList.forEach(deviceIdentification -> otaTaskExecutionHandler.processDeviceUpgrade(deviceIdentification, task, upgradePackage, fileInfoMap));
    }

    /**
     * 获取升级包文件信息
     *
     * @param upgradePackage 升级包信息
     * @return {@link Map<Long,OtaUpgradeFileResultDTO>} 文件信息映射
     */
    private Map<Long, OtaUpgradeFileResultDTO> getUpgradePackageFileInfoMap(OtaUpgradesResultDTO upgradePackage) {
        return Optional.ofNullable(upgradePackage)
                .map(OtaUpgradesResultDTO::getFileIds)
                .filter(CollUtil::isNotEmpty)
                .map(otaUpgradeFileUtils::getOtaUpgradeFileInfoMap)
                .orElse(Collections.emptyMap());
    }
}
