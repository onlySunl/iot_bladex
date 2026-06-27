package org.springblade.modules.iot.zlm.service;

import org.springblade.modules.iot.zlm.api.domain.ZlmMediaServer;
import org.springblade.modules.iot.zlm.hook.ResultForOnPublish;

/**
 * 媒体信息业务
 */
public interface IMediaService {

    boolean closeStreamOnNoneReader(String mediaServerId, String app, String stream, String schema);

    ResultForOnPublish authenticatePublish(ZlmMediaServer mediaServer, String app, String stream, String params);
}
