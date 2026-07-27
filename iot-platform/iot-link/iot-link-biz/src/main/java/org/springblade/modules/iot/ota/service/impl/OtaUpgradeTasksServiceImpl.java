package org.springblade.modules.iot.ota.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSON;
import org.springblade.core.mp.support.Query;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.vo.result.DeviceDetailsResultVO;
import org.springblade.modules.iot.device.vo.update.DeviceUpdateVO;
import org.springblade.modules.iot.ota.converter.OtaUpgradeCommandConverter;
import org.springblade.modules.iot.ota.dto.OtaUpgradeFileResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTargetsResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradesResultDTO;
import org.springblade.modules.iot.ota.entity.OtaUpgradeRecords;
import org.springblade.modules.iot.ota.entity.OtaUpgradeTasks;
import org.springblade.modules.iot.ota.entity.OtaUpgrades;
import org.springblade.modules.iot.ota.enumeration.OtaPackageSignMethodEnum;
import org.springblade.modules.iot.ota.enumeration.OtaPackageStatusEnum;
import org.springblade.modules.iot.ota.enumeration.OtaPackageTypeEnum;
import org.springblade.modules.iot.ota.enumeration.OtaTaskRecordAppConfirmStatusEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeMethodEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeScopeEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTargetStatusEnum;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum;
import org.springblade.modules.iot.ota.service.OtaUpgradeRecordsService;
import org.springblade.modules.iot.ota.service.OtaUpgradeTargetsService;
import org.springblade.modules.iot.ota.service.OtaUpgradeTasksService;
import org.springblade.modules.iot.ota.service.OtaUpgradesService;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeRecordsPageQuery;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTargetsPageQuery;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTasksPageQuery;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradesPageQuery;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsResultVO;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeTasksResultVO;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeRecordStatusEnum;
import org.springblade.modules.iot.ota.service.support.OtaModelVersionSwitcher;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradesResultVO;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradeTargetsSaveVO;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradeTasksSaveVO;
import org.springblade.modules.iot.ota.vo.update.OtaUpgradeTasksUpdateVO;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaCommandResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaListUpgradeableVersionsResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaPullParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaPullResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReadResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReportParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReportResponseParam;
import org.springblade.modules.iot.utils.ota.OtaUpgradeFileUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 涓氬姟瀹炵幇绫?
 * OTA鍗囩骇浠诲姟琛?
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:40:04
 * @create [2024-01-12 22:40:04] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Service
public class OtaUpgradeTasksServiceImpl extends BaseServiceImpl<OtaUpgradeTasksMapper, OtaUpgradeTasks> implements OtaUpgradeTasksService {

    private final OtaUpgradesService otaUpgradesService;

    private final DeviceService deviceService;

    private final OtaUpgradeRecordsService otaUpgradeRecordsService;

    private final OtaUpgradeTargetsService otaUpgradeTargetsService;

    private final OtaUpgradeFileUtils otaUpgradeFileUtils;

    private final OtaModelVersionSwitcher otaModelVersionSwitcher;

    /**
     * Save OTA Upgrade Task
     *
     * @param saveVO 淇濆瓨鍙傛暟
     * @return {@link OtaUpgradeTasksSaveVO} 杩斿洖缁撴灉
     */
    @Override
    public OtaUpgradeTasksSaveVO saveUpgradeTask(OtaUpgradeTasksSaveVO saveVO) {
        log.info("saveUpgradeTask saveVO: {}", saveVO);

        validateOtaUpgradeTasksSaveVO(saveVO);

        OtaUpgrades otaUpgrade = otaUpgradesService.getById(saveVO.getUpgradeId());
        if (Objects.isNull(otaUpgrade)) {
            throw BizException.wrap("OTA upgrade package does not exist");
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;
        }

        // Map the saveVO to your OtaUpgradeTask entity
        OtaUpgradeTasks otaUpgradeTask = buildOtaUpgradeTaskFromSaveVO(saveVO);

        // Persist the OtaUpgradeTask entity using your manager or repository
        boolean save = superManager.save(otaUpgradeTask);

        // Return the saved entity or a custom response
        if (!save) {
            throw BizException.wrap("Failed to save OTA upgrade task");
        }

        if (!OtaUpgradeScopeEnum.ALL_DEVICES.getValue().equals(saveVO.getUpgradeScope())) {
            ArgumentAssert.notEmpty(saveVO.getTargetValueList(), "Target values cannot be empty");
            List<OtaUpgradeTargetsSaveVO> otaUpgradeTargetsSaveVOList = saveVO.getTargetValueList().stream().map(targetValue -> new OtaUpgradeTargetsSaveVO()
                    .setTaskId(otaUpgradeTask.getId())
                    .setTargetValue(targetValue)
                    .setTargetStatus(OtaUpgradeTargetStatusEnum.PENDING.getValue())
                    .setCreatedOrgId(AuthUtil.getCurrentDeptId())).collect(Collectors.toList());
            otaUpgradeTargetsService.saveBatchForOtaUpgradeTargets(otaUpgradeTargetsSaveVOList);
        }

        // Map the saved entity back to OtaUpgradeTasksSaveVO if needed
        return BeanUtil.toBeanIgnoreError(otaUpgradeTask, OtaUpgradeTasksSaveVO.class);
    }

