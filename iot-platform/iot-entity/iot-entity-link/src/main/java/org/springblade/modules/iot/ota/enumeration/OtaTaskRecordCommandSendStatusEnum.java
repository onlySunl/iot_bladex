package org.springblade.modules.iot.ota.enumeration;

import java.util.Optional;
import java.util.stream.Stream;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Description:
 * OTA升级任务记录指令下发状态
 * 字典标识：LINK_OTA_TASK_RECORD_COMMAND_SEND_STATUS
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/11/14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "OtaTaskRecordCommandSendStatusEnum", description = "OTA升级任务记录指令下发状态，字典标识：LINK_OTA_TASK_RECORD_COMMAND_SEND_STATUS")
public enum OtaTaskRecordCommandSendStatusEnum {

    /**
     * 未下发
     */
    NOT_SENT(0, "未下发"),

    /**
     * 下发中
     */
    SENDING(1, "下发中"),

    /**
     * 下发成功
     */
    SENT_SUCCESS(2, "下发成功"),

    /**
     * 下发失败
     */
    SENT_FAILED(3, "下发失败");

    private Integer value;
    private String desc;

    /**
     * Get the enum value from an integer.
     *
     * @param value Integer value representing the command send status.
     * @return An Optional of OtaTaskRecordCommandSendStatusEnum.
     */
    public static Optional<OtaTaskRecordCommandSendStatusEnum> fromValue(Integer value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(OtaTaskRecordCommandSendStatusEnum.values())
                        .filter(e -> e.getValue().equals(val))
                        .findFirst());
    }

    /**
     * Check if the command is ready to be sent.
     *
     * @param value Integer value representing the status.
     * @return true if command can be sent, false otherwise.
     */
    public static boolean canSendCommand(Integer value) {
        return fromValue(value)
                .map(status -> status.equals(NOT_SENT) || status.equals(SENT_FAILED))
                .orElse(false);
    }

    /**
     * Check if the command is currently being sent.
     *
     * @param value Integer value representing the status.
     * @return true if command is being sent, false otherwise.
     */
    public static boolean isSending(Integer value) {
        return fromValue(value)
                .map(status -> status.equals(SENDING))
                .orElse(false);
    }

    /**
     * Check if the command was sent successfully.
     *
     * @param value Integer value representing the status.
     * @return true if command was sent successfully, false otherwise.
     */
    public static boolean isSentSuccess(Integer value) {
        return fromValue(value)
                .map(status -> status.equals(SENT_SUCCESS))
                .orElse(false);
    }

    /**
     * Check if the command sending failed.
     *
     * @param value Integer value representing the status.
     * @return true if command sending failed, false otherwise.
     */
    public static boolean isSentFailed(Integer value) {
        return fromValue(value)
                .map(status -> status.equals(SENT_FAILED))
                .orElse(false);
    }

    /**
     * Check if the command sending is in progress (sending or not sent yet).
     *
     * @param value Integer value representing the status.
     * @return true if command sending is in progress, false otherwise.
     */
    public static boolean isInProgress(Integer value) {
        return fromValue(value)
                .map(status -> status.equals(NOT_SENT) || status.equals(SENDING))
                .orElse(false);
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
