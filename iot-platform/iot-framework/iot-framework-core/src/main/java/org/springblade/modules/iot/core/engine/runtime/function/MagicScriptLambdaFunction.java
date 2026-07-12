package org.springblade.modules.iot.core.engine.runtime.function;

import org.springblade.modules.iot.core.engine.runtime.Variables;

@FunctionalInterface
public interface MagicScriptLambdaFunction {

  Object apply(Variables variables, Object[] args);
}
