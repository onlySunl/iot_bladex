

package org.springblade.modules.iot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTProduct extends CustomBaseEntity {

  /**
   * 主键ID
   */

  /**
   * 产品编号
   */
  @TableField("product_id")
  private String productId;

  /**
   * 产品标签
   */
  @TableField("tags")
  private String tags;

  /**
   * 产品KEY
   */
  @TableField("product_key")
  private String productKey;

  /**
   * 产品密钥（1型1密）
   */
  @TableField("product_secret")
//  @SensitiveField(showPrefix = 3, showSuffix = 3)
  private String productSecret;

  /**
   * 接入方式
   */
  @TableField("third_platform")
  private String thirdPlatform;

  /**
   * 第三方平台配置信息
   */
  @TableField("third_configuration")
  private String thirdConfiguration;

  /**
   * 厂商编号
   */
  @TableField("company_no")
  private String companyNo;

  /**
   * 分类ID
   */
  @TableField("classified_id")
  private String classifiedId;

  /**
   * 网络组件：关联network表
   */
  @TableField("network_union_id")
  private String networkUnionId;

  /**
   * 设备类型: 网关，设备
   */
  @TableField("device_node")
  private String deviceNode;

  /**
   * 所属网关设备的ProductKey
   */
  @TableField("gw_product_key")
  private String gwProductKey;

  /**
   * 分类名称
   */
  @TableField("classified_name")
  private String classifiedName;

  /**
   * 消息协议:
   */
  @TableField("message_protocol")
  private String messageProtocol;

  /**
   * 名称
   */
  private String name;

  /**
   * 创建者id
   */
  @TableField("creator_id")
  private String creatorId;

  /**
   * 产品状态
   */
  private Byte state;

  /**
   * 说明
   */
  @TableField("`describe`")
  private String describe;

  /**
   * 数据存储策略
   */
  @TableField("store_policy")
  private String storePolicy;

  /**
   * 传输协议: MQTT,COAP,UDP
   */
  @TableField("transport_protocol")
  private String transportProtocol;

  /**
   * 图片地址
   */
  @TableField("photo_url")
  private String photoUrl;

  /**
   * 创建时间
   */
  /**
   * 协议配置
   */
  private String configuration;

  /**
   * 数据存储策略配置
   */
  @TableField("store_policy_configuration")
  private String storePolicyConfiguration;

  /**
   * 物模型
   */
  private String metadata;

  /**
   * 第三方平台产品下发信息
   */
  private String thirdDownRequest;
  @TableField("instance")
  private String instance;
}
