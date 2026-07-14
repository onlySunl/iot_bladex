

package org.springblade.modules.iot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_subscribe")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceSubscribe extends CustomBaseEntity {

  /** 消息类别：属性（PROPERTIES），指令（REPLY），事件（EVENT），上下线（EVENT：online,offline），所有 */
  @TableField("msg_type")
  private String msgType;

  /** 订阅级别：设备级，产品级 */
  @TableField("sub_type")
  private String subType;

  /** 设备deviceId */
  @TableField("device_id")
  private String deviceId;

  /** 产品ID或者设备唯一标识 */
  @TableField("product_key")
  private String productKey;

  /** 产品ID或者设备唯一标识 */
  @TableField("iot_id")
  private String iotId;

  /** 订阅地址 */
  private String url;

  /** 主题 */
  private String topic;

  /** 创建时间 */
  @TableField("create_date")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDate;

  /** 创建人 */
  private String creater;

  /** 实例编号(应用标识) */
  @TableField("`instance`")
  private String instance;

  /** 是否启用 */
  private Boolean enabled;
}
