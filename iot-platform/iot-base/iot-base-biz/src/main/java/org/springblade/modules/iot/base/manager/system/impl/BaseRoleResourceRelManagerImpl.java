package org.springblade.modules.iot.base.manager.system.impl;

import com.mqttsnet.basic.base.manager.impl.SuperManagerImpl;
import com.mqttsnet.basic.cache.repository.CacheOps;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.model.cache.CacheKey;
import org.springblade.modules.iot.base.entity.system.BaseRoleResourceRel;
import org.springblade.modules.iot.base.manager.system.BaseRoleResourceRelManager;
import org.springblade.modules.iot.base.mapper.system.BaseRoleResourceRelMapper;
import org.springblade.modules.iot.common.cache.base.system.RoleResourceCacheKeyBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 角色的资源
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-18
 * @create [2021-10-18] [mqttsnet] 
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseRoleResourceRelManagerImpl extends SuperManagerImpl<BaseRoleResourceRelMapper, BaseRoleResourceRel> implements BaseRoleResourceRelManager {
    private final CacheOps cacheOps;

    @Override
    public List<BaseRoleResourceRel> findByRoleIdAndCategory(Long roleId, String category) {
        return baseMapper.findByRoleIdAndCategory(roleId, category);
    }

    @Override
    public void deleteByRole(Collection<Long> roleIdList) {
        List<BaseRoleResourceRel> roleResourceRelList = list(Wraps.<BaseRoleResourceRel>lbQ().in(BaseRoleResourceRel::getRoleId, roleIdList));
        super.remove(Wraps.<BaseRoleResourceRel>lbQ().in(BaseRoleResourceRel::getRoleId, roleIdList));

        List<CacheKey> keys = new ArrayList<>();
        for (BaseRoleResourceRel rr : roleResourceRelList) {
            keys.add(RoleResourceCacheKeyBuilder.build(rr.getApplicationId(), rr.getRoleId()));
            keys.add(RoleResourceCacheKeyBuilder.build(null, rr.getRoleId()));
        }
        cacheOps.del(keys);
    }
}
