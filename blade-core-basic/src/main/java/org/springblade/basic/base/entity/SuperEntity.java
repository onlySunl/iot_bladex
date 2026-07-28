package org.springblade.basic.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.*;
import lombok.experimental.Accessors;
import org.springblade.core.tenant.mp.TenantEntity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 包括id、created_time、created_by字段的表继承的基础实体
 *
 * @param <T> 主键类型
 * @author zuihou
 * @date 2019/05/05
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode
public class SuperEntity<T> extends TenantEntity {
    public static final String ID_FIELD = "id";
    public static final String CREATED_TIME = "createdTime";
    public static final String CREATED_TIME_FIELD = "created_time";
    public static final String CREATED_BY = "createdBy";
    public static final String CREATED_BY_FIELD = "created_by";
    public static final String CREATED_ORG_ID = "createdOrgId";
    public static final String CREATED_ORG_ID_FIELD = "created_org_id";

    @Serial
    private static final long serialVersionUID = -4603650115461757622L;

    @Generated
    public SuperEntity() {
    }
    /**
     * 保存和缺省验证组
     */
    public interface Save extends Default {

    }

    /**
     * 更新和缺省验证组
     */
    public interface Update extends Default {

    }
}
