package org.springblade.modules.iot.core.engine.parsing.ast.binary;

import org.springblade.modules.iot.core.engine.asm.Label;
import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.BinaryOperation;
import org.springblade.modules.iot.core.engine.parsing.ast.Expression;
import org.springblade.modules.iot.core.engine.runtime.handle.OperatorHandle;

/** || 操作 */
public class OrOperation extends BinaryOperation {

  public OrOperation(Expression leftOperand, Span span, Expression rightOperand) {
    super(leftOperand, span, rightOperand);
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    Label end = new Label();
    compiler
        .visit(getLeftOperand())
        .insn(DUP)
        .invoke(INVOKESTATIC, OperatorHandle.class, "isTrue", boolean.class, Object.class)
        .jump(IFNE, end)
        .insn(POP)
        .visit(getRightOperand())
        .label(end);
  }
}
