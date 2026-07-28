package org.springblade.modules.iot.ota.service.statemachine.event;

import org.springblade.modules.iot.ota.service.statemachine.event.source.OtaTaskExecutionEventSource;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * OTA任务执行事件
 * <p>
 * 用于异步处理OTA升级任务的执行
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/08/22
 */
@Getter
public class OtaTaskExecutionEvent extends ApplicationEvent {
    private final OtaTaskExecutionEventSource eventSource;

    /**
     * 构造函数
     *
     * @param source 事件源
     */
    public OtaTaskExecutionEvent(OtaTaskExecutionEventSource source) {
        super(source);
        this.eventSource = source;
    }
}