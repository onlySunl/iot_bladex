package org.springblade.modules.iot.ota.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.boot.request.PageParam;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.common.converter.Builder;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.common.utils.ArgumentAssert;
import org.springblade.common.utils.BeanUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.ota.dto.OtaUpgradeRecordsSummaryResultDTO;
import org.springblade.modules.iot.ota.entity.OtaUpgradeRecords;
import org.springblade.modules.iot.ota.enumeration.OtaTaskRecordAppConfirmStatusEnum;
import org.springblade.modules.iot.ota.manager.OtaUpgradeRecordsManager;
import org.springblade.modules.iot.ota.service.OtaUpgradeRecordsService;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeRecordsPageQuery;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsResultVO;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsSummaryResultVO;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradeRecordsSaveVO;
import org.springblade.modules.iot.ota.vo.update.OtaUpgradeRecordsUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * OTA升级记录表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:42:04
 * @create [2024-01-12 22:42:04] [mqttsnet]
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
public class OtaUpgradeRecordsServiceImpl extends SuperServiceImpl<OtaUpgradeRecordsManager, Long, OtaUpgradeRecords> implements OtaUpgradeRecordsService {

    /**
     * Save a new OTA upgrade record.
     *
     * @param saveVO Record to be saved
     * @return Saved record
     */
    @Override
    public OtaUpgradeRecordsSaveVO saveOtaUpgradeRecord(OtaUpgradeRecordsSaveVO saveVO) {

        validateOtaUpgradeRecordsSaveVO(saveVO);

        // Validate and map the saveVO
        OtaUpgradeRecords record = buildOtaUpgradeRecordSaveVO(saveVO);
        superManager.save(record);
        return BeanPlusUtil.toBeanIgnoreError(record, OtaUpgradeRecordsSaveVO.class);
    }

    private void validateOtaUpgradeRecordsSaveVO(OtaUpgradeRecordsSaveVO saveVO) {

    }

    /**
     * Update an existing OTA upgrade record.
     *
     * @param updateVO Record to be updated
     * @return Updated record
     */
    @Override
    public OtaUpgradeRecordsUpdateVO updateOtaUpgradeRecord(OtaUpgradeRecordsUpdateVO updateVO) {

        validateOtaUpgradeRecordsUpdateVO(updateVO);

        // Validate and fetch existing record
        OtaUpgradeRecords existingRecord = superManager.getById(updateVO.getId());
        if (Objects.isNull(existingRecord)) {
            throw BizException.wrap("OTA upgrade record not found");
        }

        // Update the record
        Builder<OtaUpgradeRecords> recordBuilder = builderOtaUpgradeRecordsUpdateVO(updateVO);
        OtaUpgradeRecords updatedRecord = recordBuilder.with(OtaUpgradeRecords::setId, updateVO.getId()).build();
        superManager.updateById(updatedRecord);

        return BeanPlusUtil.toBeanIgnoreError(updatedRecord, OtaUpgradeRecordsUpdateVO.class);
    }

    /**
     * 获取OTA升级记录分页信息
     *
     * @param params 查询参数
     * @return {@link IPage < OtaUpgradeRecordsPageQuery >} OTA升级记录分页信息
     */
    @Override
    public IPage<OtaUpgradeRecordsResultVO> getOtaUpgradeRecordsResultVOPage(PageParams<OtaUpgradeRecordsPageQuery> params) {
        IPage<OtaUpgradeRecords> otaUpgradeRecordsPage = superManager.getOtaUpgradeRecordsPage(params);
        if (otaUpgradeRecordsPage.getRecords().isEmpty()) {
            return new Page<>();
        }
        Page<OtaUpgradeRecordsResultVO> resultPage = new Page<>(otaUpgradeRecordsPage.getCurrent(), otaUpgradeRecordsPage.getSize(), otaUpgradeRecordsPage.getTotal());
        resultPage.setRecords(BeanPlusUtil.toBeanList(otaUpgradeRecordsPage.getRecords(), OtaUpgradeRecordsResultVO.class));

        return resultPage;
    }

    /**
     * Converts OTA upgrade entities to view objects based on the given query.
     *
     * @param query The {@link OtaUpgradeRecordsPageQuery} object containing the search criteria.
     * @return A {@link List} of {@link OtaUpgradeRecordsResultVO} A list of OTA upgrade records that match the given query criteria.
     */
    @Override
    public List<OtaUpgradeRecordsResultVO> getOtaUpgradeRecordsResultVOList(OtaUpgradeRecordsPageQuery query) {
        List<OtaUpgradeRecords> otaUpgradesList = superManager.getOtaUpgradeRecordsList(query);
        return BeanPlusUtil.toBeanList(otaUpgradesList, OtaUpgradeRecordsResultVO.class);
    }

