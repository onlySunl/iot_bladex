

package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.modules.iot.common.annotation.Excel;

import java.util.Date;
@TableName("iot_user_application")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTUserApplication extends CustomBaseEntity {

  //  @TableField("uuid")
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
