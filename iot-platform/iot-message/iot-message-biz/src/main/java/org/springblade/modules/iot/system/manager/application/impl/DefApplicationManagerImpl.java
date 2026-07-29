package org.springblade.modules.iot.system.manager.application.impl;

import com.mqttsnet.basic.base.manager.impl.SuperCacheManagerImpl;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;
import com.mqttsnet.basic.utils.CollHelper;
import org.springblade.modules.iot.common.cache.tenant.application.ApplicationCacheKeyBuilder;
import org.springblade.modules.iot.model.constant.EchoApi;
import org.springblade.modules.iot.system.entity.application.DefApplication;
import org.springblade.modules.iot.system.manager.application.DefApplicationManager;
import org.springblade.modules.iot.system.mapper.application.DefApplicationMapper;
import org.springblade.modules.iot.system.vo.result.application.DefApplicationResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
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
@Service(EchoApi.DEF_APPLICATION_SERVICE_IMPL_CLASS)
public class DefApplicationManagerImpl extends SuperCacheManagerImpl<DefApplicationMapper, DefApplication> implements DefApplicationManager {

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new ApplicationCacheKeyBuilder();
    }


    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return CollHelper.uniqueIndex(find(ids), DefApplication::getId, DefApplication::getName);
    }

    public List<DefApplication> find(Set<Serializable> ids) {
//         强转， 防止数据库隐式转换，  若你的id 是string类型，请勿强转
        return findByIds(ids, null).stream().filter(Objects::nonNull).toList();
    }

    @Override
    public List<DefApplication> findApplicationListByTenantId(Long tenantId) {
        return tenantId == null ? Collections.emptyList() : baseMapper.findApplicationListByTenantId(tenantId);
    }

    @Override
    public List<DefApplicationResultVO> findMyApplication(Long tenantId, String name) {
        return baseMapper.findMyApplication(tenantId, name);
    }

    @Override
    public List<DefApplicationResultVO> findRecommendApplication(Long tenantId, String name) {
        return baseMapper.findRecommendApplication(tenantId, name);
    }

    @Override
    public List<DefApplication> findGeneral() {
        return list(Wraps.<DefApplication>lbQ().eq(DefApplication::getIsGeneral, true));
    }

}
