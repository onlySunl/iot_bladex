package org.springblade.modules.iot.ota.enumeration;

import java.util.Optional;
import java.util.stream.Stream;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * OTA升级事件枚举
 * 精简事件类型，只保留核心事件，确保与任务状态紧密对应
 *
 * @author mqttsnet
 * @version 1.0.0
 * @see OtaUpgradeTaskStatusEnum
 * <p><strong>核心事件分类：</strong></p>
 * <ul>
 *   <li><strong>任务控制事件</strong>：START_UPGRADE, MANUAL_CANCEL, RETRY_UPGRADE</li>
 *   <li><strong>升级结果事件</strong>：UPGRADE_SUCCESS, UPGRADE_FAILED, TIMEOUT</li>
 *   <li><strong>设备相关事件</strong>：DEVICE_ONLINE, DEVICE_OFFLINE, DEVICE_RESULT_REPORT</li>
 *   <li><strong>APP交互事件</strong>：APP_CONFIRM_UPGRADE, APP_REJECT_UPGRADE, APP_CONFIRM_UPGRADE_PENDING</li>
 * </ul>
 * @since 2025/11/10
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "OtaUpgradeEventEnum", description = "OTA升级事件")
public enum OtaUpgradeEventEnum {
    /**
     * 开始升级事件
     * 触发状态：PENDING → IN_PROGRESS
     */
    START_UPGRADE(0, "开始升级"),

    /**
     * 升级完成事件
     * 触发状态：IN_PROGRESS → COMPLETED（静态升级）
     */
    UPGRADE_COMPLETED(1, "升级完成"),

    /**
     * 升级失败事件
     * 触发状态：IN_PROGRESS → FAILED（静态升级）
     */
    UPGRADE_FAILED(2, "升级失败"),

    /**
     * 重试升级事件
     * 触发状态：FAILED → IN_PROGRESS
     */
    RETRY_UPGRADE(3, "重试升级"),

    /**
     * 手动取消事件
     * 触发状态：IN_PROGRESS → CANCELLED
     */
    MANUAL_CANCEL(4, "手动取消"),

    /**
     * 设备上线事件（动态升级使用）
     * 触发状态：IN_PROGRESS → IN_PROGRESS（内联）
     */
    DEVICE_ONLINE(5, "设备上线"),

    /**
     * 设备离线事件（动态升级使用）
     * 触发状态：IN_PROGRESS → IN_PROGRESS（内联）
     */
    DEVICE_OFFLINE(6, "设备离线"),

    /**
     * 设备结果上报事件
     * 触发状态：IN_PROGRESS → IN_PROGRESS（内联）
     */
    DEVICE_RESULT_REPORT(7, "设备结果上报"),

    /**
     * 超时事件
     * 触发状态：IN_PROGRESS → FAILED
     */
    TIMEOUT(8, "超时"),

    /**
     * 无需APP确认升级事件
     * 触发状态：IN_PROGRESS → IN_PROGRESS（内联）
     */
    APP_CONFIRM_UPGRADE_NO_REQUIRED(9, "无需APP确认升级"),

    /**
     * APP确认升级待处理事件
     * 触发状态：IN_PROGRESS → IN_PROGRESS（内联）
     */
    APP_CONFIRM_UPGRADE_PENDING(10, "APP确认升级待处理"),

    /**
     * APP确认升级事件
     * 触发状态：IN_PROGRESS → IN_PROGRESS（内联）
     */
    APP_CONFIRM_UPGRADE(11, "APP确认升级"),

    /**
     * APP拒绝升级事件
     * 触发状态：IN_PROGRESS → IN_PROGRESS（内联）
     */
    APP_REJECT_UPGRADE(12, "APP拒绝升级"),

    /**
     * 手动重试指定设备事件
     * <p>用于手动重试指定设备列表的升级，允许重复下发升级指令</p>
     * <p>触发状态：IN_PROGRESS → IN_PROGRESS（内联）</p>
     * <p>触发状态：PENDING → IN_PROGRESS（外部）</p>
     * <p>与 {@link #START_UPGRADE} 和 {@link #RETRY_UPGRADE} 的区别：</p>
     * <ul>
     *   <li>不获取升级范围内的设备列表，直接使用指定的设备列表</li>
     *   <li>允许对已经下发过指令的设备重新下发</li>
     *   <li>不影响任务的整体状态和重试计数</li>
     *   <li>主要用于手动介入场景，如设备升级失败后的单独重试</li>
     * </ul>
     */
    MANUAL_RETRY_DEVICES(13, "手动重试指定设备");

    private Integer value;
    private String desc;

    /**
     * 根据数值获取枚举
     *
     * @param value 数值
     * @return 枚举Optional
     * @see #getDescByValue(Integer)
     * @see #isValid(Integer)
     */
    public static Optional<OtaUpgradeEventEnum> fromValue(Integer value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(OtaUpgradeEventEnum.values())
                        .filter(e -> e.getValue().equals(val))
                        .findFirst());
    }

    /**
     * 根据数值获取描述
     *
     * @param value 数值
     * @return 描述
     * @see #fromValue(Integer)
     */
    public static String getDescByValue(Integer value) {
        return fromValue(value)
                .map(OtaUpgradeEventEnum::getDesc)
                .orElse(null);
    }

    /**
     * 检查数值是否有效
     *
     * @param value 数值
     * @return 是否有效
     * @see #fromValue(Integer)
     */
    public static boolean isValid(Integer value) {
        return fromValue(value).isPresent();
    }
}