package org.springblade.modules.iot.service.alarm.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.SnowflakeIdUtil;
import org.springblade.modules.iot.cache.helper.LinkCacheDataHelper;
import org.springblade.common.constant.DsConstant;
import org.springblade.modules.iot.entity.alarm.RuleAlarm;
import org.springblade.modules.iot.manager.alarm.RuleAlarmManager;
import org.springblade.modules.iot.service.alarm.RuleAlarmChannelService;
import org.springblade.modules.iot.service.alarm.RuleAlarmService;
import org.springblade.modules.iot.vo.query.alarm.RuleAlarmChannelPageQuery;
import org.springblade.modules.iot.vo.query.alarm.RuleAlarmPageQuery;
import org.springblade.modules.iot.vo.result.alarm.RuleAlarmChannelDetailsResultVO;
import org.springblade.modules.iot.vo.result.alarm.RuleAlarmChannelResultVO;
import org.springblade.modules.iot.vo.result.alarm.RuleAlarmDetailsResultVO;
import org.springblade.modules.iot.vo.result.alarm.RuleAlarmResultVO;
import org.springblade.modules.iot.vo.save.alarm.RuleAlarmSaveVO;
import org.springblade.modules.iot.vo.update.alarm.RuleAlarmUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 告警规则表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:14:37
 * @create [2023-09-09 21:14:37] [mqttsnet]
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
public class RuleAlarmServiceImpl extends BladeServiceImpl<RuleAlarmManager, RuleAlarm> implements RuleAlarmService {

    private final LinkCacheDataHelper linkCacheDataHelper;

    @Autowired
    private RuleAlarmChannelService ruleAlarmChannelService;

    /**
     * Save alarm rule.
     *
     * @param saveVO Input parameters for saving.
     * @return {@link RuleAlarmSaveVO} Saved entity.
     */
    @Override
    public RuleAlarmSaveVO saveRuleAlarm(RuleAlarmSaveVO saveVO) {
        log.info("Saving alarm rule: {}", saveVO);

        // Validate input parameters.
        checkedRuleAlarmSaveVO(saveVO);

        // Build entity from the provided VO.
        RuleAlarm ruleAlarm = builderRuleAlarmSaveVO(saveVO);

        // Persist the alarm rule to the database.
        baseMapper.save(ruleAlarm);

        return BeanPlusUtil.toBeanIgnoreError(ruleAlarm, RuleAlarmSaveVO.class);
    }

    /**
     * Validate parameters for creating a new rule alarm.
     *
     * @param saveVO Input parameters.
     */
    private void checkedRuleAlarmSaveVO(RuleAlarmSaveVO saveVO) {
        ArgumentAssert.notBlank(saveVO.getAlarmName(), "Alarm rule name is required.");
        ArgumentAssert.notNull(saveVO.getAlarmScene(), "Alarm rule scene is required.");
        ArgumentAssert.notNull(saveVO.getLevel(), "Alarm rule level is required.");
    }

    /**
     * Build an entity from the input VO.
     *
     * @param saveVO Input parameters.
     * @return {@link RuleAlarm} Constructed alarm rule entity.
     */
    private RuleAlarm builderRuleAlarmSaveVO(RuleAlarmSaveVO saveVO) {
        saveVO.setAlarmIdentification(String.valueOf(SnowflakeIdUtil.nextId()));
        saveVO.setCreatedOrgId(ContextUtil.getCurrentDeptId());
        return BeanPlusUtil.toBeanIgnoreError(saveVO, RuleAlarm.class);
    }

    /**
     * Update an alarm rule.
     *
     * @param updateVO Input parameters for updating.
     * @return {@link RuleAlarmUpdateVO} Updated result.
     */
    @Override
    public RuleAlarmUpdateVO updateRuleAlarm(RuleAlarmUpdateVO updateVO) {
        log.info("Updating alarm rule: {}", updateVO);

        // Validate input parameters.
        checkedRuleAlarmUpdateVO(updateVO);

        // Build entity for update.
        RuleAlarm ruleAlarmBuilder = builderRuleAlarmUpdateVO(updateVO);
        ruleAlarmBuilder.setId(updateVO.getId());

        // Update alarm rule in the database.
        baseMapper.updateById(ruleAlarmBuilder);

        return updateVO;
    }

    /**
     * Delete an alarm rule.
     *
     * @param id The ID of the alarm rule.
     * @return {@link Boolean} true if successful, false otherwise.
     */
    @Override
    public Boolean deleteRuleAlarm(Long id) {
        ArgumentAssert.notNull(id, "ID is required.");

        RuleAlarm ruleAlarm = baseMapper.getById(id);

        if (ruleAlarm == null) {
            throw new ServiceException("The specified alarm rule does not exist.");
        }

        // Check if the rule is in use before deletion. Example logic:
        // if (isRuleAlarmInUse(id)) {
        //     throw new ServiceException("The rule alarm is in use and cannot be deleted.");
        // }

        return baseMapper.removeById(id);
    }

