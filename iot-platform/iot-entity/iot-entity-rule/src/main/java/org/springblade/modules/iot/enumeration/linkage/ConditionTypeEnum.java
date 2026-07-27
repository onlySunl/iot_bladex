package org.springblade.modules.iot.enumeration.linkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * -----------------------------------------------------------------------------
 * File Name: ConditionTypeEnum.java
 * -----------------------------------------------------------------------------
 * Description:
 * 规则条件类型枚举
 * -----------------------------------------------------------------------------
 *
 * @author ShiHuan Sun
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * <p>
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023-11-26 16:36
 */
@Getter
@Schema(title = "ConditionTypeEnum", description = "规则条件类型枚举")
public enum ConditionTypeEnum {
    /**
     * 设备属性触发
     */
    DEVICE_PROPERTIES_TRIGGER(0, "设备属性触发"),

    /**
     * 定时触发
     */
    TIMING_TRIGGER(1, "定时触发"),

    /**
     * 设备动作触发
     */
    DEVICE_ACTION_TRIGGER(2, "设备动作触发"),

    /**
     * 自定义API触发
     */
    CUSTOM_API_TRIGGER(3, "自定义API触发"),
    ;


    ConditionTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    private Integer value;
    private String desc;

    /**
     * 根据value获取对应的枚举
     *
     * @param value 条件类型的数值
     * @return 返回对应的枚举，如果没有找到则返回null
     */
    public static ConditionTypeEnum fromValue(Integer value) {
        for (ConditionTypeEnum type : ConditionTypeEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }


    public String getDesc() {
        return this.desc;
    }

}