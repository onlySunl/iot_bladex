package org.springblade.modules.iot.core.engine.parsing.ast.statement;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.exception.MagicScriptRuntimeException;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.Expression;
import org.springblade.modules.iot.core.engine.parsing.ast.Node;

public class Throw extends Node {

  private final Expression expression;

  public Throw(Span span, Expression expression) {
    super(span);
    this.expression = expression;
  }

  @Override
  public void visitMethod(MagicScriptCompiler compiler) {
    expression.visitMethod(compiler);
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    compiler
        .visit(expression)
        .invoke(
            INVOKESTATIC,
            MagicScriptRuntimeException.class,
            "create",
            MagicScriptRuntimeException.class,
            Object.class)
        .insn(ATHROW);
  }
}
