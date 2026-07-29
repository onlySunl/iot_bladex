package org.springblade.modules.iot.system.manager.tenant.impl;

import com.mqttsnet.basic.base.manager.impl.SuperCacheManagerImpl;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;
import com.mqttsnet.basic.utils.CollHelper;
import org.springblade.modules.iot.common.cache.tenant.tenant.TenantCacheKeyBuilder;
import org.springblade.modules.iot.model.constant.EchoApi;
import org.springblade.modules.iot.model.enumeration.system.DefTenantStatusEnum;
import org.springblade.modules.iot.system.entity.tenant.DefTenant;
import org.springblade.modules.iot.system.manager.tenant.DefTenantManager;
import org.springblade.modules.iot.system.mapper.tenant.DefTenantMapper;
import org.springblade.modules.iot.system.vo.result.tenant.DefTenantResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
@Service(EchoApi.DEF_TENANT_SERVICE_IMPL_CLASS)
public class DefTenantManagerImpl extends SuperCacheManagerImpl<DefTenantMapper, DefTenant> implements DefTenantManager {
    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new TenantCacheKeyBuilder();
    }

    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return CollHelper.uniqueIndex(find(ids), DefTenant::getId, DefTenant::getName);
    }

    public List<DefTenant> find(Set<Serializable> ids) {
        // 强转， 防止数据库隐式转换，  若你的id 是string类型，请勿强转
        return findByIds(ids, null).stream().filter(Objects::nonNull).toList();
    }


    @Override
    public List<DefTenant> listNormal() {
        return list(Wraps.<DefTenant>lbQ().eq(DefTenant::getStatus, DefTenantStatusEnum.NORMAL.getCode()));
    }

    @Override
    public List<DefTenantResultVO> listTenantByUserId(Long userId) {
        return baseMapper.listTenantByUserId(userId);
    }
}
