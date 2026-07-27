package org.springblade.modules.iot.broker.mqtt.exception;

import org.springblade.core.log.exception.ServiceException;

import java.io.Serial;

/**
 * BifroMQ session not found 专属异常(broker 明确 404)。
 * 区别于普通 {@link ServiceException}(broker 临时异常/超时);调用方据此判定真离线 vs 状态未知。
 *
 * @author mqttsnet
 */
public class SessionNotFoundException extends ServiceException {

    @Serial
    private static final long serialVersionUID = -7621932158420917388L;

    public SessionNotFoundException(String message) {
        super(message);
    }

    public SessionNotFoundException(String message, Throwable cause) {
        super(message);
    }
}
