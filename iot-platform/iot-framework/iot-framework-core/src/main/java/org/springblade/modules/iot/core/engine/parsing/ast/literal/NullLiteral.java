package org.springblade.modules.iot.core.engine.parsing.ast.literal;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.Literal;

/** null 常量 */
public class NullLiteral extends Literal {

  public NullLiteral(Span span) {
    super(span);
  }

  @Override
  public void compile(MagicScriptCompiler context) {
    context.insn(ACONST_NULL);
  }
}
