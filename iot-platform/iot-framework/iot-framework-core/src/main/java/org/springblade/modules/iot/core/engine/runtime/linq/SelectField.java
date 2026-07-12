package org.springblade.modules.iot.core.engine.runtime.linq;

import org.springblade.modules.iot.core.engine.runtime.function.MagicScriptLambdaFunction;

public class SelectField {

  private final MagicScriptLambdaFunction function;

  private final String aliasName;

  private final int aliasIndex;

  public SelectField(MagicScriptLambdaFunction function, String aliasName, int aliasIndex) {
    this.function = function;
    this.aliasName = aliasName;
    this.aliasIndex = aliasIndex;
  }

  public MagicScriptLambdaFunction getFunction() {
    return function;
  }

  public String getAliasName() {
    return aliasName;
  }

  public boolean isWhole() {
    return aliasIndex == -1 && "*".equals(aliasName);
  }
}
