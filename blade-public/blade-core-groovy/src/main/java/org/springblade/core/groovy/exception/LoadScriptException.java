package org.springblade.core.groovy.exception;

/**
 * 加载脚本异常
 *
 * @author mqttsnet 2025/03/18 16:26
 */
public class LoadScriptException extends RuntimeException {

    public LoadScriptException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public LoadScriptException(String message) {
        super(message);
    }
}
