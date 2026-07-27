package org.springblade.common.base;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体类
 */
@Data
public class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String createBy;
    private String updateBy;
    private String tenantId;
    private Integer deleted;
}
