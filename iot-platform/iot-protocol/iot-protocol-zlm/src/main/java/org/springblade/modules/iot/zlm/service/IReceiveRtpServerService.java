package org.springblade.modules.iot.zlm.service;

import org.springblade.modules.iot.zlm.api.domain.RTPServerParam;
import org.springblade.modules.iot.zlm.domain.OpenRTPServerResult;
import org.springblade.modules.iot.zlm.domain.SSRCInfo;

/**
 * @FileName IReceiveRtpServerService
 * @Description
 * @Author fengcheng
 * @date 2026-04-07
 **/
public interface IReceiveRtpServerService {

    SSRCInfo openRTPServer(RTPServerParam rtpServerParam, ErrorCallback<OpenRTPServerResult> callback);
}
