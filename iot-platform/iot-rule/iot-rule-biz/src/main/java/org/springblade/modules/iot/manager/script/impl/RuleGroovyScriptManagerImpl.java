package org.springblade.modules.iot.manager.script.impl;

import cn.hutool.core.util.StrUtil;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.common.database.mybatis.conditions.query.QueryWrap;
import org.springblade.modules.iot.entity.script.RuleGroovyScript;
import org.springblade.modules.iot.manager.script.RuleGroovyScriptManager;
import org.springblade.modules.iot.mapper.script.RuleGroovyScriptMapper;
import org.springblade.modules.iot.vo.query.script.RuleGroovyScriptPageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 规则脚本表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-03-24 09:54:10
 * @create [2025-03-24 09:54:10] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RuleGroovyScriptManagerImpl extends BaseServiceImpl<RuleGroovyScriptMapper, RuleGroovyScript> implements RuleGroovyScriptManager {

    private final RuleGroovyScriptMapper ruleGroovyScriptMapper;


    @Override
    public List<RuleGroovyScript> getRuleGroovyScriptList(RuleGroovyScriptPageQuery query) {
        QueryWrap<RuleGroovyScript> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(query.getId() != null, RuleGroovyScript::getId, query.getId());
        queryWrap.lambda().eq(StrUtil.isNotBlank(query.getAppId()), RuleGroovyScript::getAppId, query.getAppId());
        queryWrap.lambda().eq(StrUtil.isNotBlank(query.getScriptType()), RuleGroovyScript::getScriptType, query.getScriptType());
        queryWrap.lambda().eq(StrUtil.isNotBlank(query.getChannelCode()), RuleGroovyScript::getChannelCode, query.getChannelCode());
        queryWrap.lambda().eq(StrUtil.isNotBlank(query.getProductIdentification()), RuleGroovyScript::getProductIdentification, query.getProductIdentification());
        queryWrap.lambda().eq(StrUtil.isNotBlank(query.getTopicPattern()), RuleGroovyScript::getTopicPattern, query.getTopicPattern());
        queryWrap.lambda().eq(query.getEnable() != null, RuleGroovyScript::getEnable, query.getEnable());
        queryWrap.lambda().like(StrUtil.isNotBlank(query.getScriptContent()), RuleGroovyScript::getScriptContent, query.getScriptContent());
        queryWrap.lambda().like(StrUtil.isNotBlank(query.getExtendParams()), RuleGroovyScript::getExtendParams, query.getExtendParams());
        queryWrap.lambda().eq(StrUtil.isNotBlank(query.getObjectVersion()), RuleGroovyScript::getObjectVersion, query.getObjectVersion());

        return ruleGroovyScriptMapper.selectList(queryWrap);
    }
}


