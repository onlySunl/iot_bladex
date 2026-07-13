

package org.springblade.modules.iot.rule.function.inner;

import org.springblade.modules.iot.rule.function.RuleFunction;
import org.springblade.modules.iot.rule.utils.ThreadLocalUtils;
import org.springframework.stereotype.Component;

/**
 * todo @Author gitee.com/NexIoT
 *
 * @since 2025/12/3 13:58
 */
@Component
public class DeviceNameFunction implements RuleFunction {

  @Override
  public String functionName() {
    return "deviceName";
  }

  @Override
  public Object executeFunction(Object[] param) {
    return ThreadLocalUtils.get("deviceName");
  }
}
