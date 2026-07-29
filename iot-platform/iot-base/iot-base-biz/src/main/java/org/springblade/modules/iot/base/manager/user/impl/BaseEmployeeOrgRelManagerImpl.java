package org.springblade.modules.iot.base.manager.user.impl;

import cn.hutool.core.collection.CollUtil;
import com.mqttsnet.basic.base.manager.impl.SuperManagerImpl;
import com.mqttsnet.basic.cache.redis2.CacheResult;
import com.mqttsnet.basic.cache.repository.CacheOps;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.database.mybatis.conditions.query.LbQueryWrap;
import com.mqttsnet.basic.model.cache.CacheKey;
import com.mqttsnet.basic.utils.ArgumentAssert;
import org.springblade.modules.iot.base.entity.user.BaseEmployeeOrgRel;
import org.springblade.modules.iot.base.manager.user.BaseEmployeeOrgRelManager;
import org.springblade.modules.iot.base.mapper.user.BaseEmployeeOrgRelMapper;
import org.springblade.modules.iot.base.mapper.user.BaseOrgMapper;
import org.springblade.modules.iot.common.cache.base.user.EmployeeOrgCacheKeyBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 员工所在部门
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-18
 * @create [2021-10-18] [mqttsnet]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseEmployeeOrgRelManagerImpl extends SuperManagerImpl<BaseEmployeeOrgRelMapper, BaseEmployeeOrgRel> implements BaseEmployeeOrgRelManager {
    private final CacheOps cacheOps;
    private final BaseOrgMapper orgMapper;

    @Override
    public List<Long> findOrgIdByEmployeeId(Long employeeId) {
        CacheKey eoKey = EmployeeOrgCacheKeyBuilder.build(employeeId);
        CacheResult<List<Long>> orgIdResult = cacheOps.get(eoKey, k -> orgMapper.selectOrgByEmployeeId(employeeId));
        return orgIdResult.asList();
    }

    @Override
    public boolean removeByEmployeeIds(Collection<Long> employeeIds) {
        ArgumentAssert.notEmpty(employeeIds, "员工ID不能为空");

        boolean remove = remove(Wraps.<BaseEmployeeOrgRel>lbQ().in(BaseEmployeeOrgRel::getEmployeeId, employeeIds));
        cacheOps.del(employeeIds.stream().map(EmployeeOrgCacheKeyBuilder::build).toList());
        return remove;
    }

    @Override
    public void deleteByOrg(Collection<Long> orgIdList) {
        if (CollUtil.isEmpty(orgIdList)) {
            return;
        }
        LbQueryWrap<BaseEmployeeOrgRel> wrap = Wraps.<BaseEmployeeOrgRel>lbQ().in(BaseEmployeeOrgRel::getOrgId, orgIdList);
        List<BaseEmployeeOrgRel> list = list(wrap);
        remove(wrap);
        List<CacheKey> keys = list.stream()
                .map(BaseEmployeeOrgRel::getEmployeeId)
                .distinct()
                .map(EmployeeOrgCacheKeyBuilder::build)
                .toList();
        cacheOps.del(keys);
    }
}
