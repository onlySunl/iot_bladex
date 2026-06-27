package org.springblade.modules.iot.common.gb28181;

import org.springblade.modules.iot.domain.gb28181.bean.SipTransactionInfo;

public interface SubscribeCallback {
    public void run(String deviceId, SipTransactionInfo transactionInfo);
}
