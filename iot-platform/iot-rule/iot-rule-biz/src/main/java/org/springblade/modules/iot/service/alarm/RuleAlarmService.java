package org.springblade.modules.iot.service.alarm;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.entity.alarm.RuleAlarm;
import org.springblade.modules.iot.vo.query.alarm.RuleAlarmPageQuery;
import org.springblade.modules.iot.vo.result.alarm.RuleAlarmDetailsResultVO;
import org.springblade.modules.iot.vo.result.alarm.RuleAlarmResultVO;
import org.springblade.modules.iot.vo.save.alarm.RuleAlarmSaveVO;
import org.springblade.modules.iot.vo.update.alarm.RuleAlarmUpdateVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 告警规则表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:14:37
 * @create [2023-09-09 21:14:37] [mqttsnet]
 */
public interface RuleAlarmService extends BaseService<RuleAlarm> {

    /**
     * Save alarm rule.
     *
     * @param ruleAlarmSaveVO Save parameters
     * @return {@link RuleAlarmSaveVO} Entity
     */
    RuleAlarmSaveVO saveRuleAlarm(RuleAlarmSaveVO ruleAlarmSaveVO);

    /**
     * Update alarm rule.
     *
     * @param ruleAlarmUpdateVO Update parameters
     * @return {@link RuleAlarmUpdateVO} Entity
     */
    RuleAlarmUpdateVO updateRuleAlarm(RuleAlarmUpdateVO ruleAlarmUpdateVO);

    /**
     * Delete alarm rule.
     *
     * @param id ID
     * @return {@link Boolean} Boolean value: true: successful, false: failed
     */
    Boolean deleteRuleAlarm(Long id);

    /**
     * Get alarm rule details.
     *
     * @param id ID
     * @return {@link RuleAlarmDetailsResultVO} Entity
     */
    RuleAlarmDetailsResultVO getRuleAlarmDetails(Long id);

    /**
     * Retrieve a list of rule alarm VO.
     *
     * @param query Search parameters for rule alarm.
     * @return {@link List < RuleAlarmChannelResultVO >} List of rule alarm VO.
     */
    List<RuleAlarmResultVO> getRuleAlarmResultVOList(RuleAlarmPageQuery query);

    /**
     * Get rule alarm details by alarm identification.
     *
     * @param alarmIdentification Alarm identification
     * @return {@link RuleAlarmDetailsResultVO} Entity
     */
    RuleAlarmDetailsResultVO getRuleAlarmDetailsByAlarmIdentification(String alarmIdentification);
}


