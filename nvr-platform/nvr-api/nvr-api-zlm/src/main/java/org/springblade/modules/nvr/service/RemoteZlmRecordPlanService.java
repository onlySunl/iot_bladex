package org.springblade.modules.nvr.service;


import org.springblade.core.tool.api.R;
import org.springblade.modules.nvr.common.constants.SecurityConstants;
import org.springblade.modules.nvr.common.constants.ServiceNameConstants;
import org.springblade.modules.nvr.factory.RemoteZlmRecordPlanFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * ZLM录像计划服务
 *
 * @FileName RemoteZlmRecordPlanService
 * @Description
 * @Author fengcheng
 * @date 2026-04-12
 **/
@FeignClient(contextId = "remoteZlmRecordPlanService",
        value = ServiceNameConstants.ZLM_SERVICE,
        fallbackFactory = RemoteZlmRecordPlanFallbackFactory.class,
        url= ServiceNameConstants.SERVICE_URL
)
public interface RemoteZlmRecordPlanService {

    /**
     * 录像计划任务
     *
     * @param inner
     */
    @GetMapping("/api/recordPlan/task")
    R<Void> task();
}
