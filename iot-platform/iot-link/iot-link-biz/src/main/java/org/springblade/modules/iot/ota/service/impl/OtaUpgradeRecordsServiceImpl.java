package org.springblade.modules.iot.ota.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeRecordsServiceImpl.java.mapper.OtaUpgradeRecordsMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.mp.support.Query;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.ota.dto.OtaUpgradeRecordsSummaryResultDTO;
import org.springblade.modules.iot.ota.entity.OtaUpgradeRecords;
import org.springblade.modules.iot.ota.enumeration.OtaTaskRecordAppConfirmStatusEnum;
import org.springblade.modules.iot.ota.service.OtaUpgradeRecordsService;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeRecordsPageQuery;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsResultVO;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsSummaryResultVO;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradeRecordsSaveVO;
import org.springblade.modules.iot.ota.vo.update.OtaUpgradeRecordsUpdateVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 涓氬姟瀹炵幇绫?
 * OTA鍗囩骇璁板綍琛?
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:42:04
 * @create [2024-01-12 22:42:04] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Service
public class OtaUpgradeRecordsServiceImpl extends BaseServiceImpl<OtaUpgradeRecordsMapper, OtaUpgradeRecords> implements OtaUpgradeRecordsService {

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
        return BeanUtil.toBeanIgnoreError(record, OtaUpgradeRecordsSaveVO.class);
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

