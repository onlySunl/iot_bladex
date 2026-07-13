

package org.springblade.modules.iot.rule.engine;

import cn.hutool.json.JSONObject;

/**
 * todo @Author gitee.com/NexIoT
 *
 * @since 2025/12/3 9:36
 */
public interface RuleEngine {

  JSONObject executeRule(JSONObject param, String modelDefineString, String appId);
}
