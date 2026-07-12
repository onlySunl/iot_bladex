package org.springblade.modules.iot.core.engine.parsing.ast.binary;

import org.springblade.modules.iot.core.engine.MagicScriptError;
import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.BinaryOperation;
import org.springblade.modules.iot.core.engine.parsing.ast.Expression;
import org.springblade.modules.iot.core.engine.parsing.ast.VariableSetter;
import org.springblade.modules.iot.core.engine.parsing.ast.statement.VariableAccess;

/** = 操作 */
public class AssigmentOperation extends BinaryOperation {

  public AssigmentOperation(Expression leftOperand, Span span, Expression rightOperand) {
    super(leftOperand, span, rightOperand);
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    if (getLeftOperand() instanceof VariableAccess) {
      compiler
          .pre_store(((VariableAccess) getLeftOperand()).getVarIndex())
          .compile(getRightOperand());
      if (getRightOperand() instanceof AssigmentOperation) {
        compiler.visit(((AssigmentOperation) getRightOperand()).getLeftOperand());
      }
      compiler.store(((VariableAccess) getLeftOperand()).getVarIndex());
    } else if (getLeftOperand() instanceof VariableSetter) {
      compiler.newRuntimeContext();
      ((VariableSetter) getLeftOperand()).compile_visit_variable(compiler);
      compiler.compile(getRightOperand()).call("set_variable_value", 4);
    } else {
      MagicScriptError.error("赋值目标应为变量", getLeftOperand().getSpan());
    }
  }
}
