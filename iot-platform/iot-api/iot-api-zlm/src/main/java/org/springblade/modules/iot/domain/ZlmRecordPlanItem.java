package org.springblade.modules.iot.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.mpe.autotable.annotation.AutoColumn;
import com.tangzc.mpe.autotable.annotation.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

/**
 * 录像计划管理通道对象 zlm_record_plan_item
 *
 * @author fengcheng
 * @date 2026-04-11
 */
@Data
@TableName("zlm_record_plan_item")
@EqualsAndHashCode(callSuper = true)
@Table(value = "zlm_record_plan_item", comment = "录像计划管理通道对象")
public class ZlmRecordPlanItem extends CustomBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 开始时间
     */
    @AutoColumn(comment = "开始时间", length = 20, defaultValueType = DefaultValueEnum.NULL)
    @TableField("start")
    private Long start;

    /**
     * 结束时间
     */
    @AutoColumn(comment = "结束时间", length = 20, defaultValueType = DefaultValueEnum.NULL)
    @TableField("stop")
    private Long stop;

    /**
     * 星期几
     */
    @AutoColumn(comment = "星期几", length = 11, defaultValueType = DefaultValueEnum.NULL)
    @TableField("week_day")
    private Long weekDay;

    /**
     * 计划id
     */
    @AutoColumn(comment = "计划id", length = 20, defaultValueType = DefaultValueEnum.NULL)
    @TableField("plan_id")
    private Long planId;
}
