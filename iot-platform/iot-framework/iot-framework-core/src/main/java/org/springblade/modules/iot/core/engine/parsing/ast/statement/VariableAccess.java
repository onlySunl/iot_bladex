package org.springblade.modules.iot.core.engine.parsing.ast.statement;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.VarIndex;
import org.springblade.modules.iot.core.engine.parsing.ast.Expression;
import org.springblade.modules.iot.core.engine.parsing.ast.VariableSetter;

public class VariableAccess extends Expression implements VariableSetter {

  private final VarIndex varIndex;

  public VariableAccess(Span name, VarIndex varIndex) {
    super(name);
    this.varIndex = varIndex;
  }

  public VarIndex getVarIndex() {
    return varIndex;
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    compiler.load(varIndex);
  }
}
