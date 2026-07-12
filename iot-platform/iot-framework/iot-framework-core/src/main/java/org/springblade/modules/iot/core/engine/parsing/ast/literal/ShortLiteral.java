package org.springblade.modules.iot.core.engine.parsing.ast.literal;

import org.springblade.modules.iot.core.engine.MagicScriptError;
import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.Literal;

/** short 常量 */
public class ShortLiteral extends Literal {

  public ShortLiteral(Span literal) {
    super(literal);
    try {
      setValue(
          Short.parseShort(
              literal.getText().substring(0, literal.getText().length() - 1).replace("_", "")));
    } catch (NumberFormatException e) {
      MagicScriptError.error("定义short变量值不合法", literal, e);
    }
  }

  @Override
  public void compile(MagicScriptCompiler context) {
    context.ldc(value).invoke(INVOKESTATIC, Short.class, "valueOf", Short.class, short.class);
  }
}
