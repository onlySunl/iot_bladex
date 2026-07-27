package org.springblade.modules.iot.service.alarm;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.dto.alarm.RuleAlarmActionConfigDTO;
import org.springblade.modules.iot.dto.linkage.execution.PolicyContext;
import org.springblade.modules.iot.entity.alarm.RuleAlarmRecord;
import org.springblade.modules.iot.protocol.vo.param.DeviceAlarmNotificationRequestParam;
import org.springblade.modules.iot.vo.param.linkage.RuleAlarmRecordHandleParamVO;
import org.springblade.modules.iot.vo.result.alarm.RuleAlarmRecordDetailsResultVO;
import org.springblade.modules.iot.vo.result.alarm.RuleAlarmRecordResultVO;
import org.springblade.modules.iot.vo.save.alarm.RuleAlarmRecordSaveVO;
import org.springblade.modules.iot.vo.update.alarm.RuleAlarmRecordUpdateVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 告警记录表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:15:22
 * @create [2023-09-09 21:15:22] [mqttsnet]
 */
public interface RuleAlarmRecordService extends BaseService<Long, RuleAlarmRecord> {

    /**
     * Save the alarm record.
     *
     * @param alarmRecordSaveVO Save parameters for alarm record
     * @return {@link RuleAlarmRecordSaveVO} Saved alarm record entity
     */
    RuleAlarmRecordSaveVO saveAlarmRecord(RuleAlarmRecordSaveVO alarmRecordSaveVO);

    /**
     * Update the alarm record.
     *
     * @param alarmRecordUpdateVO Update parameters for alarm record
     * @return {@link RuleAlarmRecordUpdateVO} Updated alarm record entity
     */
    RuleAlarmRecordUpdateVO updateAlarmRecord(RuleAlarmRecordUpdateVO alarmRecordUpdateVO);

    /**
     * Delete the alarm record.
     *
     * @param id ID of the alarm record to be deleted
     * @return {@link Boolean} True if successful, false otherwise
     */
    Boolean deleteAlarmRecord(Long id);

    /**
     * Get details of a specific alarm record.
     *
     * @param id ID of the alarm record
     * @return {@link RuleAlarmRecordDetailsResultVO} Details of the alarm record
     */
    RuleAlarmRecordDetailsResultVO getAlarmRecordDetails(Long id);

    /**
     * Fill rule and channel details for alarm record list results.
     *
     * @param records alarm record list
     */
    void fillAlarmRuleDetails(List<RuleAlarmRecordResultVO> records);


    /**
     * handle or solve alarm record
     *
     * @param recordHandleParamVO parameters
     * @return {@link RuleAlarmRecordUpdateVO} updated alarm record entity
     */
    RuleAlarmRecordUpdateVO handleOrSolveAlarmRecord(RuleAlarmRecordHandleParamVO recordHandleParamVO);

    /**
     * Trigger device alarm.
     *
     * @param notificationParam Device alarm notification parameters
     * @return {@link Boolean} Boolean value: true: successful, false: failed
     */
    boolean triggerDeviceAlarm(DeviceAlarmNotificationRequestParam notificationParam);

    /**
     * 场景联动触发设备告警,支持渠道级模板。
     *
     * @param actionConfig 触发告警动作配置
     * @param policyContext 规则执行上下文
     * @return 是否触发成功
     */
    boolean triggerDeviceAlarm(RuleAlarmActionConfigDTO actionConfig, PolicyContext policyContext);
}