    /**
     * Update OTA Upgrade Task
     *
     * @param updateVO 鏇存柊鍙傛暟
     * @return {@link OtaUpgradeTasksUpdateVO} 杩斿洖缁撴灉
     */
    @Override
    public OtaUpgradeTasksUpdateVO updateUpgradeTask(OtaUpgradeTasksUpdateVO updateVO) {
        log.info("Updating OTA upgrade task: {}", updateVO);

        // Validate the updateVO object
        validateOtaUpgradeTasksUpdateVO(updateVO);

        // Fetch the existing task and update with new details
        OtaUpgradeTasks otaUpgradeTask = superManager.getById(updateVO.getId());

        if (Objects.isNull(otaUpgradeTask)) {
            throw BizException.wrap("OTA upgrade task not found");
        }
        Builder<OtaUpgradeTasks> otaUpgradeTasksBuilder = builderOtaUpgradeTasksUpdateVO(updateVO);
        otaUpgradeTask = otaUpgradeTasksBuilder.with(OtaUpgradeTasks::setId, updateVO.getId()).build();

        // Save the updated entity
        superManager.updateById(otaUpgradeTask);

        // Map the updated entity back to OtaUpgradeTasksUpdateVO if needed
        return BeanUtil.toBeanIgnoreError(otaUpgradeTask, OtaUpgradeTasksUpdateVO.class);
    }

    /**
     * Update OTA Upgrade Task Status
     *
     * @param id     涓婚敭
     * @param status 鐘舵€?
     * @return {@link Boolean} 杩斿洖缁撴灉
     */
    @Override
    public Boolean changeTaskStatus(Long id, Integer status) {
        ArgumentAssert.notNull(id, "Task ID cannot be null");
        ArgumentAssert.notNull(status, "Status cannot be null");

        OtaUpgradeTasks otaUpgradeTask = superManager.getById(id);
        if (Objects.isNull(otaUpgradeTask)) {
            throw BizException.wrap("OTA upgrade task does not exist");
        }
        OtaUpgradeTaskStatusEnum.fromValue(status)
                .orElseThrow(() -> BizException.wrap("Invalid task status"));

        otaUpgradeTask.setTaskStatus(status);
        return superManager.updateById(otaUpgradeTask);
    }

    /**
     * Delete OTA Upgrade Task
     *
     * @param id 涓婚敭
     * @return {@link Boolean} 杩斿洖缁撴灉
     */
    @Override
    public Boolean deleteOtaUpgradeTask(Long id) {
        ArgumentAssert.notNull(id, "Task ID cannot be null");

        OtaUpgradeTasks task = superManager.getById(id);
        if (Objects.isNull(task)) {
            throw new BizException("OTA upgrade task does not exist");
        }
        // 涓嶅厑璁稿垹闄ENDING銆両N_PROGRESS銆丆OMPLETED鐘舵€佺殑浠诲姟
        ArgumentAssert.isTrue(OtaUpgradeTaskStatusEnum.PENDING.getValue().equals(task.getTaskStatus()), "OTA upgrade task is pending, cannot be deleted");
        ArgumentAssert.isTrue(OtaUpgradeTaskStatusEnum.IN_PROGRESS.getValue().equals(task.getTaskStatus()), "OTA upgrade task is in progress, cannot be deleted");
        ArgumentAssert.isTrue(OtaUpgradeTaskStatusEnum.COMPLETED.getValue().equals(task.getTaskStatus()), "OTA upgrade task is completed, cannot be deleted");
        // Delete associated upgrade targets
        otaUpgradeTargetsService.deleteByTaskId(id);
        return superManager.removeById(id);
    }

    /**
     * Retrieves the details of an OTA upgrade task including the associated upgrade package information.
     * The method uses Optional to handle potential null values and ensure the task and its related upgrade
     * package information are correctly retrieved and set in the result object.
     *
     * @param taskId The unique identifier of the OTA upgrade task.
     * @return {@link OtaUpgradeTasksResultVO} The detailed information about the OTA upgrade task.
     */
    @Override
    public OtaUpgradeTasksResultVO getUpgradeTaskDetails(Long taskId) {
        ArgumentAssert.notNull(taskId, "Task ID cannot be null");
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;

        OtaUpgradeTasks otaUpgradeTask = superManager.getById(taskId);
        ArgumentAssert.notNull(otaUpgradeTask, "OTA upgrade task does not exist");

        OtaUpgradeTasksResultVO resultVO = BeanUtil.toBeanIgnoreError(otaUpgradeTask, OtaUpgradeTasksResultVO.class);

        OtaUpgrades otaUpgrade = otaUpgradesService.getById(otaUpgradeTask.getUpgradeId());
        ArgumentAssert.notNull(otaUpgrade, "Associated OTA upgrade package does not exist");
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;
        OtaUpgradesResultVO otaUpgradesResultVO = BeanUtil.toBeanIgnoreError(otaUpgrade, OtaUpgradesResultVO.class);
        resultVO.setOtaUpgradesResult(otaUpgradesResultVO);

        OtaUpgradeTargetsPageQuery otaUpgradeTargetsPageQuery = new OtaUpgradeTargetsPageQuery().setTaskId(taskId);
        List<OtaUpgradeTargetsResultDTO> otaUpgradeTargetsResultDTOList = otaUpgradeTargetsService.getOtaUpgradeTargetsResultDTOList(otaUpgradeTargetsPageQuery);
        resultVO.setTargetValueList(otaUpgradeTargetsResultDTOList.stream().map(OtaUpgradeTargetsResultDTO::getTargetValue).collect(Collectors.toList()));
        return resultVO;
    }

