package org.springblade.core.condition.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 条件表达式DTO基类
 */
@Getter
@Setter
public abstract class BaseConditionDTO implements Serializable {

    private static final long serialVersionUID = 7175617009127176322L;

    /**
     * 类型：GROUP | CONDITION
     */
    protected ConditionExpTypeEnum type;

    /**
     * 逻辑操作符 AND|OR
     */
    protected LogicalOperator logicalOperator;

    protected String uuid;

    /**
     * 强转成期望的子类
     *
     * @return T
     * @author shisen
     * @date 2021/4/21
     */
    public <T extends BaseConditionDTO> T cast() {
        return (T) this;
    }

    public enum ConditionExpTypeEnum {

        /**
         * 条件组
         */
        GROUP,
        /**
         * 条件
         */
        CONDITION
    }

    @Getter
    public enum LogicalOperator {
        /**
         * 并且
         */
        AND("and", "&&", "and"),
        /**
         * 或者
         */
        OR("or", "||", "or");

        private String desc;

        private String symbol;

        private String value;

        LogicalOperator(String desc, String symbol, String value) {
            this.desc = desc;
            this.symbol = symbol;
            this.value = value;
        }
    }
}
