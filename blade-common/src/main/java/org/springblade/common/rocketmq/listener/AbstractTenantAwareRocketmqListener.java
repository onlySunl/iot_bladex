package org.springblade.common.rocketmq.listener;
public abstract class AbstractTenantAwareRocketmqListener<T> {
    public abstract void onMessage(T message);
}
