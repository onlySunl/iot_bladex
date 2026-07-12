package org.springblade.modules.iot.core.engine.runtime;

import org.springblade.modules.iot.core.engine.MagicScriptContext;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.runtime.function.MagicScriptLambdaFunction;
import java.util.List;

public abstract class MagicScriptRuntime {

  protected MagicScriptContext context;

  private String[] varNames;

  private List<Span> spans;

  public abstract Object execute(MagicScriptContext context);

  public String[] getVarNames() {
    return varNames;
  }

  public void setVarNames(String[] varNames) {
    this.varNames = varNames;
  }

  public void setSpans(List<Span> spans) {
    this.spans = spans;
  }

  public Span getSpan(int index) {
    return spans.get(index);
  }

  public List<Span> getSpans() {
    return spans;
  }

  protected MagicScriptLambdaFunction createLambda(
      MagicScriptLambdaFunction function, Variables variables) {
    return (var, arguments) -> function.apply(variables, arguments);
  }
}
