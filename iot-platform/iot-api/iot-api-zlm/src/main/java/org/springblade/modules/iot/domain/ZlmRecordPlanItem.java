package org.springblade.modules.iot.domain;

import com.baomidou.mybatisplus.annotation.TableName;
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

    private Long start;

    private Long stop;

    private Long weekDay;

    /**
     * 计划id
     */
    private Long planId;
}
