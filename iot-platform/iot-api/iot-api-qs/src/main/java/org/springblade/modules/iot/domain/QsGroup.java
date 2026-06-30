package org.springblade.modules.iot.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import org.springblade.modules.iot.common.entity.CustomBaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 业务分组
 */
@Data
@TableName("qs_common_group")
@EqualsAndHashCode(callSuper = true)
@Table(value = "qs_common_group", comment = "业务分组表")
public class QsGroup extends CustomBaseEntity implements Comparable<QsGroup> {
    private static final long serialVersionUID = 1L;


    /** 区域国标编号 */
    @TableField(value = "device_id")
    @AutoColumn(comment = "区域国标编号", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String deviceId;

    /** 区域名称 */
    @TableField(value = "name")
    @AutoColumn(comment = "区域名称", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String name;

    /** 父分组ID */
    @TableField(value = "parent_id")
    @AutoColumn(comment = "父分组ID", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer parentId;

    /** 父区域国标ID */
    @TableField(value = "parent_device_id")
    @AutoColumn(comment = "父区域国标ID", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String parentDeviceId;

    /** 所属的业务分组国标编号 */
    @TableField(value = "business_group")
    @AutoColumn(comment = "所属的业务分组国标编号", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String businessGroup;

    /** 行政区划 */
    @TableField(value = "civil_code")
    @AutoColumn(comment = "行政区划", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String civilCode;

    /** 别名 */
    @TableField(value = "alias")
    @AutoColumn(comment = "别名", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String alias;

    @Override
    public int compareTo(@NotNull QsGroup region) {
        return Integer.compare(Integer.parseInt(this.deviceId), Integer.parseInt(region.getDeviceId()));
    }
}
