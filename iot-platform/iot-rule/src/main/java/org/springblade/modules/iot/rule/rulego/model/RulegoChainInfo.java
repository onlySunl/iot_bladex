

package org.springblade.modules.iot.rule.rulego.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rulego规则链信息
 *
 * @author gitee.com/NexIoT
 * @since 2025/01/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RulegoChainInfo {

  /** 规则链ID */
  private String id;

  /** 规则链名称 */
  private String name;

  private boolean root;

  /** 规则链描述 */
  private String description;

  /** 规则链状态 */
  private String status;

  /** 规则链DSL */
  private String dsl;

  /** 创建时间 */
  private String createTime;

  /** 更新时间 */
  private String updateTime;
}
