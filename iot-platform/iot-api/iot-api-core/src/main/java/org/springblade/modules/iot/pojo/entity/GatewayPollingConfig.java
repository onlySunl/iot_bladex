/*
 *
 * Copyright (c) 2025, IoT-Universal. All Rights Reserved.
 *
 * @Description: 本文件由 Aleo 开发并拥有版权，未经授权严禁擅自商用、复制或传播。
 * @Author: Aleo
 * @Email: wo8335224@gmail.com
 * @Wechat: outlookFil
 *
 *
 */

package org.springblade.modules.iot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网关轮询配置实体
 * 
 * @author Aleo
 * @date 2025-10-26
 */
@Data
@TableName("iot_gateway_polling_config")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GatewayPollingConfig extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 主键ID */

  /** 网关设备ID */
  @TableField(value = "device_id")
  @AutoColumn(comment = "网关设备ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceId;

  /** 产品KEY */
  @TableField(value = "product_key")
  @AutoColumn(comment = "产品KEY", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productKey;

  /** IoT ID */
  @TableField(value = "iot_id")
  @AutoColumn(comment = "IoT ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String iotId;

  /** 是否启用轮询 */
  @TableField(value = "enabled")
  @AutoColumn(comment = "是否启用轮询", defaultValueType = DefaultValueEnum.NULL)
  private Boolean enabled;

  /** 轮询间隔(秒) */
  @TableField(value = "interval_seconds")
  @AutoColumn(comment = "轮询间隔(秒)", defaultValueType = DefaultValueEnum.NULL)
  private Integer intervalSeconds;

  /** 超时时间(秒) */
  @TableField(value = "timeout_seconds")
  @AutoColumn(comment = "超时时间(秒)", defaultValueType = DefaultValueEnum.NULL)
  private Integer timeoutSeconds;

  /** 失败重试次数 */
  @TableField(value = "retry_times")
  @AutoColumn(comment = "失败重试次数", defaultValueType = DefaultValueEnum.NULL)
  private Integer retryTimes;

  /** 指令间隔(毫秒) */
  @TableField(value = "command_interval_ms")
  @AutoColumn(comment = "指令间隔(毫秒)", defaultValueType = DefaultValueEnum.NULL)
  private Integer commandIntervalMs;

  /** 下次轮询时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(value = "next_poll_time")
  @AutoColumn(comment = "下次轮询时间", defaultValueType = DefaultValueEnum.NULL)
  private Date nextPollTime;

  /** 最后轮询时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(value = "last_poll_time")
  @AutoColumn(comment = "最后轮询时间", defaultValueType = DefaultValueEnum.NULL)
  private Date lastPollTime;

  /** 最后成功时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(value = "last_success_time")
  @AutoColumn(comment = "最后成功时间", defaultValueType = DefaultValueEnum.NULL)
  private Date lastSuccessTime;

  /** 连续失败次数 */
  @TableField(value = "continuous_fail_count")
  @AutoColumn(comment = "连续失败次数", defaultValueType = DefaultValueEnum.NULL)
  private Integer continuousFailCount;

  /** 轮询状态: NORMAL-正常, PAUSED-暂停, FAILED-失败 */
  @TableField(value = "polling_status")
  @AutoColumn(comment = "轮询状态: NORMAL-正常, PAUSED-暂停, FAILED-失败", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String pollingStatus;

  /** 总轮询次数 */
  @TableField(value = "total_poll_count")
  @AutoColumn(comment = "总轮询次数", defaultValueType = DefaultValueEnum.NULL)
  private Long totalPollCount;

  /** 成功次数 */
  @TableField(value = "success_count")
  @AutoColumn(comment = "成功次数", defaultValueType = DefaultValueEnum.NULL)
  private Long successCount;

  /** 失败次数 */
  @TableField(value = "fail_count")
  @AutoColumn(comment = "失败次数", defaultValueType = DefaultValueEnum.NULL)
  private Long failCount;

  /** 创建时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(value = "create_time")
  @AutoColumn(comment = "创建时间", defaultValueType = DefaultValueEnum.NULL)
  private Date createTime;

  /** 更新时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(value = "update_time")
  @AutoColumn(comment = "更新时间", defaultValueType = DefaultValueEnum.NULL)
  private Date updateTime;

  /** 创建人ID */
  @TableField(value = "creator_id")
  @AutoColumn(comment = "创建人ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String creatorId;
}
