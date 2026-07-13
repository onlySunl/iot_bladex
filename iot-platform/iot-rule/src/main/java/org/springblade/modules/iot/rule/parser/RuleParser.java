

package org.springblade.modules.iot.rule.parser;

import org.springblade.modules.iot.rule.enums.ParserFormat;
import org.springblade.modules.iot.rule.model.RuleParserResult;
import java.util.List;

/**
 * 规则解析器 @Author gitee.com/NexIoT
 *
 * @since 2025/12/3 9:07
 */
public interface RuleParser {

  /**
   * 解析指定格式的模型字符为规则模型
   *
   * @param format 模型格式
   * @param modelDefineString 字符模型
   * @return 规则模型
   */
  RuleParserResult parse(ParserFormat format, String modelDefineString);

  /**
   * @return 全部支持的模型格式
   */
  List<ParserFormat> getAllSupportFormat();
}
