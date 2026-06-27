package org.springblade.modules.iot.common;


import org.springblade.modules.iot.bean.SipTransactionInfo;

public interface SubscribeCallback {
    public void run(String deviceId, SipTransactionInfo transactionInfo);
}
