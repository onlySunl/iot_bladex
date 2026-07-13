

package org.springblade.modules.iot.rule.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * todo @Author gitee.com/NexIoT
 *
 * @since 2023/1/14 16:02
 */
@Data
@Schema
public class RuleConfig {

  @Schema(description = "查询字段")
  private String fields;

  @Schema(description = "应用id")
  private String appId;

  @Schema(description = "过滤条件")
  private String condition;

  @Schema(description = "执行sql")
  private List<RuleTarget> targets;

  public String getSql() {
    return String.format(
        "select %s from %s %s",
        fields, appId, StringUtils.isEmpty(condition) ? "" : " where " + condition);
  }
}
