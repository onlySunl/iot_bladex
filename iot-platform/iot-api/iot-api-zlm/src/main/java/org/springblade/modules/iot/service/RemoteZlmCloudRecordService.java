package org.springblade.modules.iot.service;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.common.constants.SecurityConstants;
import org.springblade.modules.iot.common.constants.ServiceNameConstants;
import org.springblade.modules.iot.domain.ZlmCloudRecord;
import org.springblade.modules.iot.factory.RemoteZlmCloudRecordFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * zlm接口云端接口 服务
 *
 * @FileName RemoteZlmCloudRecordService
 * @Description
 * @Author fengcheng
 * @date 2026-03-28
 **/
@FeignClient(contextId = "remoteZlmCloudRecordService",
        value = ServiceNameConstants.ZLM_SERVICE,
        fallbackFactory = RemoteZlmCloudRecordFallbackFactory.class,
        url= ServiceNameConstants.SERVICE_URL
)
public interface RemoteZlmCloudRecordService {

    /**
     * 定时查询待删除的录像文件
     *
     * @param inner 请求来源
     * @return
     */
    @GetMapping("/api/cloudRecord/task")
    R<Void> task(@RequestHeader(SecurityConstants.FROM_SOURCE) String inner);

    /**
     * 查询云端录像列表
     *
     * @param zlmCloudRecord 云端录像
     * @param inner 请求来源
     * @return
     */
    @PostMapping("/api/cloudRecord/list")
    R<List<ZlmCloudRecord>> selectZlmCloudRecordList(@RequestBody ZlmCloudRecord zlmCloudRecord, @RequestHeader(SecurityConstants.FROM_SOURCE) String inner);
}
