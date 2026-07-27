package org.springblade.modules.iot.ota.enumeration;

import java.util.Optional;
import java.util.stream.Stream;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * -----------------------------------------------------------------------------
 * File Name: OtaUpgradeTaskStatusEnum
 * -----------------------------------------------------------------------------
 * Description:
 * Ota upgrade task  status
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/1/18       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2024/1/18 17:38
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "OtaUpgradeTaskStatusEnum", description = "OTA升级任务状态，字典标识：LINK_OTA_TASK_STATUS")
public enum OtaUpgradeTaskStatusEnum {
    PENDING(0, "待发布"),
    IN_PROGRESS(1, "进行中"),
    COMPLETED(2, "已完成"),
    CANCELLED(3, "已取消"),
    FAILED(4, "失败"),
    ;

    private Integer value;
    private String desc;

    /**
     * Get the enum value from an integer.
     *
     * @param value Integer value representing the status.
     * @return An Optional of OtaTaskStatusEnum.
     */
    public static Optional<OtaUpgradeTaskStatusEnum> fromValue(Integer value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(OtaUpgradeTaskStatusEnum.values())
                        .filter(e -> e.getValue().equals(val))
                        .findFirst());
    }

    /**
     * 判断是否为成功的状态
     *
     * @return 是否为成功的状态
     */
    public boolean isSuccessfulStatus() {
        return this.equals(IN_PROGRESS) || this.equals(COMPLETED);
    }
}