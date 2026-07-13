

package org.springblade.modules.iot.databridge.core.exception;

/**
 * 数据桥接异常
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/15
 */
public class DataBridgeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String errorCode;

  public DataBridgeException(String message) {
    super(message);
  }

  public DataBridgeException(String errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public DataBridgeException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataBridgeException(String errorCode, String message, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }
}