    @Override
    public List<OtaUpgradeTasksResultDTO> getUpgradeTaskDetailsList(OtaUpgradeTasksPageQuery query) {
        List<OtaUpgradeTasks> otaUpgradeTasksList = superManager.getOtaUpgradeTasksList(query);
        List<OtaUpgradeTasksResultDTO> otaUpgradeTasksResultDTOList = BeanUtil.toBeanList(otaUpgradeTasksList, OtaUpgradeTasksResultDTO.class);
        List<OtaUpgradesResultVO> otaUpgradesResultVOList = otaUpgradesService.selectListByIds(otaUpgradeTasksList.stream().map(OtaUpgradeTasks::getUpgradeId).distinct().collect(Collectors.toList()));
        Map<Long, OtaUpgradesResultVO> otaUpgradesResultVOMap = CollectionUtil.isNotEmpty(otaUpgradesResultVOList) ?
                otaUpgradesResultVOList.stream().collect(Collectors.toMap(OtaUpgradesResultVO::getId, Function.identity(), (existing, replacement) -> existing)) : Collections.emptyMap();
        otaUpgradeTasksResultDTOList.forEach(otaUpgradeTasksResultDTO -> {
            if (otaUpgradesResultVOMap.containsKey(otaUpgradeTasksResultDTO.getUpgradeId())) {
                otaUpgradeTasksResultDTO.setOtaUpgradesResult(BeanUtil.toBeanIgnoreError(otaUpgradesResultVOMap.get(otaUpgradeTasksResultDTO.getUpgradeId()), OtaUpgradesResultDTO.class));
            }
        });
        return otaUpgradeTasksResultDTOList;
    }

    /**
     * Save an OTA upgrade record from MQTT events.
     *
     * @param topoOtaCommandResponseParam The message body containing the OTA command response.
     * @return {@link TopoOtaCommandResponseParam} The saved OTA upgrade record.
     */
    @Override
    public TopoOtaCommandResponseParam saveOtaUpgradeRecordByMqtt(TopoOtaCommandResponseParam topoOtaCommandResponseParam) {
        return handleAndPersistOtaUpgradeRecord(topoOtaCommandResponseParam);
    }

    /**
     * Save an OTA upgrade record from HTTP events.
     *
     * @param topoOtaCommandResponseParam The message body containing the OTA command response.
     * @return {@link TopoOtaCommandResponseParam} The saved OTA upgrade record.
     */
    @Override
    public TopoOtaCommandResponseParam saveUpgradeRecordByNorthbound(TopoOtaCommandResponseParam topoOtaCommandResponseParam) {
        return handleAndPersistOtaUpgradeRecord(topoOtaCommandResponseParam);
    }

    /**
     * 閫氳繃MQTT浜嬩欢鎷夊彇OTA淇℃伅
     *
     * @param topoOtaPullParam 鎷夊彇OTA鍙傛暟
     * @return {@link TopoOtaPullResponseParam} OTA淇℃伅璁板綍
     */
    @Override
    public TopoOtaPullResponseParam otaPullByMqtt(TopoOtaPullParam topoOtaPullParam) {
        return handleOtaPull(topoOtaPullParam);
    }

    /**
     * 閫氳繃HTTP浜嬩欢鎷夊彇OTA淇℃伅
     *
     * @param topoOtaPullParam 鎷夊彇OTA鍙傛暟
     * @return {@link TopoOtaPullResponseParam} OTA淇℃伅璁板綍
     */
    @Override
    public TopoOtaPullResponseParam otaPullByNorthbound(TopoOtaPullParam topoOtaPullParam) {
        return handleOtaPull(topoOtaPullParam);
    }

    @Override
    public TopoOtaReportResponseParam otaReportByMqtt(TopoOtaReportParam topoOtaReportParam) {
        return handleOtaReport(topoOtaReportParam);
    }

    @Override
    public TopoOtaReportResponseParam otaReportByNorthbound(TopoOtaReportParam topoOtaReportParam) {
        return handleOtaReport(topoOtaReportParam);
    }

    private TopoOtaReportResponseParam handleOtaReport(TopoOtaReportParam topoOtaReportParam) {
        OtaPackageTypeEnum.fromValue(topoOtaReportParam.getPackageType()).orElseThrow(() -> BizException.wrap("Invalid package type"));
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;
        // Check if the device exists
        DeviceDetailsResultVO deviceDetailsResultVO = deviceService.findOneByDeviceIdentification(topoOtaReportParam.getDeviceIdentification());
        ArgumentAssert.notNull(deviceDetailsResultVO, "Device not found");

        // Update the device with the new version
        DeviceUpdateVO deviceUpdateVO = new DeviceUpdateVO();
        deviceUpdateVO.setId(deviceDetailsResultVO.getId());
        if (OtaPackageTypeEnum.FIRMWARE.getValue().equals(topoOtaReportParam.getPackageType())) {
            deviceUpdateVO.setFwVersion(topoOtaReportParam.getCurrentVersion());
        } else if (OtaPackageTypeEnum.SOFTWARE.getValue().equals(topoOtaReportParam.getPackageType())) {
            deviceUpdateVO.setSwVersion(topoOtaReportParam.getCurrentVersion());
        }
        deviceService.updateById(deviceUpdateVO);

        // hook B:璁惧涓婃姤鍥轰欢 / 杞欢鐗堟湰 鈫?鍙嶆煡瀵瑰簲鍗囩骇鍖呯殑鐩爣浜у搧鐗堟湰(褰卞瓙鐗堟湰)骞惰嚜鍔ㄥ垏鎹㈢粦瀹氱増鏈?
        otaModelVersionSwitcher.syncByReportedVersion(
                deviceDetailsResultVO.getProductIdentification(),
                deviceDetailsResultVO.getDeviceIdentification(),
                topoOtaReportParam.getCurrentVersion(),
                topoOtaReportParam.getPackageType());

        // Build OTA report response parameters
        return new TopoOtaReportResponseParam()
                .setDeviceIdentification(deviceDetailsResultVO.getDeviceIdentification())
                .setPackageType(topoOtaReportParam.getPackageType())
                .setCurrentVersion(topoOtaReportParam.getCurrentVersion());
    }

