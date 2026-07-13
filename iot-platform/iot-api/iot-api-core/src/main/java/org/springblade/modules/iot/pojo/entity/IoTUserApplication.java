

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
import org.springblade.modules.iot.common.annotation.Excel;

@TableName("iot_user_application")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTUserApplication extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;


  @TableField(value = "union_id")
  @AutoColumn(comment = "private Long uuid;", length = 128, defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "用户唯一标识")
  private String unionId;

  /** 调用凭证APPID */
  @TableField(value = "app_id")
  @AutoColumn(comment = "调用凭证APPID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "调用凭证APPID")
  private String appId;

  /** MQ上行主题，英文逗号分隔 */
  @TableField(value = "up_topic")
  @AutoColumn(comment = "MQ上行主题，英文逗号分隔", length = 255, defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "MQ上行主题")
  private String upTopic;

  /** MQ下行主题，英文逗号分隔 */
  @TableField(value = "down_topic")
  @AutoColumn(comment = "MQ下行主题，英文逗号分隔", length = 255, defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "MQ下行主题")
  private String downTopic;

  /** 调用密钥 */
  @TableField(value = "app_secret")
  @AutoColumn(comment = "调用密钥", length = 128, defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "调用密钥")
  private String appSecret;

  /** 授权结束时间 */
  @TableField(value = "valid_end_date")
  @AutoColumn(comment = "授权结束时间", defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "授权结束时间")
  private Date validEndDate;

  /** 授权范围 */
  @Excel(name = "授权范围")
  private String scope;

  /** 0-正常，1-停用 */
  @TableField(value = "app_status")
  @AutoColumn(comment = "0-正常，1-停用", defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "应用状态 0-正常，1-停用")
  private Integer appStatus;

  /** 0-正常，1-删除 */
  @Excel(name = "是否删除 0正常 1删除")
  private Integer deleted;

  @TableField(value = "notify_url")
  @AutoColumn(comment = "0-正常，1-删除", length = 255, defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "推送地址")
  private String notifyUrl;

  @TableField(value = "app_name")
  @AutoColumn(comment = "appName", length = 128, defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "应用名称")
  private String appName;

  @TableField(value = "app_unique_id")
  @AutoColumn(comment = "appUniqueId", length = 128, defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "应用唯一标识")
  private String appUniqueId;

  @TableField(value = "instance")
  @AutoColumn(comment = "instance", length = 255, defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "实例名称")
  private String instance;

  @TableField(value = "remark")
  @ColumnType("text")
  @AutoColumn(comment = "remark", defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "描述")
  private String remark;

@TableField(value = "create_date")
  @Excel(name = "创建时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate;

  @TableField(value = "http_enable")
  @AutoColumn(comment = "httpEnable", defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "http启用")
  private Boolean httpEnable;

  @TableField(value = "mqtt_enable")
  @AutoColumn(comment = "mqttEnable", defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "mqtt启用")
  private Boolean mqttEnable;

  private String cfg;
}