    @Override
    public OtaUpgradeRecordsSummaryResultVO getOtaUpgradeRecordsSummary(Long taskId) {
        PageParams<OtaUpgradeRecordsPageQuery> params = new PageParams<>();
        OtaUpgradeRecordsPageQuery query = new OtaUpgradeRecordsPageQuery()
                .setTaskId(taskId);
        params.setModel(query);
        OtaUpgradeRecordsSummaryResultDTO summaryDTO = superManager.selectOtaUpgradeRecordsSummary(params);
        return BeanPlusUtil.toBeanIgnoreError(summaryDTO, OtaUpgradeRecordsSummaryResultVO.class);
    }

    @Override
    public Optional<OtaUpgradeRecordsResultVO> getByTaskIdAndDeviceIdentification(Long taskId, String deviceIdentification) {
        // Implement the logic to fetch a specific OTA upgrade record by task ID and device identification
        Optional<OtaUpgradeRecords> otaUpgradeRecordsOptional = superManager.getOtaUpgradeRecordsByTaskIdAndDeviceIdentification(taskId, deviceIdentification);
        return otaUpgradeRecordsOptional.map(otaUpgradeRecords -> BeanPlusUtil.toBeanIgnoreError(otaUpgradeRecords, OtaUpgradeRecordsResultVO.class));
    }

    @Override
    public void updateStatusByTaskId(Long taskId, Integer status) {
        if (Objects.isNull(taskId) || Objects.isNull(status)) {
            log.warn("任务ID或状态为空，无法更新升级记录状态");
            return;
        }
        try {
            superManager.updateStatusByTaskId(taskId, status);
        } catch (Exception e) {
            log.error("根据任务ID更新升级记录状态异常 - 任务ID: {}, 状态: {}", taskId, status, e);
            throw new BizException("更新升级记录状态失败");
        }
    }

    @Override
    public void updateStatusByDeviceAndTask(Long taskId, String deviceIdentification, Integer status, String errorMessage) {
        if (Objects.isNull(taskId) || Objects.isNull(deviceIdentification) || Objects.isNull(status)) {
            log.warn("任务ID、设备标识或状态为空，无法更新升级记录状态");
            return;
        }

        try {
            superManager.updateStatusByDeviceAndTask(taskId, deviceIdentification, status, errorMessage);
        } catch (Exception e) {
            log.error("根据任务ID和设备标识更新升级记录状态异常 - 任务ID: {}, 设备: {}, 状态: {}",
                    taskId, deviceIdentification, status, e);
            throw new BizException("更新升级记录状态失败");
        }
    }

    @Override
    public void updateProgressByDeviceAndTask(Long taskId, String deviceIdentification, int progress) {
        if (Objects.isNull(taskId) || Objects.isNull(deviceIdentification)) {
            log.warn("任务ID或设备标识为空，无法更新升级进度");
            return;
        }

        try {
            superManager.updateProgressByDeviceAndTask(taskId, deviceIdentification, progress);
        } catch (Exception e) {
            log.error("根据任务ID和设备标识更新升级进度异常 - 任务ID: {}, 设备: {}, 进度: {}%",
                    taskId, deviceIdentification, progress, e);
            throw new BizException("更新升级进度失败");
        }
    }

