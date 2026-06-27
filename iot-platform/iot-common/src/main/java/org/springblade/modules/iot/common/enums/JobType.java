
package org.springblade.modules.iot.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务类型枚举
 */
@AllArgsConstructor
@Getter
public enum JobType {
    /** 定时任务 */
    CRON(1, "定时任务"),
    /** 周期任务 */
    PERIOD(2, "周期任务"),
    /** 一次性任务 */
    ONCE(3, "一次性任务"),
    /** 手动任务 */
    MANUAL(4, "手动任务");

    private int code;
    private String desc;

    public static JobType transfer(Integer code) {
        for (JobType value : JobType.values()) {
            if (value.code == code) {
                return value;
            }
        }
        return JobType.CRON;
    }
}
