package org.springblade.modules.iot.enumeration.alarm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 告警记录处理状态
 * </p>
 *
 * @author shihuan sun
 * @date 2023-04-14
 */
@Getter
@Schema(title = "AlarmRecordHandleTypeEnum", description = "告警记录处理状态枚举")
public enum AlarmRecordHandledStatusEnum {

    /**
     * Pending
     */
    PENDING(0, "Pending"),

    /**
     * In Progress
     */
    IN_PROGRESS(1, "In Progress"),

    /**
     * Resolved
     */
    RESOLVED(2, "Resolved");

    /**
     * 可选值
     */
    public static final List<Integer> STATE_COLLECTION = List.of(PENDING.value, IN_PROGRESS.value, RESOLVED.value);
    private Integer value;
    private String desc;
    AlarmRecordHandledStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public static AlarmRecordHandledStatusEnum valueOf(Integer value) {
        return Arrays.stream(values())
                .filter(type -> type.getValue().equals(Optional.ofNullable(value).orElse(-1))) // 使用一个不存在的默认值，如-1
                .findFirst()
                .orElse(null); // 或者您可以选择返回一个默认的枚举值，比如UNKNOWN
    }


    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getValue() {
        return this.value;
    }


    public String getDesc() {
        return this.desc;
    }

}