

package org.springblade.modules.iot.rule.function;

/**
 * todo @Author gitee.com/NexIoT
 *
 * @since 2025/12/3 13:38
 */
public interface RuleFunction {

  String functionName();

  Object executeFunction(Object[] param);
}