    @Override
    public void otaReadResponseByMqtt(TopoOtaReadResponseParam topoOtaReadResponseParam) {
        handleOtaResponse(topoOtaReadResponseParam);
    }

    @Override
    public void otaReadResponseByNorthbound(TopoOtaReadResponseParam topoOtaReadResponseParam) {
        handleOtaResponse(topoOtaReadResponseParam);
    }

    private void handleOtaResponse(TopoOtaReadResponseParam topoOtaReadResponseParam) {
        log.info("handle Ota  Response...request:{}", JSON.toJSONString(topoOtaReadResponseParam));
        OtaPackageTypeEnum.fromValue(topoOtaReadResponseParam.getPackageType()).orElseThrow(() -> BizException.wrap("Invalid package type"));
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;
        // Check if the device exists
        DeviceDetailsResultVO deviceDetailsResultVO = deviceService.findOneByDeviceIdentification(topoOtaReadResponseParam.getDeviceIdentification());
        ArgumentAssert.notNull(deviceDetailsResultVO, "Device not found");

        // Update the device with the new version
        DeviceUpdateVO deviceUpdateVO = new DeviceUpdateVO();
        deviceUpdateVO.setId(deviceDetailsResultVO.getId());
        if (OtaPackageTypeEnum.FIRMWARE.getValue().equals(topoOtaReadResponseParam.getPackageType())) {
            deviceUpdateVO.setFwVersion(topoOtaReadResponseParam.getCurrentVersion());
        } else if (OtaPackageTypeEnum.SOFTWARE.getValue().equals(topoOtaReadResponseParam.getPackageType())) {
            deviceUpdateVO.setSwVersion(topoOtaReadResponseParam.getCurrentVersion());
        }
        deviceService.updateById(deviceUpdateVO);

        // hook B:璁惧涓婃姤鍥轰欢 / 杞欢鐗堟湰 鈫?鍙嶆煡瀵瑰簲鍗囩骇鍖呯殑鐩爣浜у搧鐗堟湰(褰卞瓙鐗堟湰)骞惰嚜鍔ㄥ垏鎹㈢粦瀹氱増鏈?
        otaModelVersionSwitcher.syncByReportedVersion(
                deviceDetailsResultVO.getProductIdentification(),
                deviceDetailsResultVO.getDeviceIdentification(),
                topoOtaReadResponseParam.getCurrentVersion(),
                topoOtaReadResponseParam.getPackageType());
    }

    /**
     * pull OTA upgrade task
     * 浠庡崌绾ц褰曚腑鏌ユ壘鍖归厤鐨勫崌绾т换鍔?
     *
     * @param topoOtaPullParam pull OTA upgrade task param
     * @return {@link TopoOtaPullResponseParam} OTA upgrade task
     */
    private TopoOtaPullResponseParam handleOtaPull(TopoOtaPullParam topoOtaPullParam) {
        log.info("OTA pull request: {}", JSON.toJSONString(topoOtaPullParam));
        OtaPackageTypeEnum.fromValue(topoOtaPullParam.getPackageType()).orElseThrow(() -> BizException.wrap("Invalid package type"));
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;
        // Check if the device exists
        DeviceDetailsResultVO deviceDetailsResultVO = deviceService.findOneByDeviceIdentification(topoOtaPullParam.getDeviceIdentification());

        ArgumentAssert.notNull(deviceDetailsResultVO, "Device not found");

        // 鏌ヨ璇ヨ澶囩殑鍗囩骇璁板綍锛屾牴鎹?sourceVersion 鍖归厤 currentVersion
        OtaUpgradeRecordsPageQuery recordsQuery = new OtaUpgradeRecordsPageQuery()
                .setDeviceIdentification(topoOtaPullParam.getDeviceIdentification())
                .setSourceVersion(topoOtaPullParam.getCurrentVersion())
                .setTargetVersion(topoOtaPullParam.getRequestVersion())
                .setAppConfirmationStatusList(Arrays.asList(
                        OtaTaskRecordAppConfirmStatusEnum.NOT_REQUIRED.getValue(),
                        OtaTaskRecordAppConfirmStatusEnum.CONFIRMED.getValue()
                ));
        List<OtaUpgradeRecordsResultVO> upgradeRecords = otaUpgradeRecordsService.getOtaUpgradeRecordsResultVOList(recordsQuery);

        if (upgradeRecords.isEmpty()) {
            throw BizException.wrap("No OTA upgrade package found for the device");
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;
        }

        // 鑾峰彇鏈€鏂扮殑鍗囩骇璁板綍锛堟寜鍒涘缓鏃堕棿鎺掑簭锛?
        OtaUpgradeRecordsResultVO record = upgradeRecords.stream()
                .max(Comparator.comparing(OtaUpgradeRecordsResultVO::getCreatedTime, Comparator.nullsLast(Comparator.naturalOrder())))
                .orElseThrow(() -> BizException.wrap("No OTA upgrade package found for the device"));
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;

        // 鏍规嵁鍗囩骇璁板綍涓殑鍗囩骇鍖匢D鏌ヨ鍗囩骇鍖呬俊鎭?
        OtaUpgrades otaUpgrade = otaUpgradesService.getById(record.getUpgradeId());
        if (Objects.isNull(otaUpgrade)) {
            throw BizException.wrap("OTA upgrade package not found");
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;
        }
        OtaUpgradesResultDTO otaUpgradesResultDTO = BeanUtil.toBeanIgnoreError(otaUpgrade, OtaUpgradesResultDTO.class);

        // 楠岃瘉鍗囩骇鍖呯殑鍖呯被鍨嬫槸鍚﹀尮閰?
        if (!topoOtaPullParam.getPackageType().equals(otaUpgradesResultDTO.getPackageType())) {
            throw BizException.wrap("OTA upgrade package type mismatch");
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;
        }

        List<Long> fileIds = otaUpgradesResultDTO.getFileIds();

        Map<Long, OtaUpgradeFileResultDTO> fileInfoMap = getOtaUpgradeFileInfoMap(fileIds);

        // 鑾峰彇浠诲姟璇︽儏
        OtaUpgradeTasksResultVO taskDetails = this.getUpgradeTaskDetails(record.getTaskId());
        OtaUpgradeTasksResultDTO otaUpgradeTasksResultDTO = BeanUtil.toBeanIgnoreError(taskDetails, OtaUpgradeTasksResultDTO.class);

        return OtaUpgradeCommandConverter.buildOtaPullResponseParam(
                deviceDetailsResultVO.getDeviceIdentification(),
                otaUpgradeTasksResultDTO,
                otaUpgradesResultDTO,
                fileInfoMap);
    }

