

package org.springblade.modules.iot.persistence.entity.vo;

import org.springblade.modules.iot.pojo.entity.RulegoChain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rulego规则链VO
 *
 * @author gitee.com/NexIoT
 * @since 2025/01/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema
public class RulegoChainVO extends RulegoChain {

  @Schema(description = "状态描述")
  private String statusDesc;

  @Schema(description = "设计器URL")
  private String designerUrl;
}
