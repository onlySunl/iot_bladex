package org.springblade.modules.nvr.rule.engine;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 规则引擎执行器
 * 负责解析并执行规则SQL，对设备上报数据进行过滤和转换
 */
@Slf4j
@Component
public class RuleEngine {

    /**
     * 执行规则引擎
     *
     * @param payload 设备上报数据
     * @param sql     规则SQL语句
     * @param appId   应用ID
     * @return 匹配结果，null表示不匹配
     */
    public JSONObject executeRule(JSONObject payload, String sql, String appId) {
        try {
            // 解析SQL规则
            RuleSqlParser parser = new RuleSqlParser(sql);
            RuleSqlParser.ParsedRule rule = parser.parse();

            // 执行SELECT过滤
            if (!matchesWhere(payload, rule)) {
                return null;
            }

            // 构建输出结果
            return buildResult(payload, rule);
        } catch (Exception e) {
            log.error("规则引擎执行异常, sql={}, appId={}", sql, appId, e);
            return null;
        }
    }

    /**
     * 执行WHERE条件匹配
     */
    private boolean matchesWhere(JSONObject payload, RuleSqlParser.ParsedRule rule) {
        if (rule.getWhereConditions() == null || rule.getWhereConditions().isEmpty()) {
            return true;
        }
        for (RuleSqlParser.WhereCondition condition : rule.getWhereConditions()) {
            if (!evaluateCondition(payload, condition)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 评估单个条件
     */
    private boolean evaluateCondition(JSONObject payload, RuleSqlParser.WhereCondition condition) {
        Object value = payload.getByPath(condition.getField());
        if (value == null) {
            return false;
        }

        String strValue = String.valueOf(value);
        String compareValue = condition.getValue();

        return switch (condition.getOperator().toUpperCase()) {
            case "=" -> strValue.equals(compareValue);
            case ">" -> {
                try {
                    yield Double.parseDouble(strValue) > Double.parseDouble(compareValue);
                } catch (NumberFormatException e) {
                    yield strValue.compareTo(compareValue) > 0;
                }
            }
            case "<" -> {
                try {
                    yield Double.parseDouble(strValue) < Double.parseDouble(compareValue);
                } catch (NumberFormatException e) {
                    yield strValue.compareTo(compareValue) < 0;
                }
            }
            case ">=" -> {
                try {
                    yield Double.parseDouble(strValue) >= Double.parseDouble(compareValue);
                } catch (NumberFormatException e) {
                    yield strValue.compareTo(compareValue) >= 0;
                }
            }
            case "<=" -> {
                try {
                    yield Double.parseDouble(strValue) <= Double.parseDouble(compareValue);
                } catch (NumberFormatException e) {
                    yield strValue.compareTo(compareValue) <= 0;
                }
            }
            case "!=" -> !strValue.equals(compareValue);
            case "LIKE" -> strValue.contains(compareValue.replace("%", ""));
            default -> false;
        };
    }

    /**
     * 构建输出结果
     */
    private JSONObject buildResult(JSONObject payload, RuleSqlParser.ParsedRule rule) {
        JSONObject result = new JSONObject();
        if (rule.getSelectFields() == null || rule.getSelectFields().isEmpty() ||
            (rule.getSelectFields().size() == 1 && "*".equals(rule.getSelectFields().get(0)))) {
            // SELECT * 返回全部字段
            result.putAll(payload);
        } else {
            // 按指定字段选取
            for (String field : rule.getSelectFields()) {
                Object value = payload.get(field);
                if (value != null) {
                    result.set(field, value);
                }
            }
        }
        return result;
    }
}
