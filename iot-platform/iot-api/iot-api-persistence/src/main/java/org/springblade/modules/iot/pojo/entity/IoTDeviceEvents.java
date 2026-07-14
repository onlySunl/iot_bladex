

package org.springblade.modules.iot.pojo.entity;

import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_events")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class IoTDeviceEvents extends CustomBaseEntity {

  /** 事件标识 */

  /** 事件名称 */
  private String name;

  /** 事件级别 */
  private String level;

  /** 描述 */
  private String description;

  /** 事件总数 */
  private String qty;

  /** 最新事件上报时间 */
  private String time;

  // 是否设置存储策略
  private boolean storagePolicy;
}
