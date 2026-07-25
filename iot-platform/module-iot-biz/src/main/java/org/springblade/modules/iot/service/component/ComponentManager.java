

package org.springblade.modules.iot.service.component;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.common.constant.Constants;
import org.springblade.modules.iot.common.thing.ComponentMessage;
import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.controller.admin.component.vo.ComponentCreateReqVO;
import org.springblade.modules.iot.message.core.ConsumerHandler;
import org.springblade.modules.iot.message.core.MqConsumer;
import org.springblade.modules.iot.message.core.MqProducer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static org.springblade.modules.iot.common.constant.Constants.COMPONENT_DISCOVER_REPLY_TOPIC;
import static org.springblade.modules.iot.common.constant.Constants.COMPONENT_DISCOVER_TOPIC;
import static org.springblade.modules.iot.common.enums.ErrorCodeConstants.ROUTER_NOT_EXISTS;

@Service
public class ComponentManager implements ConsumerHandler<ComponentMessage> {

    private final MqProducer<ThingModelMessage> producer;
    private final MqProducer<ComponentMessage> componentMessageMqProducer;

    private final StringRedisTemplate stringRedisTemplate;

    private final IComponentService componentService;

    private Set<String> components = new HashSet<>();

    public ComponentManager(MqConsumer<ThingModelMessage> consumer,
                            MqProducer<ThingModelMessage> producer,
                            MqConsumer<ComponentMessage> componentMessageMqConsumer,
                            MqProducer<ComponentMessage> componentMessageMqProducer,
                            StringRedisTemplate stringRedisTemplate,
                            IComponentService componentService) {
        this.producer = producer;
        this.componentMessageMqProducer = componentMessageMqProducer;
        this.stringRedisTemplate = stringRedisTemplate;
        this.componentService = componentService;

        //订阅组件发现
        componentMessageMqConsumer.consume(COMPONENT_DISCOVER_TOPIC, this);
    }

    @Override
    public void handler(ComponentMessage msg) {
        //组件发现消息
        componentDiscover(msg);
    }

    /**
     * 通过路由发送消息给设备
     */
    public void sendToDevice(ThingModelMessage msg) {
        String routerKey = getRouterKey(msg);
        String router = stringRedisTemplate.opsForValue().get(routerKey);
        //未找到设备路由
        if (StringUtils.isBlank(router)) {
            throw new ServiceException(ROUTER_NOT_EXISTS);
        }
//
//        String[] split = router.split("/");
//        String componentType = split[0];
        //发给指定组件
        producer.publish(Constants.getSendToDeviceTopic(router), msg);
    }

    private void componentDiscover(ComponentMessage msg) {
        //组件类型
        String type = msg.getType();
        //组件实例id
        String componentId = msg.getComponentId();
        //组件名称
        String name = msg.getName();

        if (!components.contains(type)) {
            if (componentService.getComponent(type) == null) {
                //组件信息入库
                componentService.createComponent(ComponentCreateReqVO.builder()
                        .type(type)
                        .name(name)
                        .status(0)
                        .build());
            }
            components.add(type);
        }

        //回复发现
        componentMessageMqProducer.publish(COMPONENT_DISCOVER_REPLY_TOPIC, ComponentMessage.builder()
                .id(IdUtil.fastSimpleUUID())
                .content(COMPONENT_DISCOVER_REPLY_TOPIC)
                .componentId(componentId)
                .type(type)
                .time(System.currentTimeMillis())
                .name(name)
                .build());
    }

    private String getRouterKey(ThingModelMessage message) {
        return Constants.getRedisDeviceRouter(message.getProductKey(), message.getDn());
    }
}
