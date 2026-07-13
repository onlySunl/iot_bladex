package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

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

  @KeySql(useGeneratedKeys = true)
  private Long id;

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

  /** 创建时间 */
  @TableField(value = "create_time")
  @AutoColumn(comment = "创建时间", defaultValueType = DefaultValueEnum.NULL)
  private LocalDateTime createTime;

  /** 更新时间 */
  @TableField(value = "update_time")
  @AutoColumn(comment = "更新时间", defaultValueType = DefaultValueEnum.NULL)
  private LocalDateTime updateTime;

  /** 指标类型枚举 */
  public enum MetricType {
    DEVICE_TOTAL("device_total", "设备总数"),
    DEVICE_ONLINE("device_online", "在线设备数"),
    MESSAGE_TOTAL("message_total", "消息总数"),
    MESSAGE_SUCCESS("message_success", "成功消息数"),
    MESSAGE_FAILED("message_failed", "失败消息数"),
    MESSAGE_RETRY("message_retry", "重试消息数"),
    MESSAGE_PUSH("message_push", "推送消息数");

    private final String code;
    private final String description;

    MetricType(String code, String description) {
      this.code = code;
      this.description = description;
    }

    public String getCode() {
      return code;
    }

    public String getDescription() {
      return description;
    }
  }
}
