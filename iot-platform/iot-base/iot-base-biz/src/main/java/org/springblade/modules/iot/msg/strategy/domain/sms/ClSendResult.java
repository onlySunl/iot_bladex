package org.springblade.modules.iot.msg.strategy.domain.sms;

import lombok.Data;

/**
 * @author mqttsnet
 * @date 2021/7/15 16:13
 */
@Data
public class ClSendResult {
    private String code;
    private String failNum;
    private String successNum;
    private String msgId;
    private String time;
    private String errorMsg;

}
