package org.springblade.modules.iot.system.manager.application.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.google.common.collect.Multimap;
import com.mqttsnet.basic.base.manager.impl.SuperManagerImpl;
import com.mqttsnet.basic.cache.repository.CacheOps;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.database.mybatis.conditions.query.LbQueryWrap;
import com.mqttsnet.basic.model.cache.CacheKey;
import com.mqttsnet.basic.utils.CollHelper;
import com.mqttsnet.basic.utils.StrPool;
import org.springblade.modules.iot.common.cache.tenant.tenant.TenantResourceCacheKeyBuilder;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.system.entity.application.DefTenantResourceRel;
import org.springblade.modules.iot.system.manager.application.DefTenantResourceRelManager;
import org.springblade.modules.iot.system.mapper.application.DefTenantResourceRelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 应用管理
 *
 * @author mqttsnet
 * @version v1.0
 * @date 2021/9/29 1:26 下午
 * @create [2021/9/29 1:26 下午 ] [mqttsnet] [初始创建]
 */
@RequiredArgsConstructor
@Service
public class DefTenantResourceRelManagerImpl extends SuperManagerImpl<DefTenantResourceRelMapper, DefTenantResourceRel> implements DefTenantResourceRelManager {
    private final CacheOps cacheOps;

    @Override
    @DS(DsConstant.DEFAULTS)
    public Map<Long, Collection<Long>> findResourceIdByTenantId(Long tenantId) {
        List<DefTenantResourceRel> list = list(Wraps.<DefTenantResourceRel>lbQ().eq(DefTenantResourceRel::getTenantId, tenantId));
        Multimap<Long, Long> map = CollHelper.iterableToMultiMap(list, DefTenantResourceRel::getApplicationId, DefTenantResourceRel::getResourceId);
        return map.asMap();
    }

    @Override
    public void deleteByTenantId(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        LbQueryWrap<DefTenantResourceRel> wrap = Wraps.<DefTenantResourceRel>lbQ().in(DefTenantResourceRel::getTenantId, ids);
        List<DefTenantResourceRel> list = list(wrap);
        remove(wrap);

        List<CacheKey> keys = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (DefTenantResourceRel defTenantResourceRel : list) {
            String key = defTenantResourceRel.getTenantId() + StrPool.DOT + defTenantResourceRel.getApplicationId();
            if (!set.contains(key)) {
                keys.add(TenantResourceCacheKeyBuilder.builder(defTenantResourceRel.getTenantId(), defTenantResourceRel.getApplicationId()));
                set.add(key);
            }
        }
        cacheOps.del(keys);
        cacheOps.del(ids.stream().map(id -> TenantResourceCacheKeyBuilder.builder(id, null)).toArray(CacheKey[]::new));
    }
}
