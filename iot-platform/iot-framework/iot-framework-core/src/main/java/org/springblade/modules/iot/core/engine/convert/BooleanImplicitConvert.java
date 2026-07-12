package org.springblade.modules.iot.core.engine.convert;

import org.springblade.modules.iot.core.engine.parsing.ast.literal.BooleanLiteral;
import org.springblade.modules.iot.core.engine.runtime.Variables;

/** 任意值到boolean类型的隐式转换 */
public class BooleanImplicitConvert implements ClassImplicitConvert {

  @Override
  public boolean support(Class<?> from, Class<?> to) {
    return to == Boolean.class || to == boolean.class;
  }

  @Override
  public Object convert(Variables variables, Object source, Class<?> target) {
    return BooleanLiteral.isTrue(source);
  }
}
