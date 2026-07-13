

package org.springblade.modules.iot.rule.parser;

import org.springblade.modules.iot.rule.enums.ParserFormat;
import org.springblade.modules.iot.rule.model.RuleParserResult;

/**
 * 模型解析器策略 @Author gitee.com/NexIoT
 *
 * @since 2025/12/3 9:07
 */
public interface RuleModelParserStrategy {

  ParserFormat getFormat();

  RuleParserResult parse(String modelDefineString);
}
