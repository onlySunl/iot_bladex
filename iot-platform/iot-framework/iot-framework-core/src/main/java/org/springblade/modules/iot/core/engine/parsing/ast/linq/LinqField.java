package org.springblade.modules.iot.core.engine.parsing.ast.linq;

import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.VarIndex;
import org.springblade.modules.iot.core.engine.parsing.ast.Expression;
import org.springblade.modules.iot.core.engine.parsing.ast.VariableSetter;

public class LinqField extends LinqExpression implements VariableSetter {

  private final String aliasName;

  private final VarIndex varIndex;

  public LinqField(Span span, Expression expression, VarIndex alias) {
    super(span, expression);
    this.aliasName = alias != null ? alias.getName() : expression.getSpan().getText();
    this.varIndex = alias;
  }

  public VarIndex getVarIndex() {
    return varIndex;
  }

  public String getAlias() {
    return aliasName;
  }
}
