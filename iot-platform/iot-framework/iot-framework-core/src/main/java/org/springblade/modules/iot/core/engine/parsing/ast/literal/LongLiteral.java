package org.springblade.modules.iot.core.engine.parsing.ast.literal;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.Literal;

/** long 常量 */
public class LongLiteral extends Literal {

  public LongLiteral(Span literal) {
    this(
        literal,
        Long.parseLong(
            literal.getText().substring(0, literal.getText().length() - 1).replace("_", "")));
  }

  public LongLiteral(Span span, Object value) {
    super(span);
    this.value = value;
  }

  @Override
  public void compile(MagicScriptCompiler context) {
    context.ldc(value).invoke(INVOKESTATIC, Long.class, "valueOf", Long.class, long.class);
  }
}
