package org.springblade.modules.iot.core.engine.parsing.ast.binary;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.BinaryOperation;
import org.springblade.modules.iot.core.engine.parsing.ast.Expression;

/** << */
public class LShiftOperation extends BinaryOperation {

  public LShiftOperation(Expression leftOperand, Span span, Expression rightOperand) {
    super(leftOperand, span, rightOperand);
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    compiler
        .visit(getLeftOperand())
        .visit(getRightOperand())
        .lineNumber(getSpan())
        .bit("left_shift");
  }
}
