

package org.springblade.modules.iot.core.engine.extend;

import cn.hutool.core.collection.CollUtil;
import org.springblade.modules.iot.core.engine.reflection.JavaReflection;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class FunctionRegistrar implements ApplicationContextAware {

  @Override
  public void setApplicationContext(ApplicationContext context) {
    Map<String, IdeMagicFunction> beansOfType = context.getBeansOfType(IdeMagicFunction.class);
    if (CollUtil.isNotEmpty(beansOfType)) {
      beansOfType.forEach((key, value) -> JavaReflection.registerFunction(value));
    }
  }
}
