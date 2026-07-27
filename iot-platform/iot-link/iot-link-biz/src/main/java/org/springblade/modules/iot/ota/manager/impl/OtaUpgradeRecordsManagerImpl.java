package org.springblade.modules.iot.ota.manager.impl;

import java.util.List;
import java.util.Optional;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.common.base.request.PageParams;
import org.springblade.common.database.mybatis.conditions.query.QueryWrap;
import org.springblade.modules.iot.ota.dto.OtaUpgradeRecordsSummaryResultDTO;
import org.springblade.modules.iot.ota.entity.OtaUpgradeRecords;
import org.springblade.modules.iot.ota.manager.OtaUpgradeRecordsManager;
import org.springblade.modules.iot.ota.mapper.OtaUpgradeRecordsMapper;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeRecordsPageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * OTA升级记录表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:42:04
 * @create [2024-01-12 22:42:04] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OtaUpgradeRecordsManagerImpl extends BaseServiceImpl<OtaUpgradeRecordsMapper, OtaUpgradeRecords> implements OtaUpgradeRecordsManager {

    private final OtaUpgradeRecordsMapper otaUpgradeRecordsMapper;

    @Override
    public IPage<OtaUpgradeRecords> getOtaUpgradeRecordsPage(PageParams<OtaUpgradeRecordsPageQuery> params) {
        IPage<OtaUpgradeRecords> page = params.buildPage(OtaUpgradeRecords.class);
        OtaUpgradeRecordsPageQuery paramsModel = params.getModel();

        // Construct query conditions
        QueryWrap<OtaUpgradeRecords> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(paramsModel.getId() != null, OtaUpgradeRecords::getId, paramsModel.getId());
        queryWrap.lambda().eq(paramsModel.getTaskId() != null, OtaUpgradeRecords::getTaskId, paramsModel.getTaskId());
        queryWrap.lambda().eq(StrUtil.isNotBlank(paramsModel.getDeviceIdentification()), OtaUpgradeRecords::getDeviceIdentification, paramsModel.getDeviceIdentification());
        queryWrap.lambda().in(CollUtil.isNotEmpty(paramsModel.getDeviceIdentificationList()), OtaUpgradeRecords::getDeviceIdentification, paramsModel.getDeviceIdentificationList());
        queryWrap.lambda().eq(StrUtil.isNotBlank(paramsModel.getSourceVersion()), OtaUpgradeRecords::getSourceVersion, paramsModel.getSourceVersion());
        queryWrap.lambda().eq(StrUtil.isNotBlank(paramsModel.getTargetVersion()), OtaUpgradeRecords::getTargetVersion, paramsModel.getTargetVersion());
        queryWrap.lambda().in(CollUtil.isNotEmpty(paramsModel.getAppConfirmationStatusList()), OtaUpgradeRecords::getAppConfirmationStatus, paramsModel.getAppConfirmationStatusList());
        queryWrap.lambda().eq(paramsModel.getUpgradeStatus() != null, OtaUpgradeRecords::getUpgradeStatus, paramsModel.getUpgradeStatus());
        queryWrap.lambda().eq(paramsModel.getProgress() != null, OtaUpgradeRecords::getProgress, paramsModel.getProgress());
        queryWrap.lambda().like(StrUtil.isNotBlank(paramsModel.getErrorCode()), OtaUpgradeRecords::getErrorCode, paramsModel.getErrorCode());
        queryWrap.lambda().like(StrUtil.isNotBlank(paramsModel.getErrorMessage()), OtaUpgradeRecords::getErrorMessage, paramsModel.getErrorMessage());
        queryWrap.lambda().le(paramsModel.getStartTime() != null, OtaUpgradeRecords::getStartTime, paramsModel.getStartTime());
        queryWrap.lambda().ge(paramsModel.getEndTime() != null, OtaUpgradeRecords::getEndTime, paramsModel.getEndTime());
        queryWrap.lambda().like(StrUtil.isNotBlank(paramsModel.getSuccessDetails()), OtaUpgradeRecords::getSuccessDetails, paramsModel.getSuccessDetails());
        queryWrap.lambda().like(StrUtil.isNotBlank(paramsModel.getFailureDetails()), OtaUpgradeRecords::getFailureDetails, paramsModel.getFailureDetails());
        queryWrap.lambda().like(StrUtil.isNotBlank(paramsModel.getLogDetails()), OtaUpgradeRecords::getLogDetails, paramsModel.getLogDetails());
        queryWrap.lambda().like(StrUtil.isNotBlank(paramsModel.getRemark()), OtaUpgradeRecords::getRemark, paramsModel.getRemark());
        queryWrap.lambda().eq(paramsModel.getCreatedOrgId() != null, OtaUpgradeRecords::getCreatedOrgId, paramsModel.getCreatedOrgId());
        return otaUpgradeRecordsMapper.selectPage(page, queryWrap);
    }

    @Override
    public List<OtaUpgradeRecords> getOtaUpgradeRecordsList(OtaUpgradeRecordsPageQuery query) {
        QueryWrap<OtaUpgradeRecords> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(query.getId() != null, OtaUpgradeRecords::getId, query.getId());
        queryWrap.lambda().eq(query.getTaskId() != null, OtaUpgradeRecords::getTaskId, query.getTaskId());
        queryWrap.lambda().eq(StrUtil.isNotBlank(query.getDeviceIdentification()), OtaUpgradeRecords::getDeviceIdentification, query.getDeviceIdentification());
        queryWrap.lambda().in(CollUtil.isNotEmpty(query.getDeviceIdentificationList()), OtaUpgradeRecords::getDeviceIdentification, query.getDeviceIdentificationList());
        queryWrap.lambda().eq(StrUtil.isNotBlank(query.getSourceVersion()), OtaUpgradeRecords::getSourceVersion, query.getSourceVersion());
        queryWrap.lambda().eq(StrUtil.isNotBlank(query.getTargetVersion()), OtaUpgradeRecords::getTargetVersion, query.getTargetVersion());
        queryWrap.lambda().in(CollUtil.isNotEmpty(query.getAppConfirmationStatusList()), OtaUpgradeRecords::getAppConfirmationStatus, query.getAppConfirmationStatusList());
        queryWrap.lambda().eq(query.getUpgradeStatus() != null, OtaUpgradeRecords::getUpgradeStatus, query.getUpgradeStatus());
        queryWrap.lambda().eq(query.getProgress() != null, OtaUpgradeRecords::getProgress, query.getProgress());
        queryWrap.lambda().like(StrUtil.isNotBlank(query.getErrorCode()), OtaUpgradeRecords::getErrorCode, query.getErrorCode());
        queryWrap.lambda().like(StrUtil.isNotBlank(query.getErrorMessage()), OtaUpgradeRecords::getErrorMessage, query.getErrorMessage());
        queryWrap.lambda().le(query.getStartTime() != null, OtaUpgradeRecords::getStartTime, query.getStartTime());
        queryWrap.lambda().ge(query.getEndTime() != null, OtaUpgradeRecords::getEndTime, query.getEndTime());
        queryWrap.lambda().like(StrUtil.isNotBlank(query.getSuccessDetails()), OtaUpgradeRecords::getSuccessDetails, query.getSuccessDetails());
        queryWrap.lambda().like(StrUtil.isNotBlank(query.getFailureDetails()), OtaUpgradeRecords::getFailureDetails, query.getFailureDetails());
        queryWrap.lambda().like(StrUtil.isNotBlank(query.getLogDetails()), OtaUpgradeRecords::getLogDetails, query.getLogDetails());
        queryWrap.lambda().like(StrUtil.isNotBlank(query.getRemark()), OtaUpgradeRecords::getRemark, query.getRemark());
        return otaUpgradeRecordsMapper.selectList(queryWrap);
    }

    @Override
    public Optional<OtaUpgradeRecords> getOtaUpgradeRecordsByTaskIdAndDeviceIdentification(Long taskId, String deviceIdentification) {
        QueryWrap<OtaUpgradeRecords> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(OtaUpgradeRecords::getTaskId, taskId);
        queryWrap.lambda().eq(OtaUpgradeRecords::getDeviceIdentification, deviceIdentification);
        return Optional.ofNullable(otaUpgradeRecordsMapper.selectOne(queryWrap));
    }


    @Override
    public OtaUpgradeRecordsSummaryResultDTO selectOtaUpgradeRecordsSummary(PageParams<OtaUpgradeRecordsPageQuery> params) {
        OtaUpgradeRecordsPageQuery paramsModel = params.getModel();
        LambdaQueryWrapper<OtaUpgradeRecords> wrapper = Wrappers.lambdaQuery();

        // 构建查询条件
        wrapper.eq(paramsModel.getTaskId() != null, OtaUpgradeRecords::getTaskId, paramsModel.getTaskId())
                .eq(StrUtil.isNotBlank(paramsModel.getDeviceIdentification()), OtaUpgradeRecords::getDeviceIdentification, paramsModel.getDeviceIdentification())
                .in(!CollUtil.isEmpty(paramsModel.getDeviceIdentificationList()), OtaUpgradeRecords::getDeviceIdentification, paramsModel.getDeviceIdentificationList())
                .eq(paramsModel.getUpgradeStatus() != null, OtaUpgradeRecords::getUpgradeStatus, paramsModel.getUpgradeStatus());

        return otaUpgradeRecordsMapper.selectOtaUpgradeRecordsSummary(wrapper);
    }

    @Override
    public void updateStatusByTaskId(Long taskId, Integer status) {
        if (taskId == null || status == null) {
            log.warn("任务ID或状态为空，无法更新升级记录状态");
            return;
        }

        OtaUpgradeRecords record = new OtaUpgradeRecords();
        record.setUpgradeStatus(status);

        LambdaQueryWrapper<OtaUpgradeRecords> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(OtaUpgradeRecords::getTaskId, taskId);

        int updated = otaUpgradeRecordsMapper.update(record, wrapper);
        log.info("根据任务ID更新升级记录状态成功 - 任务ID: {}, 状态: {}, 更新记录数: {}", taskId, status, updated);
    }

    @Override
    public void updateStatusByDeviceAndTask(Long taskId, String deviceIdentification, Integer status, String errorMessage) {
        if (taskId == null || deviceIdentification == null || status == null) {
            log.warn("任务ID、设备标识或状态为空，无法更新升级记录状态");
            return;
        }

        OtaUpgradeRecords record = new OtaUpgradeRecords();
        record.setUpgradeStatus(status);
        if (errorMessage != null) {
            record.setErrorMessage(errorMessage);
        }

        LambdaQueryWrapper<OtaUpgradeRecords> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(OtaUpgradeRecords::getTaskId, taskId)
                .eq(OtaUpgradeRecords::getDeviceIdentification, deviceIdentification);

        int updated = otaUpgradeRecordsMapper.update(record, wrapper);
        log.info("根据任务ID和设备标识更新升级记录状态成功 - 任务ID: {}, 设备: {}, 状态: {}, 更新记录数: {}", taskId, deviceIdentification, status, updated);
    }

    @Override
    public void updateProgressByDeviceAndTask(Long taskId, String deviceIdentification, int progress) {
        if (taskId == null || deviceIdentification == null) {
            log.warn("任务ID或设备标识为空，无法更新升级进度");
            return;
        }
        OtaUpgradeRecords record = new OtaUpgradeRecords();
        record.setProgress(progress);
        LambdaQueryWrapper<OtaUpgradeRecords> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(OtaUpgradeRecords::getTaskId, taskId)
                .eq(OtaUpgradeRecords::getDeviceIdentification, deviceIdentification);
        int updated = otaUpgradeRecordsMapper.update(record, wrapper);
        log.info("根据任务ID和设备标识更新升级进度成功 - 任务ID: {}, 设备: {}, 进度: {}%, 更新记录数: {}", taskId, deviceIdentification, progress, updated);
    }

    @Override
    public void updateAppConfirmationStatus(Long taskId, String deviceIdentification, Integer appConfirmationStatus) {
        if (taskId == null || deviceIdentification == null || appConfirmationStatus == null) {
            log.warn("任务ID、设备标识或APP确认状态为空，无法更新APP确认状态");
            return;
        }

        OtaUpgradeRecords record = new OtaUpgradeRecords();
        record.setAppConfirmationStatus(appConfirmationStatus);

        LambdaQueryWrapper<OtaUpgradeRecords> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(OtaUpgradeRecords::getTaskId, taskId)
                .eq(OtaUpgradeRecords::getDeviceIdentification, deviceIdentification);

        int updated = otaUpgradeRecordsMapper.update(record, wrapper);
        log.info("根据任务ID和设备标识更新APP确认状态成功 - 任务ID: {}, 设备: {}, 确认状态: {}, 更新记录数: {}", taskId, deviceIdentification, appConfirmationStatus, updated);
    }

    @Override
    public void updateCommandSendStatus(Long taskId, String deviceIdentification, Integer commandSendStatus, String errorMessage) {
        if (taskId == null || deviceIdentification == null || commandSendStatus == null) {
            log.warn("任务ID、设备标识或指令发送状态为空，无法更新指令发送状态");
            return;
        }

        OtaUpgradeRecords record = new OtaUpgradeRecords();
        record.setCommandSendStatus(commandSendStatus);
        record.setLastCommandSendTime(java.time.LocalDateTime.now());

        if (StrUtil.isNotBlank(errorMessage)) {
            record.setErrorMessage(errorMessage);
        }

        LambdaQueryWrapper<OtaUpgradeRecords> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(OtaUpgradeRecords::getTaskId, taskId)
                .eq(OtaUpgradeRecords::getDeviceIdentification, deviceIdentification);

        int updated = otaUpgradeRecordsMapper.update(record, wrapper);
        log.info("根据任务ID和设备标识更新指令发送状态成功 - 任务ID: {}, 设备: {}, 指令发送状态: {}, 更新记录数: {}", taskId, deviceIdentification, commandSendStatus, updated);
    }

}