

package org.springblade.modules.iot.rule.rulego.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rulego触发请求
 *
 * @author gitee.com/NexIoT
 * @since 2025/01/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RulegoTriggerRequest {

  /** 规则链ID */
  private String chainId;

  /** 触发数据 */
  private Object data;

  /** 元数据 */
  private Object metadata;
}
