package org.springblade.modules.iot.base.exception;

import lombok.Getter;

/**
 * IoT 基础异常类
 *
 * @author Chill
 */
@Getter
public class IotException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误消息
     */
    private final String message;

    public IotException(String message) {
        super(message);
        this.code = 500;
        this.message = message;
    }

    public IotException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public IotException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

}
