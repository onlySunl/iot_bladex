

package org.springblade.modules.iot.rule.enums;

public enum RunStatus {
  exist_success((byte) 0),
  success((byte) 1),
  error((byte) 2);

  public byte code;

  RunStatus(byte code) {
    this.code = code;
  }
}
