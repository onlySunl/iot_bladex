package org.springblade.core.kafka.producer;

import java.util.concurrent.CompletableFuture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

/**
 * Kafka 生产者通用服务 ── 业务侧统一调用入口,封装 send + 异步回调日志。
 *
 * <p>底层 {@link KafkaTemplate} 由 {@code KafkaProducerAutoConfiguration} 注入,Bean 名固定
 * {@code thingLinksKafkaTemplate}。{@code KafkaSendResultHandler} 已自动 attach 处理回调。
 *
 * @author mqttsnet
 */
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService {

    @Qualifier("thingLinksKafkaTemplate")
    private final KafkaTemplate<String, String> thingLinksKafkaTemplate;

    /**
     * 发送消息,返回异步结果。日志由 {@link KafkaSendResultHandler} 统一处理,本方法不重复打。
     * <p>不带 key 的消息按粘性分区随机落盘,无顺序保证;设备数据等需按实体保序的场景请用带 key 重载。
     */
    public CompletableFuture<SendResult<String, String>> thingLinksKafkaTemplateSendMsg(String topic, String msg) {
        return thingLinksKafkaTemplate.send(topic, msg);
    }

    /**
     * 发送消息(带 key)。同一 key 哈希到同一分区,保证按 key 的分区内有序,
     * 设备上行类消息应以设备标识(clientId / deviceIdentification)作为 key。
     * <p>调用方不要在主链路对返回的 future 做 get()/join() 同步等待;失败由
     * {@link KafkaSendResultHandler} 记录,投递语义为 at-most-once 旁路。
     */
    public CompletableFuture<SendResult<String, String>> thingLinksKafkaTemplateSendMsg(String topic, String key, String msg) {
        return thingLinksKafkaTemplate.send(topic, key, msg);
    }
}
