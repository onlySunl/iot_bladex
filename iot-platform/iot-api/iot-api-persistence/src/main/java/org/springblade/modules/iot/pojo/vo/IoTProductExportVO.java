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

package org.springblade.modules.iot.pojo.vo;

import cn.universal.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTProductExportVO implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 主键ID
   */

  /**
   * 产品编号
   */
    @Excel(name = "产品编号")
  private String productId;

  /**
   * 产品KEY
   */
    @Excel(name = "产品KEY")
  private String productKey;

  /**
   * 产品密钥（1型1密）
   */
    private String productSecret;

  /**
   * 第三方平台:ctwing,onenet,alibaba,baidu
   */
    @Excel(name = "第三方平台:ctwing,onenet,alibaba,baidu")
  private String thirdPlatform;

  /**
   * 第三方平台配置信息
   */
    @Excel(name = "第三方平台配置信息")
  private String thirdConfiguration;

  /**
   * 厂商编号
   */
    @Excel(name = "厂商编号")
  private String companyNo;

  /**
   * 分类ID
   */
    //  @Excel(name = "分类ID")
  private String classifiedId;

  /**
   * 网络组件：关联network表
   */
    //  @Excel(name = "网络组件：关联network表")
  private String networkUnionId;

  /**
   * 设备类型: 网关，设备
   */
    @Excel(name = "设备类型: 网关，设备")
  private String deviceNode;

  /**
   * 所属项目
   */
    private String gwProductKey;

  /**
   * 分类名称
   */
    //  @Excel(name = "分类名称")
  private String classifiedName;

  /**
   * 消息协议:
   */
    //  @Excel(name = "消息协议")
  private String messageProtocol;

  /**
   * 名称
   */
  @Excel(name = "名称")
  private String name;

  /**
   * 创建者id
   */
    private String creatorId;

  /**
   * 产品状态
   */
  @Excel(name = "产品状态")
  private Byte state;

  /**
   * 说明
   */
    //  @Excel(name = "说明")
  private String describe;

  /**
   * 数据存储策略
   */
    @Excel(name = "存储策略")
  private String storePolicy;

  /**
   * 传输协议: MQTT,COAP,UDP
   */
    @Excel(name = "传输协议: MQTT,COAP,UDP")
  private String transportProtocol;

  /**
   * 图片地址
   */
    @Excel(name = "图片地址")
  private String photoUrl;

  /**
   * 协议配置
   */
    @Excel(name = "产品配置")
  private String configuration;

  /**
   * 创建时间
   */

  /**
   * 数据存储策略配置
   */
    @Excel(name = "数据存储策略配置")
  private String storePolicyConfiguration;

  /**
   * 物模型
   */
  @Excel(name = "物模型")
  private String metadata;

  /**
   * 第三方平台产品下发信息
   */
  @Excel(name = "第三方平台产品下发信息")
  private String thirdDownRequest;

  /**
   * 协议状态
   */
  @Excel(name = "协议状态 0停用  1启用")
  private Byte protocolState;

  /**
   * 协议类型
   */
  @Excel(name = "协议类型")
  private String protocolType;

  /**
   * 协议配置
   */
  @Excel(name = "编解码协议配置")
  private String protocolConfiguration;

  /**
   * 协议编解码示例
   */
  @Excel(name = "协议编解码示例")
  private String example;
}
