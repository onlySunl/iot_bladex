package org.springblade.core.mvc.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author Chill
 */
@Getter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int code;

    public ServiceException(String message) {
        super(message);
        this.code = 500;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500;
    }

    public static ServiceException of(String message) {
        return new ServiceException(message);
    }

    public static ServiceException of(int code, String message) {
        return new ServiceException(code, message);
    }
}
