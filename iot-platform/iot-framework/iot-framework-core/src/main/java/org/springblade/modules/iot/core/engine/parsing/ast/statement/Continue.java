package org.springblade.modules.iot.core.engine.parsing.ast.statement;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.Node;

/** continue语句 */
public class Continue extends Node {

  public Continue(Span span) {
    super(span);
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    compiler.start();
  }
}
