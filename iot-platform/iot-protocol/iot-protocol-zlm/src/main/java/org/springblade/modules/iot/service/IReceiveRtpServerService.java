package org.springblade.modules.iot.service;


import org.springblade.modules.iot.domain.RTPServerParam;
import org.springblade.modules.iot.domain.OpenRTPServerResult;
import org.springblade.modules.iot.domain.SSRCInfo;

/**
 * @FileName IReceiveRtpServerService
 * @Description
 * @Author fengcheng
 * @date 2026-04-07
 **/
public interface IReceiveRtpServerService {

    SSRCInfo openRTPServer(RTPServerParam rtpServerParam, ErrorCallback<OpenRTPServerResult> callback);
}