    /**
     * 鑾峰彇OTA鍗囩骇鏂囦欢淇℃伅闆嗗悎
     *
     * @param fileIds 鏂囦欢ID鍒楄〃
     * @return {@link Map<Long,OtaUpgradeFileResultDTO>} OTA鍗囩骇鏂囦欢淇℃伅闆嗗悎
     */
    private Map<Long, OtaUpgradeFileResultDTO> getOtaUpgradeFileInfoMap(List<Long> fileIds) {
        return otaUpgradeFileUtils.getOtaUpgradeFileInfoMap(fileIds);
    }

    /**
     * Handles and persists the OTA upgrade record to the database. This method abstracts the common logic for
     * processing and saving OTA upgrade command responses, regardless of the original communication protocol (MQTT, HTTP, etc.).
     *
     * @param topoOtaCommandResponseParam The response parameters from an OTA command.
     * @return {@link TopoOtaCommandResponseParam} The persisted OTA upgrade record with any updates made during processing.
     */
    private TopoOtaCommandResponseParam handleAndPersistOtaUpgradeRecord(TopoOtaCommandResponseParam topoOtaCommandResponseParam) {

        // Check if the device exists
        DeviceDetailsResultVO deviceDetailsResultVO = deviceService.findOneByDeviceIdentification(topoOtaCommandResponseParam.getDeviceIdentification());

        ArgumentAssert.notNull(deviceDetailsResultVO, "Device not found");

        // Check if the OTA task exists
        OtaUpgradeTasksResultVO otaTask = this.getUpgradeTaskDetails(topoOtaCommandResponseParam.getOtaTaskId());

        ArgumentAssert.notNull(otaTask, "OTA upgrade task not found");

        // Update device information if necessary
        updateDeviceInfo(deviceDetailsResultVO, otaTask, topoOtaCommandResponseParam);

        // hook A:璇ヨ澶囨湰娆″崌绾ф垚鍔?鈫?鐢ㄥ崌绾у寘閰嶇疆鐨勭洰鏍囦骇鍝佺増鏈?褰卞瓙鐗堟湰)鍒囨崲鍏剁粦瀹氱殑浜у搧鐗堟湰搴忓彿
        if (OtaUpgradeRecordStatusEnum.SUCCESS.getValue().equals(topoOtaCommandResponseParam.getUpgradeStatus())) {
            OtaUpgradesResultVO upgradePackage = otaTask.getOtaUpgradesResult();
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;
            if (upgradePackage != null) {
                otaModelVersionSwitcher.switchOnUpgradeSuccess(
                        deviceDetailsResultVO.getProductIdentification(),
                        deviceDetailsResultVO.getDeviceIdentification(),
                        upgradePackage.getProductVersionNo());
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;
            }
        }

        LocalDateTime startTime = Optional.ofNullable(topoOtaCommandResponseParam.getStartTime())
                .map(time -> Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime())
                .orElse(null);

        LocalDateTime endTime = Optional.ofNullable(topoOtaCommandResponseParam.getEndTime())
                .map(time -> Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime())
                .orElse(null);

        OtaUpgradeRecordsPageQuery query = new OtaUpgradeRecordsPageQuery()
                .setTaskId(topoOtaCommandResponseParam.getOtaTaskId())
                .setDeviceIdentification(topoOtaCommandResponseParam.getDeviceIdentification());
        Query params = new Query<>();
        params.setModel(query);

        Optional<OtaUpgradeRecords> existingRecordOpt = otaUpgradeRecordsService.getOtaUpgradeRecordsResultVOPage(params).getRecords().stream()
                .findFirst()
                .map(this::convertToOtaUpgradeRecordsDO);

        OtaUpgradeRecords record = existingRecordOpt.map(rec -> {
            updateOtaUpgradeRecordsDO(rec, topoOtaCommandResponseParam, startTime, endTime);

            return rec;
        }).orElseGet(() -> createNewOtaUpgradeRecordsDO(topoOtaCommandResponseParam, startTime, endTime));

        otaUpgradeRecordsService.getSuperManager().saveOrUpdate(record);

        return topoOtaCommandResponseParam;
    }

