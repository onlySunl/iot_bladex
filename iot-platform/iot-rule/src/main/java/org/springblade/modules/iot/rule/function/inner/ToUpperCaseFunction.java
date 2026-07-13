

package org.springblade.modules.iot.rule.function.inner;

import org.springblade.modules.iot.rule.function.RuleFunction;
import java.util.Locale;
import org.springframework.stereotype.Component;

/**
 * todo @Author gitee.com/NexIoT
 *
 * @since 2025/12/3 13:58
 */
@Component
public class ToUpperCaseFunction implements RuleFunction {

  @Override
  public String functionName() {
    return "toUpperCase";
  }

  @Override
  public Object executeFunction(Object[] param) {
    return param[0].toString().toUpperCase(Locale.ROOT);
  }
}
