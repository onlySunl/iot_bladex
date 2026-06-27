package org.springblade.modules.iot.common;

import org.springblade.modules.iot.bean.SipTransactionInfo;

public interface DeviceStatusCallback {
    public void run(String deviceId, SipTransactionInfo transactionInfo);
}
