package org.springblade.modules.iot.core.engine.exception;

public class DebugTimeoutException extends RuntimeException {

  public DebugTimeoutException() {
    super("debug超时");
  }

  public DebugTimeoutException(Throwable cause) {
    super(cause);
  }
}
