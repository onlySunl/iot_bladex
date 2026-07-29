package org.springblade.modules.iot.device.manager.group.impl;

import java.util.List;

import cn.hutool.core.collection.CollUtil;
import org.springblade.core.mvc.manager.impl.SuperManagerImpl;
import org.springblade.core.database.mybatis.conditions.query.QueryWrap;
import org.springblade.modules.iot.device.entity.group.DeviceGroup;
import org.springblade.modules.iot.device.manager.group.DeviceGroupManager;
import org.springblade.modules.iot.device.mapper.group.DeviceGroupMapper;
import org.springblade.modules.iot.device.vo.query.group.DeviceGroupPageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * 设备分组表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-19 18:05:14
 * @create [2025-06-19 18:05:14] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DeviceGroupManagerImpl extends SuperManagerImpl<DeviceGroupMapper, DeviceGroup> implements DeviceGroupManager {

    private final DeviceGroupMapper deviceGroupMapper;


    @Override
    public List<DeviceGroup> getList(DeviceGroupPageQuery query) {
        QueryWrap<DeviceGroup> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(null != query.getId(), DeviceGroup::getId, query.getId());
        queryWrap.lambda().in(CollUtil.isNotEmpty(query.getIds()), DeviceGroup::getId, query.getIds());
        queryWrap.lambda().eq(null != query.getType(), DeviceGroup::getType, query.getType());
        queryWrap.lambda().eq(null != query.getState(), DeviceGroup::getState, query.getState());
        queryWrap.lambda().orderByAsc(DeviceGroup::getSortValue);
        return deviceGroupMapper.selectList(queryWrap);
    }
}


