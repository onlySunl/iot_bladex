package org.springblade.modules.iot.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.mpe.autotable.annotation.AutoColumn;
import com.tangzc.mpe.autotable.annotation.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

import java.util.List;

/**
 * 录像计划对象 zlm_record_plan
 *
 * @author fengcheng
 * @date 2026-04-11
 */
@Data
@TableName("zlm_record_plan")
@EqualsAndHashCode(callSuper = true)
@Table(value = "zlm_record_plan", comment = "录像计划对象")
public class ZlmRecordPlan extends CustomBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 是否开启定时截图
     */
    @AutoColumn(comment = "是否开启定时截图", length = 1, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    @TableField("snap")
    private String snap;

    /**
     * 计划关联通道数量
     */
    @AutoColumn(comment = "计划关联通道数量", length = 11, defaultValueType = DefaultValueEnum.NULL)
    @TableField("channel_count")
    private Integer channelCount;

    /**
     * 计划名称
     */
    @AutoColumn(comment = "计划名称", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    @TableField("name")
    private String name;

    /**
     * 计划内容
     */
    @TableField(exist = false)
    private List<ZlmRecordPlanItem> planItemList;
}
