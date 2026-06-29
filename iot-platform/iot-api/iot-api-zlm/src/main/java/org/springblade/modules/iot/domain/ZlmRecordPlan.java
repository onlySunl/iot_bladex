package org.springblade.modules.iot.domain;

import com.baomidou.mybatisplus.annotation.TableName;
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
    private String snap;

    /**
     * 计划关联通道数量
     */
    private Integer channelCount;

    /**
     * 计划名称
     */
    private String name;

    /**
     * 计划内容
     */
    private List<ZlmRecordPlanItem> planItemList;
}
