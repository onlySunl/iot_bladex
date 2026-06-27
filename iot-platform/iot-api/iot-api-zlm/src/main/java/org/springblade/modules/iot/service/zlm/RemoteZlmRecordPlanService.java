package org.springblade.modules.iot.service.zlm;

import org.springblade.core.constant.SecurityConstants;
import org.springblade.core.constant.ServiceNameConstants;
import org.springblade.core.domain.R;
import org.springblade.modules.iot.factory.zlm.RemoteZlmRecordPlanFallbackFactory;
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
@FeignClient(contextId = "remoteZlmRecordPlanService", value = ServiceNameConstants.ZLM_SERVICE, fallbackFactory = RemoteZlmRecordPlanFallbackFactory.class)
public interface RemoteZlmRecordPlanService {

    /**
     * 录像计划任务
     *
     * @param inner
     */
    @GetMapping("/api/recordPlan/task")
    R<Void> task(@RequestHeader(SecurityConstants.FROM_SOURCE) String inner);
}
