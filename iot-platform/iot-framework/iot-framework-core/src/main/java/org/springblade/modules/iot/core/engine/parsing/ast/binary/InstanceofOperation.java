package org.springblade.modules.iot.core.engine.parsing.ast.binary;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.functions.ObjectTypeConditionExtension;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.BinaryOperation;
import org.springblade.modules.iot.core.engine.parsing.ast.Expression;

/** instanceof */
public class InstanceofOperation extends BinaryOperation {

  public InstanceofOperation(Expression leftOperand, Span span, Expression rightOperand) {
    super(leftOperand, span, rightOperand);
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    compiler
        .visit(getLeftOperand())
        .visit(getRightOperand())
        .typeInsn(CHECKCAST, Class.class)
        .lineNumber(getSpan())
        .invoke(
            INVOKESTATIC,
            ObjectTypeConditionExtension.class,
            "is",
            boolean.class,
            Object.class,
            Class.class)
        .asBoolean();
  }
}
