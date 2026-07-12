package org.springblade.modules.iot.core.engine.parsing.ast;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;

public interface VariableSetter {

  default void compile_visit_variable(MagicScriptCompiler compiler) {
    throw new UnsupportedOperationException("暂不支持编译" + this.getClass().getSimpleName());
  }
}
