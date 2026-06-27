package org.springblade.modules.iot.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 告警级别枚举
 */
@Getter
@AllArgsConstructor
public enum AlertLevel {
    INFO(1, "提醒通知"),
    WARNING(2, "轻微问题"),
    CRITICAL(3, "严重警告"),
    SCENE(4, "场景联动");

    private final int code;
    private final String desc;
}
