package org.springblade.modules.iot.ota.service.statemachine.event;

import org.springblade.modules.iot.ota.service.statemachine.event.source.OtaTaskExecutionResultEventSource;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * OTA任务执行结果事件
 * <p>
 * 用于异步处理OTA升级任务的执行结果
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/08/22
 */
@Getter
public class OtaTaskExecutionResultEvent extends ApplicationEvent {

    public OtaTaskExecutionResultEvent(OtaTaskExecutionResultEventSource source) {
        super(source);
    }
}
