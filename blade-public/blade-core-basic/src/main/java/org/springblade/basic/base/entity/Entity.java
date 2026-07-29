package org.springblade.basic.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * IoT 自定义基础实体
 *
 * @author EnjoyIot
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Entity<T> extends SuperEntity<T>{
    public static final String ID_FIELD = "id";
    public static final String CREATED_TIME = "createTime";
    public static final String CREATED_TIME_FIELD = "create_time";
    public static final String CREATED_BY = "createUser";
    public static final String CREATED_BY_FIELD = "create_user";
    public static final String CREATED_ORG_ID = "createDept";
    public static final String CREATED_ORG_ID_FIELD = "create_dept";
    public static final String UPDATED_TIME = "updateTime";
    public static final String UPDATED_BY = "updateUser";
    public static final String UPDATED_TIME_FIELD = "update_time";
    public static final String UPDATED_BY_FIELD = "update_user";


    @AutoColumn(value = "revision", comment = "乐观锁", length = 10, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected Integer revision;

    @AutoColumn(value = "remark", comment = "备注", length = 1000, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected String remark;

    @AutoColumn(value = "attr1", comment = "预留字段", length = 100, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected String attr1;

    @AutoColumn(value = "attr2", comment = "预留字段", length = 100, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected String attr2;

    @AutoColumn(value = "attr3", comment = "预留字段", length = 100, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected String attr3;

    @AutoColumn(value = "attr4", comment = "预留字段", length = 100, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected String attr4;

    @AutoColumn(value = "attr5", comment = "预留字段", length = 100, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected String attr5;


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
