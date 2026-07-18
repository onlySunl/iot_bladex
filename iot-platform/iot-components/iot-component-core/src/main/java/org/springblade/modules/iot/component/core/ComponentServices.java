

package org.springblade.modules.iot.component.core;

import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.common.thing.ComponentMessage;
import org.springblade.modules.iot.message.core.MqConsumer;
import org.springblade.modules.iot.message.core.MqProducer;
import org.springblade.modules.iot.api.component.ComponentApi;
import org.springblade.modules.iot.api.device.DeviceApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Data
@Component
public class ComponentServices {

    @Resource
    private MqConsumer<ThingModelMessage> consumer;

    @Resource
    private MqProducer<ThingModelMessage> producer;

    @Resource
    private MqProducer<ComponentMessage> componentMessageProducer;

    @Resource
    private MqConsumer<ComponentMessage> componentMessageConsumer;



    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ComponentApi componentApi;

    @Resource
    private DeviceApi deviceApi;

}