        return BeanUtil.toBeanIgnoreError(updatedRecord, OtaUpgradeRecordsUpdateVO.class);
    }

    /**
     * 鑾峰彇OTA鍗囩骇璁板綍鍒嗛〉淇℃伅
     *
     * @param params 鏌ヨ鍙傛暟
     * @return {@link IPage < OtaUpgradeRecordsPageQuery >} OTA鍗囩骇璁板綍鍒嗛〉淇℃伅
     */
    @Override
    public IPage<OtaUpgradeRecordsResultVO> getOtaUpgradeRecordsResultVOPage(Query params) {
        IPage<OtaUpgradeRecords> otaUpgradeRecordsPage = superManager.getOtaUpgradeRecordsPage(params);
        if (otaUpgradeRecordsPage.getRecords().isEmpty()) {
            return new Page<>();
        }
        Page<OtaUpgradeRecordsResultVO> resultPage = new Page<>(otaUpgradeRecordsPage.getCurrent(), otaUpgradeRecordsPage.getSize(), otaUpgradeRecordsPage.getTotal());
        resultPage.setRecords(BeanUtil.toBeanList(otaUpgradeRecordsPage.getRecords(), OtaUpgradeRecordsResultVO.class));

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
        return BeanUtil.toBeanList(otaUpgradesList, OtaUpgradeRecordsResultVO.class);
    }

    @Override
    public OtaUpgradeRecordsSummaryResultVO getOtaUpgradeRecordsSummary(Long taskId) {
        Query params = new Query<>();
        OtaUpgradeRecordsPageQuery query = new OtaUpgradeRecordsPageQuery()
                .setTaskId(taskId);
        params.setModel(query);
        OtaUpgradeRecordsSummaryResultDTO summaryDTO = superManager.selectOtaUpgradeRecordsSummary(params);
        return BeanUtil.toBeanIgnoreError(summaryDTO, OtaUpgradeRecordsSummaryResultVO.class);
    }

    @Override
    public Optional<OtaUpgradeRecordsResultVO> getByTaskIdAndDeviceIdentification(Long taskId, String deviceIdentification) {
        // Implement the logic to fetch a specific OTA upgrade record by task ID and device identification
        Optional<OtaUpgradeRecords> otaUpgradeRecordsOptional = superManager.getOtaUpgradeRecordsByTaskIdAndDeviceIdentification(taskId, deviceIdentification);
        return otaUpgradeRecordsOptional.map(otaUpgradeRecords -> BeanUtil.toBeanIgnoreError(otaUpgradeRecords, OtaUpgradeRecordsResultVO.class));
    }

    @Override
    public void updateStatusByTaskId(Long taskId, Integer status) {
        if (Objects.isNull(taskId) || Objects.isNull(status)) {
            log.warn("浠诲姟ID鎴栫姸鎬佷负绌猴紝鏃犳硶鏇存柊鍗囩骇璁板綍鐘舵€?);
            return;
        }
        try {
            superManager.updateStatusByTaskId(taskId, status);
        } catch (Exception e) {
            log.error("鏍规嵁浠诲姟ID鏇存柊鍗囩骇璁板綍鐘舵€佸紓甯?- 浠诲姟ID: {}, 鐘舵€? {}", taskId, status, e);
            throw new BizException("鏇存柊鍗囩骇璁板綍鐘舵€佸け璐?);
        }
    }

    @Override
    public void updateStatusByDeviceAndTask(Long taskId, String deviceIdentification, Integer status, String errorMessage) {
        if (Objects.isNull(taskId) || Objects.isNull(deviceIdentification) || Objects.isNull(status)) {
            log.warn("浠诲姟ID銆佽澶囨爣璇嗘垨鐘舵€佷负绌猴紝鏃犳硶鏇存柊鍗囩骇璁板綍鐘舵€?);
            return;
        }

        try {
            superManager.updateStatusByDeviceAndTask(taskId, deviceIdentification, status, errorMessage);
        } catch (Exception e) {
            log.error("鏍规嵁浠诲姟ID鍜岃澶囨爣璇嗘洿鏂板崌绾ц褰曠姸鎬佸紓甯?- 浠诲姟ID: {}, 璁惧: {}, 鐘舵€? {}",
                    taskId, deviceIdentification, status, e);
            throw new BizException("鏇存柊鍗囩骇璁板綍鐘舵€佸け璐?);
        }
    }

    @Override
    public void updateProgressByDeviceAndTask(Long taskId, String deviceIdentification, int progress) {
        if (Objects.isNull(taskId) || Objects.isNull(deviceIdentification)) {
            log.warn("浠诲姟ID鎴栬澶囨爣璇嗕负绌猴紝鏃犳硶鏇存柊鍗囩骇杩涘害");
            return;
        }

        try {
            superManager.updateProgressByDeviceAndTask(taskId, deviceIdentification, progress);
        } catch (Exception e) {
            log.error("鏍规嵁浠诲姟ID鍜岃澶囨爣璇嗘洿鏂板崌绾ц繘搴﹀紓甯?- 浠诲姟ID: {}, 璁惧: {}, 杩涘害: {}%",
                    taskId, deviceIdentification, progress, e);
            throw new BizException("鏇存柊鍗囩骇杩涘害澶辫触");
        }
    }

    @Override
    public List<OtaUpgradeRecordsResultVO> getRecordsByTaskId(Long taskId) {
        if (Objects.isNull(taskId)) {
            log.warn("浠诲姟ID涓虹┖锛屾棤娉曡幏鍙栧崌绾ц褰曞垪琛?);
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
                    .map(record -> BeanUtil.toBeanIgnoreError(record, OtaUpgradeRecordsResultVO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("鏍规嵁浠诲姟ID鑾峰彇鍗囩骇璁板綍鍒楄〃寮傚父 - 浠诲姟ID: {}", taskId, e);
            throw new BizException("鑾峰彇鍗囩骇璁板綍鍒楄〃澶辫触");
        }
    }

    @Override
    public List<String> getProcessedDevicesByTaskId(Long taskId) {
        if (Objects.isNull(taskId)) {
            log.warn("浠诲姟ID涓虹┖锛屾棤娉曡幏鍙栧凡澶勭悊璁惧鍒楄〃");
            return Collections.emptyList();
        }

        try {
            OtaUpgradeRecordsPageQuery query = new OtaUpgradeRecordsPageQuery();
            query.setTaskId(taskId);

            List<OtaUpgradeRecords> records = superManager.getOtaUpgradeRecordsList(query);
            if (Objects.isNull(records) || records.isEmpty()) {
                return Collections.emptyList();
            }

            // 鎻愬彇鎵€鏈夎澶囩殑鏍囪瘑
            return records.stream()
                    .map(OtaUpgradeRecords::getDeviceIdentification)
                    .filter(Objects::nonNull)
                    .distinct()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("鏍规嵁浠诲姟ID鑾峰彇宸插鐞嗚澶囧垪琛ㄥ紓甯?- 浠诲姟ID: {}", taskId, e);
            throw new BizException("鑾峰彇宸插鐞嗚澶囧垪琛ㄥけ璐?);
        }
    }

    @Override
    public void updateAppConfirmationStatus(Long taskId, String deviceIdentification, OtaTaskRecordAppConfirmStatusEnum appConfirmationStatusEnum) {
        if (Objects.isNull(taskId) || Objects.isNull(deviceIdentification) || Objects.isNull(appConfirmationStatusEnum)) {
            log.warn("浠诲姟ID銆佽澶囨爣璇嗘垨APP纭鐘舵€佷负绌猴紝鏃犳硶鏇存柊APP纭鐘舵€?);
            return;
        }
        try {
            superManager.updateAppConfirmationStatus(taskId, deviceIdentification, appConfirmationStatusEnum.getValue());
            log.info("鏍规嵁浠诲姟ID鍜岃澶囨爣璇嗘洿鏂癆PP纭鐘舵€佹垚鍔?- 浠诲姟ID: {}, 璁惧: {}, 纭鐘舵€? {}", taskId, deviceIdentification, appConfirmationStatusEnum.getDesc());
        } catch (Exception e) {
            log.error("鏍规嵁浠诲姟ID鍜岃澶囨爣璇嗘洿鏂癆PP纭鐘舵€佸紓甯?- 浠诲姟ID: {}, 璁惧: {}, 纭鐘舵€? {}", taskId, deviceIdentification, appConfirmationStatusEnum.getDesc(), e);
            throw new BizException("鏇存柊APP纭鐘舵€佸け璐?);
        }
    }

    @Override
    public void updateCommandSendStatus(Long taskId, String deviceIdentification, Integer commandSendStatus, String errorMessage) {
        if (Objects.isNull(taskId) || Objects.isNull(deviceIdentification) || Objects.isNull(commandSendStatus)) {
            log.warn("浠诲姟ID銆佽澶囨爣璇嗘垨鎸囦护鍙戦€佺姸鎬佷负绌猴紝鏃犳硶鏇存柊鎸囦护鍙戦€佺姸鎬?);
            return;
        }

        try {
            superManager.updateCommandSendStatus(taskId, deviceIdentification, commandSendStatus, errorMessage);
            log.info("鏍规嵁浠诲姟ID鍜岃澶囨爣璇嗘洿鏂版寚浠ゅ彂閫佺姸鎬佹垚鍔?- 浠诲姟ID: {}, 璁惧: {}, 鎸囦护鍙戦€佺姸鎬? {}", taskId, deviceIdentification, commandSendStatus);
        } catch (Exception e) {
            log.error("鏍规嵁浠诲姟ID鍜岃澶囨爣璇嗘洿鏂版寚浠ゅ彂閫佺姸鎬佸紓甯?- 浠诲姟ID: {}, 璁惧: {}, 鎸囦护鍙戦€佺姸鎬? {}", taskId, deviceIdentification, commandSendStatus, e);
            throw new BizException("鏇存柊鎸囦护鍙戦€佺姸鎬佸け璐?);
        }
    }

    private void validateOtaUpgradeRecordsUpdateVO(OtaUpgradeRecordsUpdateVO updateVO) {

    }

    private OtaUpgradeRecords buildOtaUpgradeRecordSaveVO(OtaUpgradeRecordsSaveVO saveVO) {
        saveVO.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        return BeanUtil.toBeanIgnoreError(saveVO, OtaUpgradeRecords.class);
    }

    private Builder<OtaUpgradeRecords> builderOtaUpgradeRecordsUpdateVO(OtaUpgradeRecordsUpdateVO updateVO) {
        return new OtaUpgradeRecords()
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
        return BeanUtil.toBeanIgnoreError(record, OtaUpgradeRecordsResultVO.class);
    }

}