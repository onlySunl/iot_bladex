package org.springblade.modules.iot.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import org.springblade.core.tool.utils.DateUtil;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 区域
 */
@Data
@TableName("qs_common_region")
@EqualsAndHashCode(callSuper = true)
@Table(value = "qs_common_region", comment = "区域表")
public class QsRegion extends CustomBaseEntity implements Comparable<QsRegion> {
    private static final long serialVersionUID = 1L;



    /** 区域国标编号 */
    @TableField(value = "device_id")
    @AutoColumn(comment = "区域国标编号", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String deviceId;

    /** 区域名称 */
    @TableField(value = "name")
    @AutoColumn(comment = "区域名称", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String name;

    /** 父区域ID */
    @TableField(value = "parent_id")
    @AutoColumn(comment = "父区域ID", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer parentId;

    /** 父区域国标ID */
    @TableField(value = "parent_device_id")
    @AutoColumn(comment = "父区域国标ID", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String parentDeviceId;

    @Override
    public int compareTo(@NotNull QsRegion qsRegion) {
        return Integer.compare(Integer.parseInt(this.deviceId), Integer.parseInt(qsRegion.getDeviceId()));
    }

    public static QsRegion getInstance(String commonRegionDeviceId, String commonRegionName, String commonRegionParentId) {
        QsRegion region = new QsRegion();
        region.setDeviceId(commonRegionDeviceId);
        region.setName(commonRegionName);
        region.setParentDeviceId(commonRegionParentId);
        region.setCreateTime(DateUtil.now());
        region.setUpdateTime(DateUtil.now());
        return region;
    }
}
