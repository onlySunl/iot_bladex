

package org.springblade.modules.iot.databridge.engine;

import java.util.List;
import java.util.Map;

/**
 * 模板引擎接口
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/15
 */
public interface TemplateEngine {

  /** 处理模板 */
  String process(String template, Map<String, Object> variables);

  /** 验证模板语法 */
  Boolean validateTemplate(String template);

  /** 获取支持的模板类型 */
  List<String> getSupportedTypes();
}
