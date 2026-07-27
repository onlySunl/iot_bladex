package org.springblade.modules.iot.ota.enumeration;

import java.util.Optional;
import java.util.stream.Stream;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Description:
 * OTA升级任务记录APP确认状态
 * 字典标识：LINK_OTA_TASK_RECORD_APP_CONFIRM_STATUS
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "OtaTaskRecordAppConfirmStatusEnum", description = "OTA升级任务记录APP确认状态，字典标识：LINK_OTA_TASK_RECORD_APP_CONFIRM_STATUS")
public enum OtaTaskRecordAppConfirmStatusEnum {

    /**
     * 无需确认
     */
    NOT_REQUIRED(-1, "无需确认"),

    /**
     * 待确认
     */
    PENDING(0, "待确认"),

    /**
     * 已确认
     */
    CONFIRMED(1, "已确认"),

    /**
     * 已拒绝
     */
    REJECTED(2, "已拒绝");

    private Integer value;
    private String desc;

    /**
     * Get the enum value from an integer.
     *
     * @param value Integer value representing the app confirmation status.
     * @return An Optional of OtaTaskRecordAppConfirmStatusEnum.
     */
    public static Optional<OtaTaskRecordAppConfirmStatusEnum> fromValue(Integer value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(OtaTaskRecordAppConfirmStatusEnum.values())
                        .filter(e -> e.getValue().equals(val))
                        .findFirst());
    }

    /**
     * Check if the status requires user confirmation.
     *
     * @param value Integer value representing the status.
     * @return true if status requires confirmation, false otherwise.
     */
    public static boolean requiresConfirmation(Integer value) {
        return fromValue(value)
                .map(status -> status.equals(PENDING))
                .orElse(false);
    }

    /**
     * Check if the status is confirmed.
     *
     * @param value Integer value representing the status.
     * @return true if status is confirmed, false otherwise.
     */
    public static boolean isConfirmed(Integer value) {
        return fromValue(value)
                .map(status -> status.equals(CONFIRMED))
                .orElse(false);
    }

    /**
     * Check if the status is rejected.
     *
     * @param value Integer value representing the status.
     * @return true if status is rejected, false otherwise.
     */
    public static boolean isRejected(Integer value) {
        return fromValue(value)
                .map(status -> status.equals(REJECTED))
                .orElse(false);
    }

    /**
     * Check if the status doesn't require confirmation.
     *
     * @param value Integer value representing the status.
     * @return true if status doesn't require confirmation, false otherwise.
     */
    public static boolean notRequired(Integer value) {
        return fromValue(value)
                .map(status -> status.equals(NOT_REQUIRED))
                .orElse(false);
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
