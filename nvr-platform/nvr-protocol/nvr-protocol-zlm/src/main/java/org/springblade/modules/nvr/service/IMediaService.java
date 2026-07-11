package org.springblade.modules.nvr.service;


import org.springblade.modules.nvr.domain.ZlmMediaServer;
import org.springblade.modules.nvr.hook.ResultForOnPublish;

/**
 * 媒体信息业务
 */
public interface IMediaService {

    boolean closeStreamOnNoneReader(Long mediaServerId, String app, String stream, String schema);

    ResultForOnPublish authenticatePublish(ZlmMediaServer mediaServer, String app, String stream, String params);
}
