package org.springblade.core.kafka.error;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;

/**
 * 通用 {@link KafkaListenerErrorHandler} ── 仅日志输出,异常吞掉。
 *
 * <p>由 {@code KafkaConsumerAutoConfiguration} 注册,bean 名 {@code myKafkaListenerErrorHandler}
 * 兼容老业务 {@code @KafkaListener(errorHandler="myKafkaListenerErrorHandler")} 引用。
 *
 * @author mqttsnet
 */
@Slf4j
public class KafkaListenerLoggingErrorHandler implements KafkaListenerErrorHandler {

    @Override
    @NonNull
    public Object handleError(@NonNull Message<?> message, @NonNull ListenerExecutionFailedException exception) {
        log.error("[kafka.listener] error handling message: {}", message, exception);
        return new Object();
    }

    @Override
    @NonNull
    public Object handleError(@NonNull Message<?> message, @NonNull ListenerExecutionFailedException exception,
                              Consumer<?, ?> consumer) {
        log.error("[kafka.listener] error handling message: {} consumerGroup={}",
            message, consumer.groupMetadata(), exception);
        return KafkaListenerErrorHandler.super.handleError(message, exception, consumer);
    }
}
