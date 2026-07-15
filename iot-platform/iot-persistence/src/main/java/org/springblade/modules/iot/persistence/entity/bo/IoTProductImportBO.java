

package org.springblade.modules.iot.persistence.entity.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTProductImportBO implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 产品编号 */
  @Column(name = "product_id")
  private String productId;

  /** 产品KEY */
  @Column(name = "product_key")
  private String productKey;

  /** 产品密钥（1型1密） */
  @Column(name = "product_secret")
  private String productSecret;

  /** 第三方平台:ctwing,onenet,alibaba,baidu */
  @Column(name = "third_platform")
  private String thirdPlatform;

  /** 第三方平台配置信息 */
  @Column(name = "third_configuration")
  private String thirdConfiguration;

  /** 厂商编号 */
  @Column(name = "company_no")
  private String companyNo;

  /** 分类ID */
  @Column(name = "classified_id")
  private String classifiedId;

  /** 网络组件：关联network表 */
  @Column(name = "network_union_id")
  private String networkUnionId;

  /** 设备类型: 网关，设备 */
  @Column(name = "device_node")
  private String deviceNode;

  /** 网关ProductKey */
  @Column(name = "gw_product_key")
  private String gwProductKey;

  /** 分类名称 */
  @Column(name = "classified_name")
  private String classifiedName;

  /** 消息协议: */
  @Column(name = "message_protocol")
  private String messageProtocol;

  /** 名称 */
  private String name;

  /** 创建者id */
  @Column(name = "creator_id")
  private String creatorId;

  /** 产品状态 */
  private Byte state;

  /** 说明 */
  @Column(name = "`describe`")
  private String describe;

  /** 数据存储策略 */
  @Column(name = "store_policy")
  private String storePolicy;

  /** 传输协议: MQTT,COAP,UDP */
  @Column(name = "transport_protocol")
  private String transportProtocol;

  /** 图片地址 */
  @Column(name = "photo_url")
  private String photoUrl;

  /** 创建时间 */
  @Column(name = "create_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  /** 协议配置 */
  private String configuration;

  /** 数据存储策略配置 */
  @Column(name = "store_policy_configuration")
  private String storePolicyConfiguration;

  /** 物模型 */
  private String metadata;

  /** 第三方平台产品下发信息 */
  private String thirdDownRequest;

  /** 协议状态 */
  private Byte protocolState;

  /** 协议类型 */
  private String protocolType;

  /** 协议配置 */
  private String protocolConfiguration;

  /** 协议编解码示例 */
  private String protocolExample;
}
