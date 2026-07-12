package org.springblade.modules.iot.core.engine.parsing.ast.linq;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.Literal;

public class WholeLiteral extends Literal {

  public WholeLiteral(Span span) {
    super(span);
  }

  public WholeLiteral(Span span, Object value) {
    super(span, value);
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    compiler.load2();
  }
}
