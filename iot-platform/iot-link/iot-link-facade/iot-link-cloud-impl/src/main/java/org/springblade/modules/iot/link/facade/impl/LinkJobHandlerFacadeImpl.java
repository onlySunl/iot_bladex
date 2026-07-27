package org.springblade.modules.iot.link.facade.impl;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.link.api.job.LinkJobHandlerApi;
import org.springblade.modules.iot.link.facade.LinkJobHandlerFacade;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradeTasksResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangyh
 * @since 2024/12/24 17:02
 */
@Service
public class LinkJobHandlerFacadeImpl implements LinkJobHandlerFacade {
    @Lazy
    @Autowired
    private LinkJobHandlerApi linkJobHandlerApi;

    @Override
    public R<?> refreshDeviceCacheForTenant(Long tenantId) {
        return linkJobHandlerApi.refreshDeviceCacheForTenant(tenantId);
    }

    @Override
    public R<?> syncDeviceConnectionStatus(Long tenantId) {
        return linkJobHandlerApi.syncDeviceConnectionStatus(tenantId);
    }

    @Override
    public R<?> refreshProductCacheForTenant(Long tenantId) {
        return linkJobHandlerApi.refreshProductCacheForTenant(tenantId);
    }

    @Override
    public R<?> refreshProductModelCache(Long tenantId) {
        return linkJobHandlerApi.refreshProductModelCache(tenantId);
    }

    @Override
    public R<?> retryProductVersionPublish(Long tenantId) {
        return linkJobHandlerApi.retryProductVersionPublish(tenantId);
    }

    @Override
    public R<?> otaUpgradeTasksExecute(Long tenantId) {
        return linkJobHandlerApi.otaUpgradeTasksExecute(tenantId);
    }
}
