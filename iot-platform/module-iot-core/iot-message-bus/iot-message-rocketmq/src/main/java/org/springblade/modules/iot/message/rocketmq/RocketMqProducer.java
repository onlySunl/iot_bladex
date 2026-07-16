

package org.springblade.modules.iot.message.rocketmq;

import static org.springblade.modules.iot.message.core.ErrorCodeConstants.INIT_PRODUCER_ERROR;
import static org.springblade.modules.iot.message.core.ErrorCodeConstants.SEND_MSG_ERROR;
import static org.springblade.modules.iot.framework.common.exception.util.ServiceExceptionUtil.exception;
import org.springblade.modules.iot.message.core.MqProducer;
import org.springblade.modules.iot.framework.common.util.json.JsonUtils;
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
            throw  exception(INIT_PRODUCER_ERROR, e);
        }
    }

    @Override
    public void publish(String topic, T msg) {
        try {
            producer.send(new Message(topic,JsonUtils.toJsonString(msg).getBytes(StandardCharsets.UTF_8)));
        } catch (Throwable e) {
            throw  exception(SEND_MSG_ERROR, e);
        }
    }

}
