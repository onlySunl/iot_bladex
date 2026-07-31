package org.springblade.core.condition.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class ConditionParamResult implements Serializable {

    private static final long serialVersionUID = 252548762592369983L;
    /**
     * 变量类型
     */
    private ParamTypeEnum paramType;

    /**
     * 对象类型
     */
    private ObjectTypeEnum type;

    /**
     * 当为 entity/dto 时, 类名称
     */
    private String sourceName;

    /**
     * 属性名称，当为 entity/dto 时的属性名称，CUSTOM的变量名称
     */
    private String property;

    @Override
    public String toString() {
        return "ConditionParamResult{" +
               "paramType=" + paramType +
               ", type=" + type +
               ", sourceName='" + sourceName + '\'' +
               ", property='" + property + '\'' +
               '}';
    }

    public enum ParamTypeEnum {
        /**
         * 实体
         */
        ENTITY,
        /**
         * DTO
         */
        DTO,
        /**
         * 自定义变量
         */
        CUSTOM,
        /**
         * 枚举
         */
        ENUM
    }

    public enum ObjectTypeEnum {

        /**
         * 单个对象值
         */
        VALUE,

        /**
         * 列表
         */
        LIST
    }
}
