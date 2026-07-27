package org.springblade.modules.iot.ota.service;

import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.boot.request.PageParam;
import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.ota.entity.OtaUpgradeRecords;
import org.springblade.modules.iot.ota.enumeration.OtaTaskRecordAppConfirmStatusEnum;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeRecordsPageQuery;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsResultVO;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeRecordsSummaryResultVO;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradeRecordsSaveVO;
import org.springblade.modules.iot.ota.vo.update.OtaUpgradeRecordsUpdateVO;


/**
 * <p>
 * 业务接口
 * OTA升级记录表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:42:04
 * @create [2024-01-12 22:42:04] [mqttsnet]
 */
public interface OtaUpgradeRecordsService extends SuperService<Long, OtaUpgradeRecords> {


    /**
     * 保存新的OTA升级记录。
     *
     * @param saveVO 要保存的记录
     * @return 已保存的记录
     */
    OtaUpgradeRecordsSaveVO saveOtaUpgradeRecord(OtaUpgradeRecordsSaveVO saveVO);

    /**
     * 更新现有的OTA升级记录。
     *
     * @param updateVO 要更新的记录
     * @return 更新后的记录
     */
    OtaUpgradeRecordsUpdateVO updateOtaUpgradeRecord(OtaUpgradeRecordsUpdateVO updateVO);

    /**
     * 获取OTA升级记录分页信息
     *
     * @param params 查询参数
     * @return {@link IPage < OtaUpgradeRecordsPageQuery >} OTA升级记录分页信息
     */
    IPage<OtaUpgradeRecordsResultVO> getOtaUpgradeRecordsResultVOPage(PageParams<OtaUpgradeRecordsPageQuery> params);


    /**
     * Converts OTA upgrade entities to view objects based on the given query.
     *
     * @param query The {@link OtaUpgradeRecordsPageQuery} object containing the search criteria.
     * @return A {@link List} of {@link OtaUpgradeRecordsResultVO} A list of OTA upgrade records that match the given query criteria.
     */
    List<OtaUpgradeRecordsResultVO> getOtaUpgradeRecordsResultVOList(OtaUpgradeRecordsPageQuery query);


    /**
     * Get the OTA upgrade record summary information.
     *
     * @param taskId The task ID.
     * @return {@link OtaUpgradeRecordsSummaryResultVO} The OTA upgrade record summary information.
     */
    OtaUpgradeRecordsSummaryResultVO getOtaUpgradeRecordsSummary(Long taskId);


    /**
     * Get the OTA upgrade record information by task ID and device identification.
     *
     * @param taskId               The task ID.
     * @param deviceIdentification The device identification.
     * @return {@link Optional<OtaUpgradeRecordsResultVO>} The OTA upgrade record information.
     */
    Optional<OtaUpgradeRecordsResultVO> getByTaskIdAndDeviceIdentification(Long taskId, String deviceIdentification);

    /**
     * 根据任务ID更新升级记录状态
     *
     * @param taskId 任务ID
     * @param status 状态值
     */
    void updateStatusByTaskId(Long taskId, Integer status);

    /**
     * 根据任务ID和设备标识更新升级记录状态
     *
     * @param taskId               任务ID
     * @param deviceIdentification 设备标识
     * @param status               状态值
     * @param errorMessage         错误信息
     */
    void updateStatusByDeviceAndTask(Long taskId, String deviceIdentification, Integer status, String errorMessage);

    /**
     * 根据任务ID和设备标识更新升级进度
     *
     * @param taskId               任务ID
     * @param deviceIdentification 设备标识
     * @param progress             进度值
     */
    void updateProgressByDeviceAndTask(Long taskId, String deviceIdentification, int progress);

    /**
     * 根据任务ID获取升级记录列表
     *
     * @param taskId 任务ID
     * @return 升级记录列表
     */
    List<OtaUpgradeRecordsResultVO> getRecordsByTaskId(Long taskId);

    /**
     * 根据任务ID获取已处理的设备标识列表
     *
     * @param taskId 任务ID
     * @return {@link List<String>} 已处理的设备标识列表
     */
    List<String> getProcessedDevicesByTaskId(Long taskId);

    /**
     * 根据任务ID和设备标识更新APP确认状态
     *
     * @param taskId               任务ID
     * @param deviceIdentification 设备标识
     * @param appConfirmStatusEnum APP确认状态
     */
    void updateAppConfirmationStatus(Long taskId, String deviceIdentification, OtaTaskRecordAppConfirmStatusEnum appConfirmStatusEnum);

    /**
     * 根据任务ID和设备标识更新指令发送状态
     *
     * @param taskId               任务ID
     * @param deviceIdentification 设备标识
     * @param commandSendStatus    指令发送状态
     * @param errorMessage         错误信息
     */
    void updateCommandSendStatus(Long taskId, String deviceIdentification, Integer commandSendStatus, String errorMessage);

    /**
     * 根据ID获取OTA升级记录详情
     *
     * @param id 升级记录ID
     * @return OTA升级记录详情，包含关联的任务信息和升级包信息
     */
    OtaUpgradeRecordsResultVO getUpgradeRecordDetails(Long id);
}