package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springblade.common.entity.CustomBaseEntity;

import java.time.LocalDate;

/**
 * 仪表盘统计实体
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("iot_dashboard_statistics")
public class IoTDashboardStatistics extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 统计日期 */
  @TableField(value = "stat_date")
  @AutoColumn(comment = "统计日期", defaultValueType = DefaultValueEnum.NULL)
  private LocalDate statDate;

  /** 产品Key，NULL表示全产品 */
  @TableField(value = "product_key")
  @AutoColumn(comment = "产品Key，NULL表示全产品", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productKey;

  /** 推送渠道，NULL表示全渠道 */
  @TableField(value = "channel")
  @AutoColumn(comment = "推送渠道，NULL表示全渠道", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String channel;

  /** 指标类型 */
  @TableField(value = "metric_type")
  @AutoColumn(comment = "指标类型", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String metricType;

  /** 指标值 */
  @TableField(value = "metric_value")
  @AutoColumn(comment = "指标值", defaultValueType = DefaultValueEnum.NULL)
  private Long metricValue;



  /** 指标类型枚举 */
}
