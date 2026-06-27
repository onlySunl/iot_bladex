package org.springblade.modules.iot.gb28181.task.deviceStatus;

import org.springblade.modules.iot.gb28181.api.bean.SipTransactionInfo;
import lombok.Data;

@Data
public class DeviceStatusTaskInfo {

    private String deviceId;

    private SipTransactionInfo transactionInfo;

    /**
     * 过期时间,单位毫秒
     */
    private long expireTime;
}
