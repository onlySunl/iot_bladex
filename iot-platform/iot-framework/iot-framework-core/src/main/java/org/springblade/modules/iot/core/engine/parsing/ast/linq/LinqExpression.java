package org.springblade.modules.iot.core.engine.parsing.ast.linq;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.Expression;
import org.springblade.modules.iot.core.engine.parsing.ast.statement.MemberAccess;

public class LinqExpression extends Expression {

  private final Expression expression;

  private String methodName;

  public LinqExpression(Expression expression) {
    this(expression.getSpan(), expression);
  }

  public LinqExpression(Span span, Expression expression) {
    super(span);
    this.expression = expression;
  }

  @Override
  public void visitMethod(MagicScriptCompiler compiler) {
    expression.visitMethod(compiler);
    if (!(expression instanceof WholeLiteral)) {
      this.methodName =
          compiler.visitMethod(
              "linq_expression",
              () ->
                  compiler
                      .compile(
                          expression instanceof MemberAccess
                                  && ((MemberAccess) expression).isWhole()
                              ? ((MemberAccess) expression).getObject()
                              : expression)
                      .insn(ARETURN));
    }
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    if (methodName != null) {
      compiler.load0().lambda(methodName);
    } else {
      compiler.insn(ACONST_NULL);
    }
  }

  public Expression getExpression() {
    return expression;
  }
}
