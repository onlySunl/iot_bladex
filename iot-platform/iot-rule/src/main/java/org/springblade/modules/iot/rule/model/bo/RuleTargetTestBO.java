

package org.springblade.modules.iot.rule.model.bo;

import org.springblade.modules.iot.rule.model.RuleTarget;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * todo @Author gitee.com/NexIoT
 *
 * @since 2023/1/18 15:36
 */
@Data
@Schema
public class RuleTargetTestBO {

  public RuleTarget ruleTarget;

  public String param;
}
