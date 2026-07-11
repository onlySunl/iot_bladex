package org.springblade.modules.nvr.rule.engine;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * 规则SQL解析器
 * 支持类SQL语法规则: SELECT field1, field2 FROM payload WHERE condition
 */
public class RuleSqlParser {

    private final String sql;

    public RuleSqlParser(String sql) {
        this.sql = sql;
    }

    /**
     * 解析SQL规则
     */
    public ParsedRule parse() {
        ParsedRule rule = new ParsedRule();

        if (sql == null || sql.isBlank()) {
            return rule;
        }

        String normalizedSql = sql.trim();

        // 解析 SELECT ... FROM ... WHERE ...
        String upperSql = normalizedSql.toUpperCase();
        int fromIndex = upperSql.indexOf("FROM");
        int whereIndex = upperSql.indexOf("WHERE");

        // 解析SELECT字段
        String selectPart;
        if (fromIndex > 0) {
            selectPart = normalizedSql.substring(0, fromIndex).trim();
            if (selectPart.toUpperCase().startsWith("SELECT")) {
                selectPart = selectPart.substring(6).trim();
            }
        } else {
            selectPart = normalizedSql;
        }

        // 解析字段列表
        List<String> fields = new ArrayList<>();
        for (String field : selectPart.split(",")) {
            String trimmed = field.trim();
            if (!trimmed.isEmpty()) {
                fields.add(trimmed);
            }
        }
        rule.setSelectFields(fields);

        // 解析WHERE条件
        if (whereIndex > 0) {
            String wherePart = normalizedSql.substring(whereIndex + 5).trim();
            List<WhereCondition> conditions = parseWhereConditions(wherePart);
            rule.setWhereConditions(conditions);
        }

        return rule;
    }

    /**
     * 解析WHERE条件（支持AND连接的多条件）
     */
    private List<WhereCondition> parseWhereConditions(String wherePart) {
        List<WhereCondition> conditions = new ArrayList<>();

        // 按AND分割
        String[] parts = wherePart.split("(?i)\\s+AND\\s+");
        for (String part : parts) {
            String trimmed = part.trim();
            if (trimmed.isEmpty()) continue;

            WhereCondition condition = parseCondition(trimmed);
            if (condition != null) {
                conditions.add(condition);
            }
        }

        return conditions;
    }

    /**
     * 解析单个条件表达式
     */
    private WhereCondition parseCondition(String expr) {
        String[] operators = {">=", "<=", "!=", ">", "<", "=", "LIKE"};
        for (String op : operators) {
            int idx;
            if ("LIKE".equalsIgnoreCase(op)) {
                idx = expr.toUpperCase().indexOf(" LIKE ");
                if (idx > 0) {
                    WhereCondition condition = new WhereCondition();
                    condition.setField(expr.substring(0, idx).trim());
                    condition.setOperator("LIKE");
                    condition.setValue(expr.substring(idx + 6).trim().replace("'", "").replace("\"", ""));
                    return condition;
                }
            } else {
                idx = expr.indexOf(op);
                if (idx > 0) {
                    WhereCondition condition = new WhereCondition();
                    condition.setField(expr.substring(0, idx).trim());
                    condition.setOperator(op);
                    condition.setValue(expr.substring(idx + op.length()).trim().replace("'", "").replace("\"", ""));
                    return condition;
                }
            }
        }
        return null;
    }

    /**
     * 解析后的规则结构
     */
    @Data
    public static class ParsedRule {
        private List<String> selectFields;
        private List<WhereCondition> whereConditions;
    }

    /**
     * WHERE条件
     */
    @Data
    public static class WhereCondition {
        private String field;
        private String operator;
        private String value;
    }
}
