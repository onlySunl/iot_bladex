

package org.springblade.modules.iot.pojo.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.common.entity.CustomBaseEntity;
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

  /** 围栏id */
  private Long fenceId;

  /** 设备唯一标识符 */
  private String iotId;

  /** 设备序列号 */
  private String deviceId;

  /** 创建人 */
  private String creatorId;

  /** 创建时间 */
  private Date createDate;
}
