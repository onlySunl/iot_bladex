package org.springblade.modules.iot.api.rule.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.api.rule.dto.RuleInfo;
import org.springblade.modules.iot.api.rule.dto.RuleInfoPageReqVO;
import org.springblade.modules.iot.api.rule.service.RemoteIotRuleService;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 视频监控设备服务降级处理
 *
 * @FileName RemoteQsDeviceFallbackFactory
 * @Description
 * @Author fengcheng
 * @date 2026-03-28
 **/
@Component
public class RemoteIotRuleFallbackFactory implements FallbackFactory<RemoteIotRuleService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteIotRuleFallbackFactory.class);

    @Override
    public RemoteIotRuleService create(Throwable throwable) {
        log.error("视频监控设备服务调用失败:{}", throwable.getMessage());

        return new RemoteIotRuleService() {

            @Override
            public PageResult<RuleInfo> selectPage(RuleInfoPageReqVO reqVO) {
                // 熔断返回空分页
                return new PageResult<>();
            }
        };
    }
}
