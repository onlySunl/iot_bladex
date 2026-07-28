package org.springblade.core.mp.base;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体类
 *
 * @author Chill
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 更新人
     */
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /**
     * 状态
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    /**
     * 租户ID
     */
    @TableField(value = "tenant_id")
    private String tenantId;
}
