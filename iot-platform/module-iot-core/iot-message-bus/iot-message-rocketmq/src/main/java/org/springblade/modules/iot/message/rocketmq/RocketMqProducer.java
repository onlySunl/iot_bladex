

package org.springblade.modules.iot.message.rocketmq;

import io.jsonwebtoken.io.SerialException;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springblade.modules.iot.message.core.MqProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RocketMqProducer<T> implements MqProducer<T> {

    private final DefaultMQProducer producer;


    public RocketMqProducer(String nameServer, String group) {
        try {
            producer = new DefaultMQProducer(group);
            producer.setNamesrvAddr(nameServer);
            producer.start();
        } catch (Throwable e) {
            throw  new SerialException("初始化MQ生产者失败");
        }
    }

    @Override
    public void publish(String topic, T msg) {
        try {
            producer.send(new Message(topic, JsonUtils.toJsonString(msg).getBytes(StandardCharsets.UTF_8)));
        } catch (Throwable e) {
            throw  new SerialException("发送消息失败");
        }
    }

}
