package org.springblade.modules.nvr.common;

import org.springblade.modules.nvr.bean.SipTransactionInfo;

public interface DeviceStatusCallback {
    public void run(String deviceId, SipTransactionInfo transactionInfo);
}
