package org.springblade.modules.iot.core.engine.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String module) {
    super(module);
  }
}
