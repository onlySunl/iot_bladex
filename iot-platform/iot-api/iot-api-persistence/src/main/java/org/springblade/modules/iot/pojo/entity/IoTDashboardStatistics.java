package org.springblade.modules.iot.pojo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
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

  /** 统计日期 */
  @TableField("stat_date")
  private LocalDate statDate;

  /** 产品Key，NULL表示全产品 */
  @TableField("product_key")
  private String productKey;

  /** 推送渠道，NULL表示全渠道 */
  @TableField("channel")
  private String channel;

  /** 指标类型 */
  @TableField("metric_type")
  private String metricType;

  /** 指标值 */
  @TableField("metric_value")
  private Long metricValue;

  /** 创建时间 */

  /** 更新时间 */

  /** 指标类型枚举 */
  
}
