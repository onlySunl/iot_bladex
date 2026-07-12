package org.springblade.modules.iot.core.engine.parsing.ast.literal;

import org.springblade.modules.iot.core.engine.compile.MagicScriptCompiler;
import org.springblade.modules.iot.core.engine.parsing.Span;
import org.springblade.modules.iot.core.engine.parsing.ast.Literal;
import java.math.BigDecimal;

/** int常量 */
public class BigDecimalLiteral extends Literal {

  public BigDecimalLiteral(Span literal) {
    super(literal, literal.getText().substring(0, literal.getText().length() - 1).replace("_", ""));
  }

  @Override
  public void compile(MagicScriptCompiler compiler) {
    compiler
        .typeInsn(NEW, BigDecimal.class)
        .insn(DUP)
        .ldc(value)
        .invoke(INVOKESPECIAL, BigDecimal.class, "<init>", void.class, String.class);
  }
}
