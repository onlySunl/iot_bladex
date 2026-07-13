/*
 *
 *
 *
 *
 */

package org.springblade.modules.iot.pojo.entity;

import cn.universal.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_user_application")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTUserApplication extends CustomBaseEntity {

  //  private Long uuid;

  @TableField("union_id")
  @Excel(name = "用户唯一标识")
  private String unionId;

  /** 调用凭证APPID */
  @TableField("app_id")
  @Excel(name = "调用凭证APPID")
  private String appId;

  /** MQ上行主题，英文逗号分隔 */
  @TableField("up_topic")
  @Excel(name = "MQ上行主题")
  private String upTopic;

  /** MQ下行主题，英文逗号分隔 */
  @TableField("down_topic")
  @Excel(name = "MQ下行主题")
  private String downTopic;

  /** 调用密钥 */
  @TableField("app_secret")
  @Excel(name = "调用密钥")
  private String appSecret;

  /** 授权结束时间 */
  @TableField("valid_end_date")
  @Excel(name = "授权结束时间")
  private Date validEndDate;

  /** 授权范围 */
  @Excel(name = "授权范围")
  private String scope;

  /** 0-正常，1-停用 */
  @TableField("app_status")
  @Excel(name = "应用状态 0-正常，1-停用")
  private Integer appStatus;

  /** 0-正常，1-删除 */
  @Excel(name = "是否删除 0正常 1删除")
  private Integer deleted;

  @TableField("notify_url")
  @Excel(name = "推送地址")
  private String notifyUrl;

  @TableField("app_name")
  @Excel(name = "应用名称")
  private String appName;

  @Id
  @TableField("app_unique_id")
  @Excel(name = "应用唯一标识")
  private String appUniqueId;

  @TableField("instance")
  @Excel(name = "实例名称")
  private String instance;

  @TableField("remark")
  @Excel(name = "描述")
  private String remark;

  @TableField("create_date")
  @Excel(name = "创建时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate;

  @TableField("http_enable")
  @Excel(name = "http启用")
  private Boolean httpEnable;

  @TableField("mqtt_enable")
  @Excel(name = "mqtt启用")
  private Boolean mqttEnable;

  private String cfg;
}
