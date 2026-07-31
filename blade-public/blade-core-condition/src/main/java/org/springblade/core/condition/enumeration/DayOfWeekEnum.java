package org.springblade.core.condition.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

/**
 * -----------------------------------------------------------------------------
 * File Name: DayOfWeekEnum.java
 * -----------------------------------------------------------------------------
 * Description:
 * 周信息枚举
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
 * @date 2023-10-22 23:44
 */

/**
 * 枚举表示一周的每一天。
 */
@Getter
@RequiredArgsConstructor
@Schema(title = "DayOfWeekEnum", description = "周信息枚举")
public enum DayOfWeekEnum {
    MONDAY("monday", "周一"),
    TUESDAY("tuesday", "周二"),
    WEDNESDAY("wednesday", "周三"),
    THURSDAY("thursday", "周四"),
    FRIDAY("friday", "周五"),
    SATURDAY("saturday", "周六"),
    SUNDAY("sunday", "周日");

    private final String value;
    private final String desc;

    /**
     * 根据描述获取对应的枚举值。
     *
     * @param desc 描述，例如："周一"
     * @return 对应的DayOfWeekEnum，如果找不到则返回空。
     */
    public static Optional<DayOfWeekEnum> getByDesc(String desc) {
        return Arrays.stream(DayOfWeekEnum.values())
                .filter(day -> day.getDesc().equals(desc))
                .findFirst();
    }

    /**
     * 根据value获取对应的枚举值。
     *
     * @param value value，例如："monday"
     * @return 对应的DayOfWeekEnum，如果找不到则返回空。
     */
    public static Optional<DayOfWeekEnum> getByValue(String value) {
        return Arrays.stream(DayOfWeekEnum.values())
                .filter(day -> day.getValue().equals(value))
                .findFirst();
    }
}
