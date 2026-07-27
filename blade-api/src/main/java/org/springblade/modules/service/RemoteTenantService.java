package org.springblade.modules.service;


import org.springblade.common.constant.ServiceSysConstants;
import org.springblade.core.tool.api.R;
import org.springblade.modules.factory.RemoteTenantFallbackFactory;
import org.springblade.modules.system.pojo.entity.Tenant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 视频监控设备 服务
 *
 * @FileName RemoteQsDeviceService
 * @Description
 * @Author fengcheng
 * @date 2026-03-28
 **/
@FeignClient(contextId = "remoteTenantService",
        value = ServiceSysConstants.TENANT_SERVICE,
        fallbackFactory = RemoteTenantFallbackFactory.class,
        url= ServiceSysConstants.SERVICE_URL
)
public interface RemoteTenantService {

    /**
     * 查询视频监控设备
     * @return
     */
    @PostMapping("/api/tenant/allList")
    public R<List<Tenant>> list();


}
