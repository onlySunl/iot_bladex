package org.springblade.modules.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.core.tool.api.R;
import org.springblade.modules.service.RemoteTenantService;
import org.springblade.modules.system.pojo.entity.Tenant;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 视频监控设备服务降级处理
 *
 * @FileName RemoteQsDeviceFallbackFactory
 * @Description
 * @Author fengcheng
 * @date 2026-03-28
 **/
@Component
public class RemoteTenantFallbackFactory implements FallbackFactory<RemoteTenantService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteTenantFallbackFactory.class);

    @Override
    public RemoteTenantService create(Throwable throwable) {
        log.error("视频监控设备服务调用失败:{}", throwable.getMessage());

        return new RemoteTenantService() {

            @Override
            public R<List<Tenant>> list() {
                return null;
            }
        };
    }
}
