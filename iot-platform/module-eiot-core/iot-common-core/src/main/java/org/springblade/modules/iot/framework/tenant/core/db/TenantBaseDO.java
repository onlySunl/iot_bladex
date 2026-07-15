package org.springblade.modules.iot.framework.tenant.core.db;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * TenantBaseDO adapter - base entity with tenant isolation.
 */
@Data
public abstract class TenantBaseDO implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT, value = "create_user")
    private String creator;

    @TableField(fill = FieldFill.INSERT_UPDATE, value = "update_user")
    private String updater;

    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean deleted;

    @TableField(value = "tenant_id")
    private String tenantId;
}
