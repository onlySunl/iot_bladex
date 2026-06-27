package org.springblade.modules.iot.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.service.RemoteZlmRecordPlanService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * ZLM录像计划服务降级处理
 *
 * @FileName RemoteZlmRecordPlanFallbackFactory
 * @Description
 * @Author fengcheng
 * @date 2026-04-12
 **/
@Component
public class RemoteZlmRecordPlanFallbackFactory implements FallbackFactory<RemoteZlmRecordPlanService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteZlmRecordPlanService.class);

    @Override
    public RemoteZlmRecordPlanService create(Throwable throwable) {
        log.error(" ZLM录像计划服务调用失败:{}", throwable.getMessage());
        return new RemoteZlmRecordPlanService(){
            @Override
            public R<Void> task(String inner) {
                return R.fail("ZLM录像计划服务调用失败" + throwable.getMessage());
            }
        };
    }
}
