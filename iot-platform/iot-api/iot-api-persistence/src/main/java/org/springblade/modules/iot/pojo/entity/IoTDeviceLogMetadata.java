

package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_log_metadata")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceLogMetadata extends CustomBaseEntity {

  @TableField("iot_id")
  private String iotId;

  /** 产品唯一标识 */
  @TableField("product_key")
  private String productKey;

  /** 设备名称 */
  @TableField("device_name")
  private String deviceName;

  @TableField("device_id")
  private String deviceId;

  /** 消息类型 */
  @TableField("message_type")
  private String messageType;

  @TableField
  private String event;

  /** 属性 */
  private String property;

  private String ext1;

  private String ext2;

  private String ext3;

  /** 发生时间 */

  /** 其他 */
  private String content;
}
