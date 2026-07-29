package org.springblade.modules.iot.job.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * -----------------------------------------------------------------------------
 * File Name: XxlJobTriggerStatusEnum.java
 * -----------------------------------------------------------------------------
 * Description:
 * Xxljob 触发状态枚举
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
 * @date 2023-10-27 15:33
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "XxlJobTriggerStatusEnum", description = "触发状态类型")
public enum XxlJobTriggerStatusEnum {
    /**
     * 停止
     */
    STOPPED(0, "停止"),

    /**
     * 运行
     */
    RUNNING(1, "运行");

    /**
     * 可选值
     */
    public static final List<Integer> STATE_COLLECTION = List.of(STOPPED.value, RUNNING.value);
    private Integer value;
    private String desc;

    /**
     * 根据 key 获取枚举。
     *
     * @param key 要查找的 key
     * @return 包含 TriggerStatusEnum 的 Optional
     */
    public static Optional<XxlJobTriggerStatusEnum> findByKey(Integer key) {
        return Arrays.stream(XxlJobTriggerStatusEnum.values())
                .filter(enumValue -> enumValue.getValue().equals(key))
                .findFirst();
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
