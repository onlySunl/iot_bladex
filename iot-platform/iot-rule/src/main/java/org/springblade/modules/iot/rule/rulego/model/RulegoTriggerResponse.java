

package org.springblade.modules.iot.rule.rulego.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rulego触发响应
 *
 * @author gitee.com/NexIoT
 * @since 2025/01/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RulegoTriggerResponse {

  /** 是否成功 */
  private boolean success;

  /** 响应消息 */
  private String message;

  /** 响应代码 */
  private String code;

  /** 执行结果 */
  private Object data;

  /** 执行ID */
  private String executionId;
}
