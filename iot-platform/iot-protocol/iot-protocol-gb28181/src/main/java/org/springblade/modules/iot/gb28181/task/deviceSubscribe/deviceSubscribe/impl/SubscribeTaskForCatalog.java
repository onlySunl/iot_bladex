package org.springblade.modules.iot.gb28181.task.deviceSubscribe.deviceSubscribe.impl;

import org.springblade.modules.iot.gb28181.api.bean.SipTransactionInfo;
import org.springblade.modules.iot.gb28181.api.common.SubscribeCallback;
import org.springblade.modules.iot.gb28181.api.domain.Device;
import org.springblade.modules.iot.gb28181.task.deviceSubscribe.deviceSubscribe.SubscribeTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubscribeTaskForCatalog extends SubscribeTask {

    public static final String name = "catalog";

    public static SubscribeTask getInstance(Device device, SubscribeCallback callback, SipTransactionInfo transactionInfo) {
        int subscribeCycle = device.getSubscribeCycleForCatalog();
        if (subscribeCycle <= 0) {
            subscribeCycle = 3600;
        }
        SubscribeTaskForCatalog subscribeTaskForCatalog = new SubscribeTaskForCatalog();
        subscribeTaskForCatalog.setDelayTime((subscribeCycle * 1000L - 500L) + System.currentTimeMillis());
        subscribeTaskForCatalog.setDeviceId(device.getDeviceId());
        subscribeTaskForCatalog.setCallback(callback);
        subscribeTaskForCatalog.setTransactionInfo(transactionInfo);
        return subscribeTaskForCatalog;
    }

    @Override
    public void expired() {
        if (super.getCallback() == null) {
            log.info("[设备订阅到期] 目录订阅 未找到到期处理回调， 编号： {}", getDeviceId());
            return;
        }
        getCallback().run(getDeviceId(), getTransactionInfo());
    }

    @Override
    public String getKey() {
        return String.format("%s_%s", name, getDeviceId());
    }

    @Override
    public String getName() {
        return name;
    }

    public static String getKey(Device device) {
        return String.format("%s_%s", SubscribeTaskForCatalog.name, device.getDeviceId());
    }
}
