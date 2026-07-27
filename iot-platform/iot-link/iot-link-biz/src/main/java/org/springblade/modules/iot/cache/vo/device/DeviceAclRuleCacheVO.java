package org.springblade.modules.iot.cache.vo.device;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 设备访问控制规则缓存 VO
 */
@Data
public class DeviceAclRuleCacheVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String ruleName;
    private String ruleType;
    private String ruleContent;
    private String deviceId;
    private String productId;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
