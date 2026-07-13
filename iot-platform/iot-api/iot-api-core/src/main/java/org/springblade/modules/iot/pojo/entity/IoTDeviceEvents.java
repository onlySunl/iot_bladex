

package org.springblade.modules.iot.pojo.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class IoTDeviceEvents implements Serializable {

  /** 事件标识 */
  private String id;

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
