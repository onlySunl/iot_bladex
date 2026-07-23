package org.springblade.common.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import lombok.Data;
import org.springblade.core.tenant.mp.TenantEntity;

@Data
public class CustomBaseEntity extends TenantEntity {

    @AutoColumn(value = "revision",comment = "乐观锁", length = 10, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected Integer revision;

    @AutoColumn(value = "remark",comment = "备注", length = 1000, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected String remark;

    @AutoColumn(value = "attr1",comment = "预留字段", length = 100, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected String attr1;

    @AutoColumn(value = "attr2",comment = "预留字段", length = 100, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected String attr2;

    @AutoColumn(value = "attr3",comment = "预留字段", length = 100, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected String attr3;

    @AutoColumn(value = "attr4",comment = "预留字段", length = 100, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected String attr4;

    @AutoColumn(value = "attr5",comment = "预留字段", length = 100, defaultValueType = DefaultValueEnum.NULL)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    protected String attr5;
}
