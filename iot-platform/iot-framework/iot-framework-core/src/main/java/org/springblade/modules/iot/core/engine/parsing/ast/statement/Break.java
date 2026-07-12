package org.springblade.modules.iot.core.engine.parsing.ast.statement;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.Node;

/** break 语句 */
public class Break extends Node {

  public Break(Span span) {
    super(span);
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    compiler.end();
  }
}
