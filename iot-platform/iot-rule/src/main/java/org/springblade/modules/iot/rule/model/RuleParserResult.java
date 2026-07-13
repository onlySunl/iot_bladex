

package org.springblade.modules.iot.rule.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * todo @Author gitee.com/NexIoT
 *
 * @since 2025/12/2 9:02
 */
@Data
public class RuleParserResult {

  private List<RuleField> fields;

  private List<String> topics;

  private String condition;

  @Data
  @AllArgsConstructor
  public static class RuleField {

    private String name;
    private String alias;
  }
}
