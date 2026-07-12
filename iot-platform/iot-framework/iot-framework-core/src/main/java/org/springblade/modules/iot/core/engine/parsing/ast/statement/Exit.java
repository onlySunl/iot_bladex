package org.springblade.modules.iot.core.engine.parsing.ast.statement;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.exception.MagicExitException;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.Expression;
import org.springblade.modules.iot.core.engine.parsing.ast.Node;
import org.springblade.modules.iot.core.engine.runtime.ExitValue;
import java.util.List;

public class Exit extends Node {

  private final List<Expression> expressions;

  public Exit(Span span, List<Expression> expressions) {
    super(span);
    this.expressions = expressions;
  }

  @Override
  public void visitMethod(MagicScriptCompiler compiler) {
    expressions.forEach(it -> it.visitMethod(compiler));
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    compiler
        .typeInsn(NEW, MagicExitException.class)
        .insn(DUP)
        .typeInsn(NEW, ExitValue.class)
        .insn(DUP);
    if (expressions == null) {
      compiler.invoke(INVOKESPECIAL, ExitValue.class, "<init>", void.class);
    } else {
      compiler
          .newArray(expressions)
          .invoke(INVOKESPECIAL, ExitValue.class, "<init>", void.class, Object[].class);
    }
    compiler
        .invoke(INVOKESPECIAL, MagicExitException.class, "<init>", void.class, ExitValue.class)
        .insn(ATHROW);
  }
}
