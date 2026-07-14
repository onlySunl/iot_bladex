

package org.springblade.modules.iot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
/**
 * 网关轮询配置实体
 * 
 * @author Aleo
 * @date 2025-10-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("iot_gateway_polling_config")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GatewayPollingConfig extends CustomBaseEntity {

  /** 主键ID */

  /** 网关设备ID */
  @TableField("device_id")
  private String deviceId;

  /** 产品KEY */
  @TableField("product_key")
  private String productKey;

  /** IoT ID */
  @TableField("iot_id")
  private String iotId;

  /** 是否启用轮询 */
  @TableField("enabled")
  private Boolean enabled;

  /** 轮询间隔(秒) */
  @TableField("interval_seconds")
  private Integer intervalSeconds;

  /** 超时时间(秒) */
  @TableField("timeout_seconds")
  private Integer timeoutSeconds;

  /** 失败重试次数 */
  @TableField("retry_times")
  private Integer retryTimes;

  /** 指令间隔(毫秒) */
  @TableField("command_interval_ms")
  private Integer commandIntervalMs;

  /** 下次轮询时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField("next_poll_time")
  private Date nextPollTime;

  /** 最后轮询时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField("last_poll_time")
  private Date lastPollTime;

  /** 最后成功时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField("last_success_time")
  private Date lastSuccessTime;

  /** 连续失败次数 */
  @TableField("continuous_fail_count")
  private Integer continuousFailCount;

  /** 轮询状态: NORMAL-正常, PAUSED-暂停, FAILED-失败 */
  @TableField("polling_status")
  private String pollingStatus;

  /** 总轮询次数 */
  @TableField("total_poll_count")
  private Long totalPollCount;

  /** 成功次数 */
  @TableField("success_count")
  private Long successCount;

  /** 失败次数 */
  @TableField("fail_count")
  private Long failCount;

 

  /** 创建人ID */
  @TableField("creator_id")
  private String creatorId;
}
