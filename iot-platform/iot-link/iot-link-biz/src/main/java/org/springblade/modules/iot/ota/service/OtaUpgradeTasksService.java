package org.springblade.modules.iot.ota.service;

import java.util.Collection;
import java.util.List;

import org.springblade.core.mvc.service.SuperService;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.entity.OtaUpgradeTasks;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTasksPageQuery;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeTasksResultVO;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradeTasksSaveVO;
import org.springblade.modules.iot.ota.vo.update.OtaUpgradeTasksUpdateVO;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaCommandResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaListUpgradeableVersionsResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaPullParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaPullResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReadResponseParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReportParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaReportResponseParam;


/**
 * <p>
 * 业务接口
 * OTA升级任务表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:40:04
 * @create [2024-01-12 22:40:04] [mqttsnet]
 */
public interface OtaUpgradeTasksService extends SuperService<Long, OtaUpgradeTasks> {

    /**
     * Save OTA Upgrade Task
     *
     * @param saveVO 保存参数
     * @return {@link OtaUpgradeTasksSaveVO} 返回结果
     */
    OtaUpgradeTasksSaveVO saveUpgradeTask(OtaUpgradeTasksSaveVO saveVO);

    /**
     * Update OTA Upgrade Task
     *
     * @param updateVO 更新参数
     * @return {@link OtaUpgradeTasksUpdateVO} 返回结果
     */
    OtaUpgradeTasksUpdateVO updateUpgradeTask(OtaUpgradeTasksUpdateVO updateVO);

    /**
     * Update OTA Upgrade Task Status
     *
     * @param id     主键
     * @param status 状态
     * @return {@link Boolean} 返回结果
     */
    Boolean changeTaskStatus(Long id, Integer status);


    /**
     * Delete OTA Upgrade Task
     *
     * @param id 主键
     * @return {@link Boolean} 返回结果
     */
    Boolean deleteOtaUpgradeTask(Long id);

    /**
     * Get OTA Upgrade Task Details
     *
     * @param taskId 任务ID
     * @return {@link OtaUpgradeTasksResultVO} 返回结果
     */
    OtaUpgradeTasksResultVO getUpgradeTaskDetails(Long taskId);

    /**
     * Get OTA Upgrade Task Details List
     *
     * @param query 查询参数
     * @return {@link List<OtaUpgradeTasksResultDTO>} 返回结果
     */
    List<OtaUpgradeTasksResultDTO> getUpgradeTaskDetailsList(OtaUpgradeTasksPageQuery query);

    /**
     * 从MQTT事件中保存OTA升级记录。
     *
     * @param topoOtaCommandResponseParam 包含OTA命令响应的消息主体。
     * @return {@link TopoOtaCommandResponseParam} 已保存的OTA升级记录。
     */
    TopoOtaCommandResponseParam saveOtaUpgradeRecordByMqtt(TopoOtaCommandResponseParam topoOtaCommandResponseParam);

    /**
     * 北向API保存OTA升级记录
     *
     * @param topoOtaCommandResponseParam 包含OTA命令响应的消息主体。
     * @return {@link TopoOtaCommandResponseParam} 已保存的OTA升级记录。
     */
    TopoOtaCommandResponseParam saveUpgradeRecordByNorthbound(TopoOtaCommandResponseParam topoOtaCommandResponseParam);

    /**
     * 通过MQTT事件拉取OTA信息
     *
     * @param topoOtaPullParam 拉取OTA参数
     * @return {@link TopoOtaPullResponseParam} OTA信息记录
     */
    TopoOtaPullResponseParam otaPullByMqtt(TopoOtaPullParam topoOtaPullParam);


    /**
     * 北向API拉取OTA信息
     *
     * @param topoOtaPullParam 拉取OTA参数
     * @return {@link TopoOtaPullResponseParam} OTA信息记录
     */
    TopoOtaPullResponseParam otaPullByNorthbound(TopoOtaPullParam topoOtaPullParam);

    /**
     * 通过MQTT事件上报OTA信息
     *
     * @param topoOtaReportParam 上报OTA信息参数
     * @return {@link TopoOtaReportResponseParam} OTA信息记录
     */
    TopoOtaReportResponseParam otaReportByMqtt(TopoOtaReportParam topoOtaReportParam);

    /**
     * 北向API上报OTA信息
     *
     * @param topoOtaReportParam 上报OTA信息参数
     * @return {@link TopoOtaReportResponseParam} OTA信息记录
     */
    TopoOtaReportResponseParam otaReportByNorthbound(TopoOtaReportParam topoOtaReportParam);


    /**
     * 通过MQTT事件 OTA读取响应
     *
     * @param topoOtaReadResponseParam 读取OTA信息参数
     */
    void otaReadResponseByMqtt(TopoOtaReadResponseParam topoOtaReadResponseParam);

    /**
     * 北向API OTA读取响应
     *
     * @param topoOtaReadResponseParam 读取OTA信息参数
     */
    void otaReadResponseByNorthbound(TopoOtaReadResponseParam topoOtaReadResponseParam);

    /**
     * 更新任务状态
     *
     * @param taskId 任务ID
     * @param status 任务状态
     * @return 更新是否成功
     */
    boolean updateTaskStatus(Long taskId, org.springblade.modules.iot.ota.enumeration.OtaUpgradeTaskStatusEnum status);

    /**
     * 更新重试次数
     *
     * @param taskId     任务ID
     * @param retryCount 重试次数
     * @return 更新是否成功
     */
    boolean updateRetryCount(Long taskId, int retryCount);

    /**
     * 根据ID集合查询任务信息
     *
     * @param ids 任务ID集合
     * @return {@link List<OtaUpgradeTasksResultVO>} 任务信息列表
     */
    List<OtaUpgradeTasksResultVO> selectListByIds(Collection<Long> ids);

    /**
     * 北向API获取可升级版本列表
     *
     * @param deviceIdentification 设备标识
     * @param packageType          包类型
     * @return {@link TopoOtaListUpgradeableVersionsResponseParam} 可升级版本列表响应
     */
    TopoOtaListUpgradeableVersionsResponseParam getAvailableUpgradeVersionsByNorthbound(String deviceIdentification, Integer packageType);
}
