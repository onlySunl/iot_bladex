package org.springblade.core.rocketmq.listener;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.MDC;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.jackson.JsonUtil;

/**
 * 多租户感知的 RocketMQ Listener 抽象基类(Template Method)。
 * <p>final {@link #onMessage} 恢复 {@link ContextUtil} LocalMap → 调子类 {@link #onTenantMessage} → 清理。
 *
 * @param <T> 业务消息体类型
 * @author mqttsnet
 */
@Slf4j
public abstract class AbstractTenantAwareRocketmqListener<T> implements RocketMQListener<MessageExt> {

    @Override
    public final void onMessage(MessageExt message) {
        Map<String, String> ctx = restoreLocalMapFromHeader(message);
        ContextUtil.setLocalMap(ctx);

        try {
            T body = parseBody(message);
            onTenantMessage(body, message);
        } catch (Throwable t) {
            log.error("[rocketmq] consume failed topic={} tags={} msgId={} reconsume={}",
                message.getTopic(), message.getTags(), message.getMsgId(), message.getReconsumeTimes(), t);
            throw t instanceof RuntimeException ? (RuntimeException) t : new RuntimeException(t);
        } finally {
            ContextUtil.remove();
            MDC.clear();
        }
    }

    /**
     * 子类业务入口,此时 {@link ContextUtil} 上下文已就绪,{@code @DS} 切库正常路由。
     */
    protected abstract void onTenantMessage(T body, MessageExt raw);

    /**
     * 业务消息体类型,默认 {@link #parseBody} 用此做 JSON 反序列化。
     */
    protected abstract Class<T> getBodyClass();

    /**
     * 默认 JSON 反序列化 byte[] body,子类可 override 处理 Avro / Protobuf / 加密报文等。
     */
    protected T parseBody(MessageExt message) {
        byte[] body = message.getBody();
        if (body == null || body.length == 0) {
            return null;
        }
        return JsonUtil.parse(new String(body, StandardCharsets.UTF_8), getBodyClass());
    }

    /**
     * 从 RocketMQ user property {@link MessageHeaders#LOCAL_MAP} 恢复 LocalMap。
     */
    private Map<String, String> restoreLocalMapFromHeader(MessageExt message) {
        String json = message.getProperty(MessageHeaders.LOCAL_MAP);
        if (StrUtil.isBlank(json)) {
            return new HashMap<>();
        }
        try {
            Map<String, String> map = JsonUtil.parse(json, new TypeReference<Map<String, String>>() {});
            return map != null ? new HashMap<>(map) : new HashMap<>();
        } catch (Throwable t) {
            log.warn("[rocketmq] parse LocalMap header failed msgId={}: {}", message.getMsgId(), t.getMessage());
            return new HashMap<>();
        }
    }
}
