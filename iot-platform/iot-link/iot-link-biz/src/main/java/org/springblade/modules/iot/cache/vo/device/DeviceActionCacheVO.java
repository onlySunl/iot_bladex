package org.springblade.modules.iot.cache.vo.device;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 设备动作缓存 VO
 */
@Data
public class DeviceActionCacheVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String deviceId;
    private String actionName;
    private String actionType;
    private String actionContent;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
