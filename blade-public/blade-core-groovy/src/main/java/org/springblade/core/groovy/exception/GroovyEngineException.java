package org.springblade.core.groovy.exception;

/**
 * groovy脚本引擎异常
 *
 * @author mqttsnet 2025/03/18 13:35
 */
public class GroovyEngineException extends RuntimeException {

    public GroovyEngineException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public GroovyEngineException(String message) {
        super(message);
    }

}
