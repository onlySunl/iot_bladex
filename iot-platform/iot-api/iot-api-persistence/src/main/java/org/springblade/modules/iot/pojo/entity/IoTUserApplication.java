

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
  private String unionId;

  /** 调用凭证APPID */
  @TableField("app_id")
  private String appId;

  /** MQ上行主题，英文逗号分隔 */
  @TableField("up_topic")
  private String upTopic;

  /** MQ下行主题，英文逗号分隔 */
  @TableField("down_topic")
  private String downTopic;

  /** 调用密钥 */
  @TableField("app_secret")
  private String appSecret;

  /** 授权结束时间 */
  @TableField("valid_end_date")
  private Date validEndDate;

  /** 授权范围 */
  private String scope;

  /** 0-正常，1-停用 */
  @TableField("app_status")
  private Integer appStatus;

  /** 0-正常，1-删除 */
  private Integer deleted;

  @TableField("notify_url")
  private String notifyUrl;

  @TableField("app_name")
  private String appName;

  @TableField("instance")
  private String instance;

  @TableField("remark")
  private String remark;

  @TableField("create_date")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate;

  @TableField("http_enable")
  private Boolean httpEnable;

  @TableField("mqtt_enable")
  private Boolean mqttEnable;

  private String cfg;
}