    private OtaUpgradeRecords convertToOtaUpgradeRecordsDO(OtaUpgradeRecordsResultVO otaUpgradeRecordsResultVO) {
        return BeanUtil.toBeanIgnoreError(otaUpgradeRecordsResultVO, OtaUpgradeRecords.class);
    }

    // Method to update an existing DO with response params
    private void updateOtaUpgradeRecordsDO(OtaUpgradeRecords rec, TopoOtaCommandResponseParam responseParam, LocalDateTime startTime, LocalDateTime endTime) {
        rec.setUpgradeStatus(responseParam.getUpgradeStatus());
        rec.setProgress(responseParam.getProgress());
        rec.setErrorCode(responseParam.getErrorCode());
        rec.setErrorMessage(responseParam.getErrorMessage());
        rec.setStartTime(startTime);
        rec.setEndTime(endTime);
        rec.setSuccessDetails(responseParam.getSuccessDetails());
        rec.setFailureDetails(responseParam.getFailureDetails());
        rec.setLogDetails(responseParam.getLogDetails());
    }

    // Method to create a new DO from response params
    private OtaUpgradeRecords createNewOtaUpgradeRecordsDO(TopoOtaCommandResponseParam responseParam, LocalDateTime startTime, LocalDateTime endTime) {
        return OtaUpgradeRecords.builder()
                .taskId(responseParam.getOtaTaskId())
                .deviceIdentification(responseParam.getDeviceIdentification())
                .upgradeStatus(responseParam.getUpgradeStatus())
                .progress(responseParam.getProgress())
                .errorCode(responseParam.getErrorCode())
                .errorMessage(responseParam.getErrorMessage())
                .startTime(startTime)
                .endTime(endTime)
                .successDetails(responseParam.getSuccessDetails())
                .failureDetails(responseParam.getFailureDetails())
                .logDetails(responseParam.getLogDetails())
                .build();
    }

    private void updateDeviceInfo(DeviceDetailsResultVO deviceDetailsResultVO, OtaUpgradeTasksResultVO otaTask, TopoOtaCommandResponseParam topoOtaCommandResponseParam) {
        DeviceUpdateVO deviceUpdateVO = new DeviceUpdateVO();
        deviceUpdateVO.setId(deviceDetailsResultVO.getId());
        OtaUpgradesResultVO otaUpgradesResultVO = otaTask.getOtaUpgradesResult();
        if (OtaPackageTypeEnum.FIRMWARE.getValue().equals(otaUpgradesResultVO.getPackageType())) {
            deviceUpdateVO.setFwVersion(otaUpgradesResultVO.getVersion());
        } else if (OtaPackageTypeEnum.SOFTWARE.getValue().equals(otaUpgradesResultVO.getPackageType())) {
            deviceUpdateVO.setSwVersion(otaUpgradesResultVO.getVersion());
        }
        deviceService.updateById(deviceUpdateVO);
    }

    private void validateOtaUpgradeTasksSaveVO(OtaUpgradeTasksSaveVO saveVO) {
        OtaUpgradeMethodEnum.fromValue(saveVO.getUpgradeMethod())
                .orElseThrow(() -> BizException.wrap("Invalid upgrade method"));

        OtaUpgradeScopeEnum.fromValue(saveVO.getUpgradeScope())
                .orElseThrow(() -> BizException.wrap("Invalid upgrade scope"));
    }

    private OtaUpgradeTasks buildOtaUpgradeTaskFromSaveVO(OtaUpgradeTasksSaveVO saveVO) {
        saveVO.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        return BeanUtil.toBeanIgnoreError(saveVO, OtaUpgradeTasks.class);
    }

    private void validateOtaUpgradeTasksUpdateVO(OtaUpgradeTasksUpdateVO updateVO) {
        OtaUpgrades otaUpgrade = otaUpgradesService.getById(updateVO.getUpgradeId());
        ArgumentAssert.notNull(otaUpgrade, "OTA upgrade can not be null");
    }

    private Builder<OtaUpgradeTasks> builderOtaUpgradeTasksUpdateVO(OtaUpgradeTasksUpdateVO updateVO) {
        return new OtaUpgradeTasks()
                .with(OtaUpgradeTasks::setUpgradeId, updateVO.getUpgradeId())
                .with(OtaUpgradeTasks::setTaskName, updateVO.getTaskName())
                .with(OtaUpgradeTasks::setScheduledStartTime, updateVO.getScheduledStartTime())
                .with(OtaUpgradeTasks::setScheduledEndTime, updateVO.getScheduledEndTime())
                .with(OtaUpgradeTasks::setMaxRetryCount, updateVO.getMaxRetryCount())
                .with(OtaUpgradeTasks::setUpgradeRate, updateVO.getUpgradeRate())
                .with(OtaUpgradeTasks::setRetryIntervalMinutes, updateVO.getRetryIntervalMinutes())
                .with(OtaUpgradeTasks::setDeviceUpgradeTimeout, updateVO.getDeviceUpgradeTimeout())
                .with(OtaUpgradeTasks::setDescription, updateVO.getDescription())
                .with(OtaUpgradeTasks::setRemark, updateVO.getRemark())
                .with(OtaUpgradeTasks::setCreatedOrgId, updateVO.getCreatedOrgId());
    }

