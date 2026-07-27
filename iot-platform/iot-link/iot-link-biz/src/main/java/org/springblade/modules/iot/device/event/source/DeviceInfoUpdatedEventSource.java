package org.springblade.modules.iot.device.event.source;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import org.springblade.core.mp.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Description:
 * 设备信息更新事件源
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/6/5
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfoUpdatedEventSource extends Entity<Long> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 设备标识集合
     */
    private List<String> deviceIdentificationList;

}
