package org.springblade.modules.iot.core.engine.parsing.ast.binary;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.BinaryOperation;
import org.springblade.modules.iot.core.engine.parsing.ast.Expression;

/** ==、===操作 */
public class EqualOperation extends BinaryOperation {

  protected final boolean accurate;

  public EqualOperation(
      Expression leftOperand, Span span, Expression rightOperand, boolean accurate) {
    super(leftOperand, span, rightOperand);
    this.accurate = accurate;
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    compiler
        .visit(getLeftOperand())
        .visit(getRightOperand())
        .lineNumber(getSpan())
        .operator(accurate ? "accurate_equals" : "equals");
  }
}
