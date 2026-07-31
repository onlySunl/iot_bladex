package org.springblade.modules.iot.ota.service.statemachine.event.source;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import org.springblade.basic.base.entity.Entity;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradesResultDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Description:
 * OTA任务执行事件源
 * <p>
 * 封装OTA升级任务执行所需的所有数据
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/08/22
 */
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OtaTaskExecutionEventSource extends Entity<Long> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * OTA升级任务
     */
    private OtaUpgradeTasksResultDTO upgradeTask;

    /**
     * OTA升级包信息
     */
    private OtaUpgradesResultDTO upgradePackage;


    /**
     * 需要升级的设备标识列表
     */
    private List<String> deviceIdentificationList;

    /**
     * 执行时间戳
     */
    private Long executionTimestamp;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建人组织ID
     */
    private Long createdOrgId;
}