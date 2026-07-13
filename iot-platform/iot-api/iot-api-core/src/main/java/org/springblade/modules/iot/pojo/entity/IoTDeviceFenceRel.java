

package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设备和围栏中间表 @Author gitee.com/NexIoT
 *
 * @since 2023/8/5 8:51
 */
@TableName("iot_device_fence_rel")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceFenceRel extends CustomBaseEntity {

  @Id private Long id;

  /** 围栏id */
@TableField(value = "fence_id")
@AutoColumn(comment = "围栏id", defaultValueType = DefaultValueEnum.NULL)
  private Long fenceId;

  /** 设备唯一标识符 */
@TableField(value = "iot_id")
@AutoColumn(comment = "设备唯一标识符", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String iotId;

  /** 设备序列号 */
@TableField(value = "device_id")
@AutoColumn(comment = "设备序列号", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceId;

  /** 创建人 */
@TableField(value = "creator_id")
@AutoColumn(comment = "创建人", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String creatorId;

  /** 创建时间 */
@TableField(value = "create_date")
@AutoColumn(comment = "创建时间", defaultValueType = DefaultValueEnum.NULL)
  private Date createDate;
}
