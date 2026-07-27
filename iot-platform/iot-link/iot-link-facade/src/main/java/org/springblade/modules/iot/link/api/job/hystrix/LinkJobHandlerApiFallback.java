package org.springblade.modules.iot.link.api.job.hystrix;

import org.springblade.common.base.R;
import org.springblade.modules.iot.link.api.job.LinkJobHandlerApi;
import org.springframework.stereotype.Component;

/**
 * -----------------------------------------------------------------------------
 * File Name: OtaUpgradeTasksApiFallback.java
 * -----------------------------------------------------------------------------
 * Description:
 * LinkJobHandlerApi 熔断
 * -----------------------------------------------------------------------------
 *
 * @author ShiHuan Sun
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * <p>
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023-07-20 13:21
 */
@Component
public class LinkJobHandlerApiFallback implements LinkJobHandlerApi {


    @Override
    public R<?> refreshDeviceCacheForTenant(Long tenantId) {
        return R.timeout();
    }

    @Override
    public R<?> syncDeviceConnectionStatus(Long tenantId) {
        return R.timeout();
    }

    @Override
    public R<?> refreshProductCacheForTenant(Long tenantId) {
        return R.timeout();
    }

    @Override
    public R<?> refreshProductModelCache(Long tenantId) {
        return R.timeout();
    }

    @Override
    public R<?> retryProductVersionPublish(Long tenantId) {
        return R.timeout();
    }

    @Override
    public R<?> otaUpgradeTasksExecute(Long tenantId) {
        return R.timeout();
    }

    @Override
    public R<?> refreshAclRuleCache(Long tenantId) {
        return R.timeout();
    }
}
