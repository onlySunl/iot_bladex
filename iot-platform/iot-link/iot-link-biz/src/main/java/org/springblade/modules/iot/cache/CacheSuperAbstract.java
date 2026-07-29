package org.springblade.modules.iot.cache;


import jakarta.annotation.Resource;
import org.springblade.core.tool.api.R;
import org.springblade.modules.service.RemoteTenantService;
import org.springblade.modules.system.pojo.entity.Tenant;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 缓存抽象类，提供共用方法
 * @author xiaonannet
 */
public abstract class CacheSuperAbstract {

    public static final int PAGE_SIZE = 10000;

    @Resource
    protected RemoteTenantService defTenantApi;

    /**
     * 获取所有租户
     *
     * @return {@link List<Long>} 租户ID列表
     */
    public List<Long> getTenantList() {
        R<List<Tenant>> allTenant = defTenantApi.list();
        if (allTenant.getData() != null) {
            return allTenant.getData().stream().map(Tenant::getId).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
