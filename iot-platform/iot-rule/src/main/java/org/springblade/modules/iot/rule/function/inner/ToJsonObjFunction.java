

package org.springblade.modules.iot.rule.function.inner;

import cn.hutool.json.JSONUtil;
import org.springblade.modules.iot.rule.function.RuleFunction;
import org.springframework.stereotype.Component;

/**
 * todo @Author gitee.com/NexIoT
 *
 * @since 2023/1/17 9:27
 */
@Component
public class ToJsonObjFunction implements RuleFunction {

  @Override
  public String functionName() {
    return "toJsonObj";
  }

  @Override
  public Object executeFunction(Object[] param) {
    return JSONUtil.parseObj(param[0].toString());
  }
}
