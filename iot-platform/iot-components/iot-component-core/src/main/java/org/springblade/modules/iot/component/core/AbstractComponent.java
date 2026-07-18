

package org.springblade.modules.iot.component.core;

import cn.hutool.core.util.IdUtil;
import org.springblade.modules.iot.common.constant.Constants;
import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.common.thing.ComponentMessage;
import lombok.SneakyThrows;
import org.springblade.modules.iot.message.core.ConsumerHandler;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.Executors;

import static org.springblade.modules.iot.common.constant.Constants.*;

public abstract class AbstractComponent implements Component, ConsumerHandler<ThingModelMessage> {

    private final Object discoverLock = new Object();

    protected final ComponentServices componentServices;
    private final String componentId; // 添加字段来存储 UUID
    private Boolean isSubDiscoverReply = false;

    protected AbstractComponent(ComponentServices componentServices) {
        this.componentServices = componentServices;
        this.componentId = IdUtil.fastSimpleUUID();
        componentServices.getComponentMessageConsumer().consume(COMPONENT_DISCOVER_REPLY_TOPIC,
                (msg) -> {
                    //组件发现回复
                    if (Objects.equals(msg.getContent(), COMPONENT_DISCOVER_REPLY_TOPIC)) {
                        synchronized (discoverLock) {
                            //非本组件的回复
                            if (!Objects.equals(msg.getComponentId(), getId())) {
                                return;
                            }
                            //组件成功注册
                            //订阅组件消息
                            // TODO: 没必要重复订阅
                            if(!isSubDiscoverReply){
                                // 消费者订阅&&处理消息：订阅设备属性设置参数，并发送给设备
                                componentServices.getConsumer().consume(Constants.getSendToDeviceTopic(getRouter()), this);
                                isSubDiscoverReply = true;
                            }

                        }
                        return;
                    }
                });

        //发布组件发现消息
        publishDiscover();
        Executors.newSingleThreadExecutor().submit(this::publishDiscover);
    }

    @SneakyThrows
    @Scheduled(fixedRate = 3000)
    private void publishDiscover() {
        //一直发送发现消息，等组件管理回复
        componentServices.getComponentMessageProducer().publish(COMPONENT_DISCOVER_TOPIC, ComponentMessage.builder()
                .id(IdUtil.fastSimpleUUID())
                .time(System.currentTimeMillis())
                .content(COMPONENT_DISCOVER_TOPIC)
                .type(getType())
                .name(getName())
                .componentId(getId())
                .build());
    }

    @Override
    public String getId() {
        return this.componentId;
    }

    /**
     *  消费处理消息
     * @param msg
     */
    public void handler(ThingModelMessage msg) {

        String routerKey = getRouterKey(msg);
        String router = componentServices.getStringRedisTemplate().opsForValue().get(routerKey);
        //非该组件的消息
        if (!getRouter().equals(router)) {
            return;
        }

        //其它消息
        onMessage(msg);
    }

    @Override
    public void sendMessage(ThingModelMessage message) {
        String key = getRouterKey(message);
        String router = getRouter();
        //更新设备路由
        componentServices.getStringRedisTemplate().opsForValue().set(key, router);
        componentServices.getProducer().publish(Constants.THING_MODEL_MESSAGE_TOPIC, message);
    }

    private String getRouterKey(ThingModelMessage message) {
        return Constants.getRedisDeviceRouter(message.getProductKey(), message.getDn());
    }

    protected String getRouter() {
        return String.format("%s_%s", getType(), getId());
    }



    /**
     * 缓存设备对应的组件信息
     * @param pk 产品key
     * @param dn 设备标识
     */
    protected void cacheDeviceComponentInfo(String pk, String dn) {
        // 缓存设备对应的组件信息(用于下发控制指令时查询设备对应的组件信息从而拼接topic)
        String key = Constants.getRedisDeviceRouter(pk, dn);
        String router = getRouter();
        componentServices.getStringRedisTemplate().opsForValue().set(key, router);
    }

}
