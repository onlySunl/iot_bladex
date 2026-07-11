package org.springblade.modules.nvr.rule.model;

import lombok.Data;
import java.util.List;

/**
 * 规则配置
 */
@Data
public class RuleConfig {
    /**
     * SQL 规则语句
     */
    private String sql;
    /**
     * 转发目标列表
     */
    private List<RuleTarget> targets;
}
