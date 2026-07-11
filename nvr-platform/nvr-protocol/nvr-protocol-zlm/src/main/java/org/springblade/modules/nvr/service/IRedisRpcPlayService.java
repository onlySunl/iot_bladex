package org.springblade.modules.nvr.service;

import org.springblade.modules.nvr.domain.DownloadFileInfo;

/**
 * @FileName IRedisRpcPlayService
 * @Description
 * @Author fengcheng
 * @date 2026-04-11
 **/
public interface IRedisRpcPlayService {

    DownloadFileInfo getRecordPlayUrl(String serverId, Long recordId);
}