    /**
     * 鏇存柊浠诲姟鐘舵€?
     *
     * @param taskId 浠诲姟ID
     * @param status 鐘舵€佹灇涓?
     * @return 鏄惁鏇存柊鎴愬姛
     */
    @Override
    public boolean updateTaskStatus(Long taskId, OtaUpgradeTaskStatusEnum status) {
        ArgumentAssert.notNull(taskId, "Task ID cannot be null");
        ArgumentAssert.notNull(status, "Status cannot be null");

        OtaUpgradeTasks otaUpgradeTask = superManager.getById(taskId);
        if (Objects.isNull(otaUpgradeTask)) {
            log.warn("OTA upgrade task not found - taskId: {}", taskId);
            return false;
        }

        otaUpgradeTask.setTaskStatus(status.getValue());
        boolean result = superManager.updateById(otaUpgradeTask);

        if (result) {
            log.info("Successfully updated task status - taskId: {}, status: {}", taskId, status);
        } else {
            log.error("Failed to update task status - taskId: {}, status: {}", taskId, status);
        }

        return result;
    }

    /**
     * 鏇存柊浠诲姟閲嶈瘯娆℃暟
     *
     * @param taskId     浠诲姟ID
     * @param retryCount 閲嶈瘯娆℃暟
     * @return 鏄惁鏇存柊鎴愬姛
     */
    @Override
    public boolean updateRetryCount(Long taskId, int retryCount) {
        ArgumentAssert.notNull(taskId, "Task ID cannot be null");
        ArgumentAssert.isTrue(retryCount >= 0, "Retry count cannot be negative");

        OtaUpgradeTasks otaUpgradeTask = superManager.getById(taskId);
        if (Objects.isNull(otaUpgradeTask)) {
            log.warn("OTA upgrade task not found - taskId: {}", taskId);
            return false;
        }

        otaUpgradeTask.setCurrentRetryCount(retryCount);
        boolean result = superManager.updateById(otaUpgradeTask);

        if (result) {
            log.info("Successfully updated task retry count - taskId: {}, retryCount: {}", taskId, retryCount);
        } else {
            log.error("Failed to update task retry count - taskId: {}, retryCount: {}", taskId, retryCount);
        }

        return result;
    }

    /**
     * 鏍规嵁ID闆嗗悎鏌ヨ浠诲姟淇℃伅
     *
     * @param ids 浠诲姟ID闆嗗悎
     * @return {@link List<OtaUpgradeTasksResultVO>} 浠诲姟淇℃伅鍒楄〃
     */
    @Override
    public List<OtaUpgradeTasksResultVO> selectListByIds(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<OtaUpgradeTasks> tasks = superManager.listByIds(ids);
        return Optional.ofNullable(tasks)
                .map(taskList -> BeanUtil.toBeanList(taskList, OtaUpgradeTasksResultVO.class))
                .orElse(Collections.emptyList());
    }

    /**
     * 鍖楀悜API鑾峰彇鍙崌绾х増鏈垪琛?
     *
     * @param deviceIdentification 璁惧鏍囪瘑
     * @param packageType          鍖呯被鍨?
     * @return {@link TopoOtaListUpgradeableVersionsResponseParam} 鍙崌绾х増鏈垪琛ㄥ搷搴?
     */
    @Override
    public TopoOtaListUpgradeableVersionsResponseParam getAvailableUpgradeVersionsByNorthbound(String deviceIdentification, Integer packageType) {
        log.info("getAvailableUpgradeVersionsByNorthbound - deviceIdentification: {}, packageType: {}", deviceIdentification, packageType);
        OtaPackageTypeEnum.fromValue(packageType).orElseThrow(() -> BizException.wrap("Invalid package type"));
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;

        DeviceDetailsResultVO deviceDetailsResultVO = deviceService.findOneByDeviceIdentification(deviceIdentification);
        ArgumentAssert.notNull(deviceDetailsResultVO, "Device not found");

        String currentVersion = (packageType.equals(OtaPackageTypeEnum.FIRMWARE.getValue()))
                ? deviceDetailsResultVO.getFwVersion()
                : deviceDetailsResultVO.getSwVersion();

        OtaUpgradeRecordsPageQuery recordsQuery = new OtaUpgradeRecordsPageQuery()
                .setDeviceIdentification(deviceIdentification)
                .setAppConfirmationStatusList(Arrays.asList(
                        OtaTaskRecordAppConfirmStatusEnum.NOT_REQUIRED.getValue(),
                        OtaTaskRecordAppConfirmStatusEnum.CONFIRMED.getValue()
                ));
        List<OtaUpgradeRecordsResultVO> upgradeRecords = otaUpgradeRecordsService.getOtaUpgradeRecordsResultVOList(recordsQuery);

        List<Long> upgradeIds = upgradeRecords.stream()
                .map(OtaUpgradeRecordsResultVO::getUpgradeId)
                .distinct()
                .collect(Collectors.toList());
        List<Long> taskIds = upgradeRecords.stream()
                .map(OtaUpgradeRecordsResultVO::getTaskId)
                .distinct()
                .collect(Collectors.toList());

        OtaUpgradesPageQuery upgradesQuery = new OtaUpgradesPageQuery()
                .setIds(upgradeIds)
                .setStatus(OtaPackageStatusEnum.ENABLE.getValue());
        List<OtaUpgradesResultDTO> otaUpgradesResultDTOList = otaUpgradesService.getOtaUpgradesResultDTOList(upgradesQuery);

        List<OtaUpgradeTasksResultVO> tasksResultVOList = selectListByIds(taskIds);

        Map<Long, OtaUpgradesResultDTO> otaUpgradesResultDTOMap = otaUpgradesResultDTOList.stream()
                .collect(Collectors.toMap(OtaUpgradesResultDTO::getId, v -> v));

        Map<Long, OtaUpgradeTasksResultVO> otaUpgradeTasksResultVOMap = tasksResultVOList.stream()
                .collect(Collectors.toMap(OtaUpgradeTasksResultVO::getId, v -> v));

        List<TopoOtaListUpgradeableVersionsResponseParam.UpgradeVersionInfo> upgradeVersionsList = upgradeRecords.stream()
                .sorted(Comparator.comparing(OtaUpgradeRecordsResultVO::getCreatedTime, Comparator.nullsFirst(Comparator.reverseOrder())))
                .map(record -> buildUpgradeVersionInfo(record, packageType, otaUpgradesResultDTOMap, otaUpgradeTasksResultVOMap))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        return TopoOtaListUpgradeableVersionsResponseParam.builder()
                .deviceIdentification(deviceDetailsResultVO.getDeviceIdentification())
                .productIdentification(deviceDetailsResultVO.getProductIdentification())
                .packageType(packageType)
                .currentVersion(currentVersion)
                .upgradeVersions(upgradeVersionsList)
                .build();
    }

