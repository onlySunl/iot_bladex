package org.springblade.modules.nvr.service;


import org.springblade.modules.nvr.domain.RTPServerParam;
import org.springblade.modules.nvr.domain.OpenRTPServerResult;
import org.springblade.modules.nvr.domain.SSRCInfo;

/**
 * @FileName IReceiveRtpServerService
 * @Description
 * @Author fengcheng
 * @date 2026-04-07
 **/
public interface IReceiveRtpServerService {

    SSRCInfo openRTPServer(RTPServerParam rtpServerParam, ErrorCallback<OpenRTPServerResult> callback);
}
