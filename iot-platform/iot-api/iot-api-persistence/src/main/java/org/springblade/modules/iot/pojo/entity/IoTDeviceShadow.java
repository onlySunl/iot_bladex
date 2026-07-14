/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 本文件由 gitee.com/NexIoT 开发并拥有版权，未经授权严禁擅自商用、复制或传播。
 * @Author: gitee.com/NexIoT
 * @Email: wo8335224@gmail.com
 * @Wechat: outlookFil
 *
 *
 */

package org.springblade.modules.iot.pojo.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_shadow")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceShadow extends CustomBaseEntity {

  /** 本平台设备唯一标识符 */
  @TableField("iot_id")
  private String iotId;

  /** 产品KEY */
  @TableField("product_key")
  private String productKey;

  /** 设备自身序号 */
  @TableField("device_id")
  private String deviceId;

  /** 第三方平台设备ID唯一标识符 */
  @TableField("ext_device_id")
  private String extDeviceId;

  /** 注册时间 */
  @TableField("active_time")
  private Date activeTime;

  /** 激活时间 */
  @TableField("online_time")
  private Date onlineTime;

  /** 最后通信时间 */
  @TableField("last_time")
  private Date lastTime;

  /** 更新时间 */
  @TableField("update_date")
  private Date updateDate;

  /** 影子数据 */
  private String metadata;

  @TableField("`instance`")
  private String instance;

  /** 版本号 */
  @TableField("version")
  private Long version;
}
