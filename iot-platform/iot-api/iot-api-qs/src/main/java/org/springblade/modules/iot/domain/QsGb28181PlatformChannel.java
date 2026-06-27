package org.springblade.modules.iot.domain;

import org.springblade.modules.iot.common.entity.CustomBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 国标GB28181平台通道关联对象 qs_gb28181_platform_channel
 *
 * @author ruoyi
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QsGb28181PlatformChannel extends CustomBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */

    /**
     * 国标GB28181平台ID
     */
    private Long platformId;

    /**
     * 设备ID
     */
    private Long deviceId;
}
