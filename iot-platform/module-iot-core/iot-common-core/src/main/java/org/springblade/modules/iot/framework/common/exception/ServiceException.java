package org.springblade.modules.iot.framework.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ServiceException adapter - wraps BladeX BusinessException.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {
    private final Integer code;
    private String msg;

    public ServiceException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
