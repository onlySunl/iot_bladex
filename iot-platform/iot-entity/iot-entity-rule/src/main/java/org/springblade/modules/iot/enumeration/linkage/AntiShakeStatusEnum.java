package org.springblade.modules.iot.enumeration.linkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * -----------------------------------------------------------------------------
 * File Name: AntiShakeStatusEnum.java
 * -----------------------------------------------------------------------------
 * Description:
 * 防抖状态
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
 * @date 2023-11-26 17:18
 */
@Getter
@Schema(title = "AntiShakeStatusEnum", description = "防抖状态枚举")
public enum AntiShakeStatusEnum {

    /**
     * 防抖启用
     */
    ENABLED(0, "启用"),

    /**
     * 防抖禁用
     */
    DISABLED(1, "禁用");

    private Integer value;
    private String desc;
    AntiShakeStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    /**
     * 根据value获取对应的枚举
     *
     * @param value 数值
     * @return 返回对应的枚举，如果没有找到则返回null
     */
    public static AntiShakeStatusEnum fromValue(Integer value) {
        for (AntiShakeStatusEnum type : AntiShakeStatusEnum.values()) {
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