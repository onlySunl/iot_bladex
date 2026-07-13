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

@TableName("iot_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTProduct extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键ID
   */

  /**
   * 产品编号
   */
  @TableField(value = "product_id")
  @AutoColumn(comment = "/", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productId;

  /**
   * 产品标签
   */
  @TableField(value = "tags")
  @AutoColumn(comment = "/", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String tags;

  /**
   * 产品KEY
   */
  @TableField(value = "product_key")
  @AutoColumn(comment = "/", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productKey;

  /**
   * 产品密钥（1型1密）
   */
  @TableField(value = "product_secret")
  @AutoColumn(comment = "/", length = 128, defaultValueType = DefaultValueEnum.NULL)
//  @SensitiveField(showPrefix = 3, showSuffix = 3)
  private String productSecret;

  /**
   * 接入方式
   */
  @TableField(value = "third_platform")
  @AutoColumn(comment = "/", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String thirdPlatform;

  /**
   * 第三方平台配置信息
   */
  @TableField(value = "third_configuration")
  @ColumnType("text")
  @AutoColumn(comment = "/", defaultValueType = DefaultValueEnum.NULL)
  private String thirdConfiguration;

  /**
   * 厂商编号
   */
  @TableField(value = "company_no")
  @AutoColumn(comment = "/", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String companyNo;

  /**
   * 分类ID
   */
  @TableField(value = "classified_id")
  @AutoColumn(comment = "/", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String classifiedId;

  /**
   * 网络组件：关联network表
   */
  @TableField(value = "network_union_id")
  @AutoColumn(comment = "/", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String networkUnionId;

  /**
   * 设备类型: 网关，设备
   */
  @TableField(value = "device_node")
  @AutoColumn(comment = "/", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String deviceNode;

  /**
   * 所属网关设备的ProductKey
   */
  @TableField(value = "gw_product_key")
  @AutoColumn(comment = "/", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String gwProductKey;

  /**
   * 分类名称
   */
  @TableField(value = "classified_name")
  @AutoColumn(comment = "/", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String classifiedName;

  /**
   * 消息协议:
   */
  @TableField(value = "message_protocol")
  @ColumnType("text")
  @AutoColumn(comment = "/", defaultValueType = DefaultValueEnum.NULL)
  private String messageProtocol;

  /**
   * 名称
   */
  private String name;

  /**
   * 创建者id
   */
  @TableField(value = "creator_id")
  @AutoColumn(comment = "/", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String creatorId;

  /**
   * 产品状态
   */
  private Byte state;

  /**
   * 说明
   */
  @TableField(value = "`describe`")
  @AutoColumn(comment = "/", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String describe;

  /**
   * 数据存储策略
   */
  @TableField(value = "store_policy")
  @AutoColumn(comment = "/", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String storePolicy;

  /**
   * 传输协议: MQTT,COAP,UDP
   */
  @TableField(value = "transport_protocol")
  @AutoColumn(comment = "/", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String transportProtocol;

  /**
   * 图片地址
   */
  @TableField(value = "photo_url")
  @AutoColumn(comment = "/", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String photoUrl;

  /**
   * 创建时间
   */
  @TableField(value = "create_time")
  @AutoColumn(comment = "/", defaultValueType = DefaultValueEnum.NULL)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  /**
   * 协议配置
   */
  private String configuration;

  /**
   * 数据存储策略配置
   */
  @TableField(value = "store_policy_configuration")
  @ColumnType("text")
  @AutoColumn(comment = "/", defaultValueType = DefaultValueEnum.NULL)
  private String storePolicyConfiguration;

  /**
   * 物模型
   */
  private String metadata;

  /**
   * 第三方平台产品下发信息
   */
  private String thirdDownRequest;

  @TableField(value = "update_time")
  @AutoColumn(comment = "/", defaultValueType = DefaultValueEnum.NULL)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;

  @TableField(value = "instance")
  @AutoColumn(comment = "instance", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String instance;

  @TableField(value = "is_deleted")
  @AutoColumn(comment = "isDeleted", defaultValueType = DefaultValueEnum.NULL)
  private Integer isDeleted;
}
