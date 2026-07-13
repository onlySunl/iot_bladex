

package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("iot_device_log_metadata")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceLogMetadata extends CustomBaseEntity {


  @TableField(value = "iot_id")
  @AutoColumn(comment = "iotId", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String iotId;

  /** 产品唯一标识 */
  @TableField(value = "product_key")
  @AutoColumn(comment = "产品唯一标识", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productKey;

  /** 设备名称 */
  @Column(name = "device_name", length = 64)
  private String deviceName;

  @Column(name = "device_id", length = 64)
  private String deviceId;

  /** 消息类型 */
  @TableField(value = "message_type")
  @ColumnType("text")
  @AutoColumn(comment = "消息类型", defaultValueType = DefaultValueEnum.NULL)
  private String messageType;

  @Column(length = 32)
  private String event;

  /** 属性 */
  private String property;

  private String ext1;

  private String ext2;

  private String ext3;


  /** 其他 */
  private String content;

  private static final long serialVersionUID = 1L;
}
