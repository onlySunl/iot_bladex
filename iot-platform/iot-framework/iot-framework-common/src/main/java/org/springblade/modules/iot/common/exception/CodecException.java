

package org.springblade.modules.iot.common.exception;

/** 编解码错误 */
public class CodecException extends Exception {

  private static final long serialVersionUID = 1L;

  private String msg;
  private int code = 758;

  public CodecException(String msg) {
    super(msg);
    this.msg = msg;
  }

  public CodecException(String msg, Throwable e) {
    super(msg, e);
    this.msg = msg;
  }

  public CodecException(String msg, int code) {
    super(msg);
    this.msg = msg;
    this.code = code;
  }

  public CodecException(String msg, int code, Throwable e) {
    super(msg, e);
    this.msg = msg;
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
