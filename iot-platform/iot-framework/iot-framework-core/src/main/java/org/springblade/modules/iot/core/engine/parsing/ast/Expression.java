package org.springblade.modules.iot.core.engine.parsing.ast;

import org.springblade.modules.iot.core.engine.parsing.Span;

/** 表达式 */
public abstract class Expression extends Node {

  public Expression(Span span) {
    super(span);
  }
}
