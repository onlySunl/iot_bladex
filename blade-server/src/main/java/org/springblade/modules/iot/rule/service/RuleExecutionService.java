package org.springblade.modules.iot.rule.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.pojo.entity.RuleModel;
import org.springblade.modules.iot.rule.engine.RuleEngine;
import org.springblade.modules.iot.rule.model.RuleConfig;
import org.springblade.modules.iot.rule.transmit.RuleTransmitTemplate;
import org.springblade.modules.iot.service.IRuleModelService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 规则引擎执行服务
 * 负责接收设备上报数据，匹配规则并执行转发
 */
@Slf4j
@Service
@AllArgsConstructor
public class RuleExecutionService {

    private final IRuleModelService ruleModelServiceImpl;
    private final RuleEngine ruleEngine;
    private final RuleTransmitTemplate ruleTransmitTemplate;

    /**
     * 异步执行规则引擎
     *
     * @param payload   设备上报数据（JSON格式）
     * @param productKey 产品Key
     * @param deviceId  设备ID
     * @param iotId     IoT唯一标识
     */
    @Async("taskExecutor")
    public void executeRules(JSONObject payload, String productKey, String deviceId, String iotId) {
        doExecuteRule(payload, productKey, deviceId, iotId);
    }

    /**
     * 同步执行规则引擎
     */
    public void executeRulesSync(JSONObject payload, String productKey, String deviceId, String iotId) {
        doExecuteRule(payload, productKey, deviceId, iotId);
    }

    /**
     * 执行规则匹配与转发
     */
    private void doExecuteRule(JSONObject payload, String productKey, String deviceId, String iotId) {
        // 查询该产品下所有启用的规则
        List<RuleModel> ruleModels = ruleModelServiceImpl.lambdaQuery()
                .eq(RuleModel::getProductKey, productKey)
                .eq(RuleModel::getStatus, "start")
                .list();

        if (ruleModels == null || ruleModels.isEmpty()) {
            return;
        }

        ruleModels.forEach(ruleModel -> {
            try {
                RuleConfig ruleConfig = JSONUtil.toBean(ruleModel.getConfig(), RuleConfig.class);

                JSONObject result = ruleEngine.executeRule(payload, ruleConfig.getSql(), null);

                if (Objects.nonNull(result)) {
                    log.info("匹配到规则引擎，调用转发 ruleId:{}, deviceId:{}", ruleModel.getId(), deviceId);
                    ruleConfig.getTargets().forEach(target -> {
                        try {
                            ruleTransmitTemplate.transmit(result, target);
                        } catch (Exception e) {
                            log.error("执行规则错误, 调用转发目标失败, deviceId:{}, iotId:{}, ruleId:{}, data:{}, target:{}",
                                    deviceId, iotId, ruleModel.getId(), result, target, e);
                        }
                    });
                }
            } catch (Exception e) {
                log.error("执行规则错误, deviceId:{}, iotId:{}, ruleId:{}, ruleName:{}",
                        deviceId, iotId, ruleModel.getId(), ruleModel.getRuleName(), e);
            }
        });
    }

    /**
     * 测试执行规则
     *
     * @param payload 测试数据（JSON字符串）
     * @param sql     规则SQL
     * @return 匹配结果
     */
    public JSONObject testExecuteRule(String payload, String sql) {
        JSONObject jsonObject;
        try {
            jsonObject = JSONUtil.parseObj(payload);
        } catch (Exception e) {
            throw new IllegalArgumentException("payload必须为JSON格式");
        }
        return ruleEngine.executeRule(jsonObject, sql, null);
    }
}
