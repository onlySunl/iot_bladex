

package org.springblade.modules.iot.rule.rulego.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rulego保存请求
 *
 * @author gitee.com/NexIoT
 * @since 2025/01/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RulegoSaveRequest {

  /** 规则链ID */
  private String id;

  /** 规则链名称 */
  private String name;

  private boolean root = true;

  /** 规则链描述 */
  private String description;
}
