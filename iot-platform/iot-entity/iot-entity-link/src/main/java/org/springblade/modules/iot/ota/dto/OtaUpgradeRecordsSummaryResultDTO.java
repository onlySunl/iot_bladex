package org.springblade.modules.iot.ota.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Description:
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class OtaUpgradeRecordsSummaryResultDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 总数量
     */
    private Long totalCount;

    /**
     * 待升级数量
     */
    private Long pendingUpgradeCount;

    /**
     * 升级中数量
     */
    private Long upgradingCount;

    /**
     * 升级成功数量
     */
    private Long upgradeSuccessCount;

    /**
     * 升级失败数量
     */
    private Long upgradeFailureCount;
}
