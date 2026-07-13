

package org.springblade.modules.iot.persistence.shadow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设备影子
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/9/17
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Shadow {

  /** 状态 */
  private State state;

  private State metadata;

  private Long timestamp;

  private Long version;
}
