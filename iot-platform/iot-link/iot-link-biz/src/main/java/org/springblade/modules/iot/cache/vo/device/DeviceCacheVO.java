package org.springblade.modules.iot.cache.vo.device;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 设备缓存 VO
 */
@Data
public class DeviceCacheVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String deviceName;
    private String deviceId;
    private String productId;
    private String deviceType;
    private Integer status;
    private String tenantId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