    @Override
    public List<OtaUpgradeRecordsResultVO> getRecordsByTaskId(Long taskId) {
        if (Objects.isNull(taskId)) {
            log.warn("任务ID为空，无法获取升级记录列表");
            return Collections.emptyList();
        }
        try {
            OtaUpgradeRecordsPageQuery query = new OtaUpgradeRecordsPageQuery();
            query.setTaskId(taskId);
            List<OtaUpgradeRecords> records = superManager.getOtaUpgradeRecordsList(query);
            if (Objects.isNull(records) || records.isEmpty()) {
                return Collections.emptyList();
            }

            return records.stream()
                    .map(record -> BeanPlusUtil.toBeanIgnoreError(record, OtaUpgradeRecordsResultVO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("根据任务ID获取升级记录列表异常 - 任务ID: {}", taskId, e);
            throw new BizException("获取升级记录列表失败");
        }
    }

    @Override
    public List<String> getProcessedDevicesByTaskId(Long taskId) {
        if (Objects.isNull(taskId)) {
            log.warn("任务ID为空，无法获取已处理设备列表");
            return Collections.emptyList();
        }

        try {
            OtaUpgradeRecordsPageQuery query = new OtaUpgradeRecordsPageQuery();
            query.setTaskId(taskId);

            List<OtaUpgradeRecords> records = superManager.getOtaUpgradeRecordsList(query);
            if (Objects.isNull(records) || records.isEmpty()) {
                return Collections.emptyList();
            }

            // 提取所有设备的标识
            return records.stream()
                    .map(OtaUpgradeRecords::getDeviceIdentification)
                    .filter(Objects::nonNull)
                    .distinct()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("根据任务ID获取已处理设备列表异常 - 任务ID: {}", taskId, e);
            throw new BizException("获取已处理设备列表失败");
        }
    }

    @Override
    public void updateAppConfirmationStatus(Long taskId, String deviceIdentification, OtaTaskRecordAppConfirmStatusEnum appConfirmationStatusEnum) {
        if (Objects.isNull(taskId) || Objects.isNull(deviceIdentification) || Objects.isNull(appConfirmationStatusEnum)) {
            log.warn("任务ID、设备标识或APP确认状态为空，无法更新APP确认状态");
            return;
        }
        try {
            superManager.updateAppConfirmationStatus(taskId, deviceIdentification, appConfirmationStatusEnum.getValue());
            log.info("根据任务ID和设备标识更新APP确认状态成功 - 任务ID: {}, 设备: {}, 确认状态: {}", taskId, deviceIdentification, appConfirmationStatusEnum.getDesc());
        } catch (Exception e) {
            log.error("根据任务ID和设备标识更新APP确认状态异常 - 任务ID: {}, 设备: {}, 确认状态: {}", taskId, deviceIdentification, appConfirmationStatusEnum.getDesc(), e);
            throw new BizException("更新APP确认状态失败");
        }
    }

    @Override
    public void updateCommandSendStatus(Long taskId, String deviceIdentification, Integer commandSendStatus, String errorMessage) {
        if (Objects.isNull(taskId) || Objects.isNull(deviceIdentification) || Objects.isNull(commandSendStatus)) {
            log.warn("任务ID、设备标识或指令发送状态为空，无法更新指令发送状态");
            return;
        }

        try {
            superManager.updateCommandSendStatus(taskId, deviceIdentification, commandSendStatus, errorMessage);
            log.info("根据任务ID和设备标识更新指令发送状态成功 - 任务ID: {}, 设备: {}, 指令发送状态: {}", taskId, deviceIdentification, commandSendStatus);
        } catch (Exception e) {
            log.error("根据任务ID和设备标识更新指令发送状态异常 - 任务ID: {}, 设备: {}, 指令发送状态: {}", taskId, deviceIdentification, commandSendStatus, e);
            throw new BizException("更新指令发送状态失败");
        }
    }

    private void validateOtaUpgradeRecordsUpdateVO(OtaUpgradeRecordsUpdateVO updateVO) {


    }

    private OtaUpgradeRecords buildOtaUpgradeRecordSaveVO(OtaUpgradeRecordsSaveVO saveVO) {
        saveVO.setCreatedOrgId(ContextUtil.getCurrentDeptId());
        return BeanPlusUtil.toBeanIgnoreError(saveVO, OtaUpgradeRecords.class);
    }

    private Builder<OtaUpgradeRecords> builderOtaUpgradeRecordsUpdateVO(OtaUpgradeRecordsUpdateVO updateVO) {
        return Builder.of(OtaUpgradeRecords::new)
                .with(OtaUpgradeRecords::setId, updateVO.getId())
                .with(OtaUpgradeRecords::setUpgradeId, updateVO.getUpgradeId())
                .with(OtaUpgradeRecords::setTaskId, updateVO.getTaskId())
                .with(OtaUpgradeRecords::setDeviceIdentification, updateVO.getDeviceIdentification())
                .with(OtaUpgradeRecords::setUpgradeStatus, updateVO.getUpgradeStatus())
                .with(OtaUpgradeRecords::setProgress, updateVO.getProgress())
                .with(OtaUpgradeRecords::setErrorCode, updateVO.getErrorCode())
                .with(OtaUpgradeRecords::setErrorMessage, updateVO.getErrorMessage())
                .with(OtaUpgradeRecords::setStartTime, updateVO.getStartTime())
                .with(OtaUpgradeRecords::setEndTime, updateVO.getEndTime())
                .with(OtaUpgradeRecords::setSuccessDetails, updateVO.getSuccessDetails())
                .with(OtaUpgradeRecords::setFailureDetails, updateVO.getFailureDetails())
                .with(OtaUpgradeRecords::setRemark, updateVO.getRemark())
                .with(OtaUpgradeRecords::setAppConfirmationStatus, updateVO.getAppConfirmationStatus())
                .with(OtaUpgradeRecords::setAppConfirmationTime, updateVO.getAppConfirmationTime())
                .with(OtaUpgradeRecords::setCommandSendStatus, updateVO.getCommandSendStatus())
                .with(OtaUpgradeRecords::setLastCommandSendTime, updateVO.getLastCommandSendTime())
                .with(OtaUpgradeRecords::setCommandContent, updateVO.getCommandContent());

    }

    @Override
    public OtaUpgradeRecordsResultVO getUpgradeRecordDetails(Long id) {
        ArgumentAssert.notNull(id, "Upgrade record ID cannot be null");
        OtaUpgradeRecords record = superManager.getById(id);
        ArgumentAssert.notNull(record, "Upgrade record not found");
        return BeanPlusUtil.toBeanIgnoreError(record, OtaUpgradeRecordsResultVO.class);
    }

}