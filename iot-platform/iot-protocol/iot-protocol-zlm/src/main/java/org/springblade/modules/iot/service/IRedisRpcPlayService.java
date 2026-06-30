package org.springblade.modules.iot.service;

import org.springblade.modules.iot.domain.DownloadFileInfo;

/**
 * @FileName IRedisRpcPlayService
 * @Description
 * @Author fengcheng
 * @date 2026-04-11
 **/
public interface IRedisRpcPlayService {

    DownloadFileInfo getRecordPlayUrl(String serverId, Long recordId);
}