    /**
     * 鏋勫缓鍙崌绾х増鏈俊鎭?
     *
     * @param record                     鍗囩骇璁板綍
     * @param packageType                鍖呯被鍨?
     * @param otaUpgradeMap              鍗囩骇鍖匨ap
     * @param otaUpgradeTasksResultVOMap 浠诲姟 Map
     * @return 鐗堟湰淇℃伅鐨凮ptional锛屽鏋滄瀯寤哄け璐ュ垯杩斿洖Optional.empty()
     */
    private Optional<TopoOtaListUpgradeableVersionsResponseParam.UpgradeVersionInfo> buildUpgradeVersionInfo(
            OtaUpgradeRecordsResultVO record,
            Integer packageType,
            Map<Long, OtaUpgradesResultDTO> otaUpgradeMap,
            Map<Long, OtaUpgradeTasksResultVO> otaUpgradeTasksResultVOMap) {
        try {
            OtaUpgradesResultDTO otaUpgradesResultDTO = otaUpgradeMap.get(record.getUpgradeId());
            if (Objects.isNull(otaUpgradesResultDTO)) {
                log.warn("OTA upgrade package not found for record: {}", record.getId());
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTasksServiceImpl.java.mapper.OtaUpgradeTasksMapper;
                return Optional.empty();
            }

            if (!packageType.equals(otaUpgradesResultDTO.getPackageType())) {
                return Optional.empty();
            }
            OtaUpgradeTasksResultVO otaUpgradeTasksResultVO = otaUpgradeTasksResultVOMap.get(record.getTaskId());
            if (otaUpgradeTasksResultVO == null) {
                log.warn("OTA upgrade task not found for record: {}", record.getId());
                return Optional.empty();
            }

            String sign = extractFileSign(otaUpgradesResultDTO);

            return Optional.of(TopoOtaListUpgradeableVersionsResponseParam.UpgradeVersionInfo.builder()
                    .otaTaskId(otaUpgradeTasksResultVO.getId())
                    .otaTaskName(otaUpgradeTasksResultVO.getTaskName())
                    .packageName(otaUpgradesResultDTO.getPackageName())
                    .version(otaUpgradesResultDTO.getVersion())
                    .fileLocation(otaUpgradesResultDTO.getFileLocation())
                    .description(otaUpgradesResultDTO.getDescription())
                    .customInfo(otaUpgradesResultDTO.getCustomInfo())
                    .signMethod(otaUpgradesResultDTO.getSignMethod())
                    .sign(sign)
                    .build());
        } catch (Exception e) {
            log.error("Failed to build upgrade version info for record: {}", record.getId(), e);
            return Optional.empty();
        }
    }

    /**
     * 鎻愬彇鏂囦欢绛惧悕淇℃伅
     *
     * @param otaUpgradesResultDTO OTA鍗囩骇鍖匘TO
     * @return 绛惧悕瀛楃涓诧紝濡傛灉鏃犳硶鑾峰彇鍒欒繑鍥炵┖瀛楃涓?
     */
    private String extractFileSign(OtaUpgradesResultDTO otaUpgradesResultDTO) {
        if (otaUpgradesResultDTO.getSignMethod() == null) {
            return StringPool.EMPTY;
        }

        List<Long> fileIds = otaUpgradesResultDTO.getFileIds();

        Map<Long, OtaUpgradeFileResultDTO> fileInfoMap = getOtaUpgradeFileInfoMap(fileIds);
        if (fileInfoMap.isEmpty()) {
            return StringPool.EMPTY;
        }

        OtaUpgradeFileResultDTO fileInfo = fileInfoMap.values().iterator().next();
        return OtaPackageSignMethodEnum.fromValue(otaUpgradesResultDTO.getSignMethod())
                .flatMap(fileInfo::getFileSign)
                .orElse(StringPool.EMPTY);
    }
}