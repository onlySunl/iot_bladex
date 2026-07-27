package org.springblade.modules.iot.ota.manager;

import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.core.boot.request.PageParam;
import org.springblade.modules.iot.ota.dto.OtaUpgradeRecordsSummaryResultDTO;
import org.springblade.modules.iot.ota.entity.OtaUpgradeRecords;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeRecordsPageQuery;

/**
 * <p>
 * 通用业务接口
 * OTA升级记录表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:42:04
 * @create [2024-01-12 22:42:04] [mqttsnet]
 */
public interface OtaUpgradeRecordsManager extends BaseService<OtaUpgradeRecords> {

    /**
     * Queries the paginated details of OTA upgrade records.
     *
     * @param params Pagination parameters
     * @return {@link IPage <OtaUpgradeRecords>} Paginated details of OTA upgrade records
     */
    IPage<OtaUpgradeRecords> getOtaUpgradeRecordsPage(PageParams<OtaUpgradeRecordsPageQuery> params);

    /**
     * Retrieves a list of OTA (Over-The-Air) upgrade records based on the specified query criteria.
     * This method supports filtering by a single ID or multiple IDs.
     *
     * @param query The {@link OtaUpgradeRecordsPageQuery} object containing the search criteria,
     *              which may include a single ID or multiple IDs for OTA upgrades.
     * @return A {@link List} of {@link OtaUpgradeRecords} that match the given query criteria. Returns an empty list
     * if no records match the criteria.
     */
    List<OtaUpgradeRecords> getOtaUpgradeRecordsList(OtaUpgradeRecordsPageQuery query);


    /**
     * Retrieves an OTA upgrade record based on the specified task ID and device identification.
     *
     * @param taskId               The unique identifier for the OTA upgrade task.
     * @param deviceIdentification The unique identifier for the device to which the OTA upgrade record pertains.
     * @return An {@link Optional} containing the {@link OtaUpgradeRecords} entity that matches the given task ID and device identification.
     * Returns an empty {@link Optional} if no record is found for the specified criteria.
     */
    Optional<OtaUpgradeRecords> getOtaUpgradeRecordsByTaskIdAndDeviceIdentification(Long taskId, String deviceIdentification);

    /**
     * 查询OTA升级记录统计信息
     *
     * @param params 分页参数
     * @return OTA升级记录统计信息
     */
    OtaUpgradeRecordsSummaryResultDTO selectOtaUpgradeRecordsSummary(PageParams<OtaUpgradeRecordsPageQuery> params);

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
     * @param taskId 任务ID
     * @param deviceIdentification 设备标识
     * @param status 状态值
     * @param errorMessage 错误信息
     */
    void updateStatusByDeviceAndTask(Long taskId, String deviceIdentification, Integer status, String errorMessage);

    /**
     * 根据任务ID和设备标识更新升级进度
     *
     * @param taskId 任务ID
     * @param deviceIdentification 设备标识
     * @param progress 进度值
     */
    void updateProgressByDeviceAndTask(Long taskId, String deviceIdentification, int progress);

    /**
     * 根据任务ID和设备标识更新APP确认状态
     *
     * @param taskId 任务ID
     * @param deviceIdentification 设备标识
     * @param appConfirmationStatus APP确认状态 (0:待确认, 1:已确认, 2:已拒绝, -1:无需确认)
     */
    void updateAppConfirmationStatus(Long taskId, String deviceIdentification, Integer appConfirmationStatus);

    /**
     * 根据任务ID和设备标识更新指令发送状态
     *
     * @param taskId 任务ID
     * @param deviceIdentification 设备标识
     * @param commandSendStatus 指令发送状态
     * @param errorMessage 错误信息
     */
    void updateCommandSendStatus(Long taskId, String deviceIdentification, Integer commandSendStatus, String errorMessage);
}