    /**
     * Fetch details of a specific alarm rule.
     *
     * @param id The ID of the rule.
     * @return {@link RuleAlarmDetailsResultVO} Rule alarm details.
     */
    @Override
    public RuleAlarmDetailsResultVO getRuleAlarmDetails(Long id) {
        if (id == null) {
            throw new ServiceException("Alarm rule ID is required.");
        }

        RuleAlarm ruleAlarm = baseMapper.getById(id);
        if (ruleAlarm == null) {
            throw new ServiceException("Specified alarm rule does not exist.");
        }

        RuleAlarmDetailsResultVO ruleAlarmDetailsResultVO = BeanPlusUtil.toBeanIgnoreError(ruleAlarm, RuleAlarmDetailsResultVO.class);
        fillAlarmAuditFields(ruleAlarm, ruleAlarmDetailsResultVO);

        // Fetch related information for the alarm rule, such as alarm channels.
        RuleAlarmChannelPageQuery alarmChannelPageQuery = new RuleAlarmChannelPageQuery();
        Optional.ofNullable(ruleAlarmDetailsResultVO).map(RuleAlarmDetailsResultVO::getAlarmChannelIds).filter(alarmChannelIds -> !alarmChannelIds.isEmpty()).map(ids -> ids.split(ContextConstants.SEPARATOR)).map(Arrays::asList).map(list -> list.stream().map(Long::valueOf).collect(Collectors.toList())).ifPresent(alarmChannelPageQuery::setIds);

        List<RuleAlarmChannelResultVO> alarmChannelResultVOList = ruleAlarmChannelService.getRuleAlarmChannelResultVOList(alarmChannelPageQuery);
        if (CollUtil.isNotEmpty(alarmChannelResultVOList)) {
            ruleAlarmDetailsResultVO.setRuleAlarmChannelDetailsResultVOList(BeanPlusUtil.toBeanList(alarmChannelResultVOList, RuleAlarmChannelDetailsResultVO.class));
        }

        return ruleAlarmDetailsResultVO;
    }

    /**
     * Retrieve a list of rule alarm VO.
     *
     * @param query Search parameters for rule alarm.
     * @return {@link List < RuleAlarmChannelResultVO >} List of rule alarm VO.
     */
    @Override
    public List<RuleAlarmResultVO> getRuleAlarmResultVOList(RuleAlarmPageQuery query) {
        List<RuleAlarm> ruleAlarmList = baseMapper.getRuleAlarmList(query);
        return BeanPlusUtil.toBeanList(ruleAlarmList, RuleAlarmResultVO.class);
    }

    @Override
    public RuleAlarmDetailsResultVO getRuleAlarmDetailsByAlarmIdentification(String alarmIdentification) {

        if (alarmIdentification == null) {
            throw new ServiceException("Alarm identification is required.");
        }

        RuleAlarm ruleAlarm = baseMapper.getOne(new LambdaQueryWrapper<RuleAlarm>().eq(RuleAlarm::getAlarmIdentification, alarmIdentification));

        if (Objects.isNull(ruleAlarm)) {
            throw new ServiceException("Specified alarm rule does not exist.");
        }

        RuleAlarmDetailsResultVO ruleAlarmDetailsResultVO = BeanPlusUtil.toBeanIgnoreError(ruleAlarm, RuleAlarmDetailsResultVO.class);
        fillAlarmAuditFields(ruleAlarm, ruleAlarmDetailsResultVO);

        // Fetch related information for the alarm rule, such as alarm channels.
        RuleAlarmChannelPageQuery alarmChannelPageQuery = new RuleAlarmChannelPageQuery();
        Optional.ofNullable(ruleAlarmDetailsResultVO).map(RuleAlarmDetailsResultVO::getAlarmChannelIds).filter(alarmChannelIds -> !alarmChannelIds.isEmpty()).map(ids -> ids.split(ContextConstants.SEPARATOR)).map(Arrays::asList).map(list -> list.stream().map(Long::valueOf).collect(Collectors.toList())).ifPresent(alarmChannelPageQuery::setIds);

        List<RuleAlarmChannelResultVO> alarmChannelResultVOList = ruleAlarmChannelService.getRuleAlarmChannelResultVOList(alarmChannelPageQuery);
        if (CollUtil.isNotEmpty(alarmChannelResultVOList)) {
            ruleAlarmDetailsResultVO.setRuleAlarmChannelDetailsResultVOList(BeanPlusUtil.toBeanList(alarmChannelResultVOList, RuleAlarmChannelDetailsResultVO.class));
        }

        return ruleAlarmDetailsResultVO;
    }

    private void fillAlarmAuditFields(RuleAlarm ruleAlarm, RuleAlarmDetailsResultVO resultVO) {
        if (ruleAlarm == null || resultVO == null) {
            return;
        }
        resultVO.setCreatedBy(ruleAlarm.getCreatedBy());
        resultVO.setCreatedOrgId(ruleAlarm.getCreatedOrgId());
    }


    /**
     * Validate parameters for updating a rule alarm.
     *
     * @param updateVO Input parameters.
     */
    private void checkedRuleAlarmUpdateVO(RuleAlarmUpdateVO updateVO) {
        ArgumentAssert.notNull(updateVO.getId(), "ID is required.");
        RuleAlarm ruleAlarm = baseMapper.getById(updateVO.getId());

        if (ruleAlarm == null) {
            throw new ServiceException("Specified alarm rule does not exist.");
        }

        ArgumentAssert.notBlank(updateVO.getAlarmChannelIds(), "Alarm channel IDs are required.");
    }

    /**
     * Build an entity from the update VO.
     *
     * @param updateVO Input parameters.
     * @return RuleAlarm Alarm rule builder.
     */
    private RuleAlarm builderRuleAlarmUpdateVO(RuleAlarmUpdateVO updateVO) {
        RuleAlarm result = new RuleAlarm();
        result.setAppId(updateVO.getAppId());
        result.setAlarmName(updateVO.getAlarmName());
        result.setAlarmChannelIds(updateVO.getAlarmChannelIds());
        result.setAlarmScene(updateVO.getAlarmScene());
        result.setLevel(updateVO.getLevel());
        result.setStatus(updateVO.getStatus());
        result.setRemark(updateVO.getRemark());
        result.setCreatedOrgId(ContextUtil.getCurrentDeptId());
        return result;
    }
}

