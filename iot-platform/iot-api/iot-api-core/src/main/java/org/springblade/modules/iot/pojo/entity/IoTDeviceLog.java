

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

@TableName("iot_device_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceLog extends CustomBaseEntity {


  /** 唯一编码 */
  @Column(name = "iot_id", length = 128)
  private String iotId;

  /** 设备自身序号 */
  @TableField(value = "device_id")
  @AutoColumn(comment = "设备自身序号", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceId;

  /** 产品ID */
  @TableField(value = "product_key")
  @AutoColumn(comment = "产品ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productKey;

  /** 设备名称 */
  @TableField(value = "device_name")
  @AutoColumn(comment = "设备名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceName;

  /** 消息类型 */
  @TableField(value = "message_type")
  @ColumnType("text")
  @AutoColumn(comment = "消息类型", defaultValueType = DefaultValueEnum.NULL)
  private String messageType;

  /** 指令ID */
  @TableField(value = "command_id")
  @AutoColumn(comment = "指令ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String commandId;

  /** 指令ID */
  @TableField(value = "command_status")
  @AutoColumn(comment = "指令ID", defaultValueType = DefaultValueEnum.NULL)
  private Integer commandStatus;

  /** 事件名称 */
  private String event;


  /** 内容 */
  private String content;

  private String point;
  private static final long serialVersionUID = 1L;
}
