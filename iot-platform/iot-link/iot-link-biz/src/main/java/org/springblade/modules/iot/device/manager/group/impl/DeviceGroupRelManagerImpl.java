package org.springblade.modules.iot.device.manager.group.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springblade.basic.base.manager.impl.SuperManagerImpl;
import org.springblade.modules.iot.device.entity.group.DeviceGroupRel;
import org.springblade.modules.iot.device.manager.group.DeviceGroupRelManager;
import org.springblade.modules.iot.device.mapper.group.DeviceGroupRelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * 设备分组关系表
 * </p>
 *
 * @author mqttsnet
 * @since 2025-06-23 14:06:46
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DeviceGroupRelManagerImpl extends SuperManagerImpl<DeviceGroupRelMapper, DeviceGroupRel> implements DeviceGroupRelManager {

    private final DeviceGroupRelMapper deviceGroupRelMapper;

    @Override
    public Optional<List<DeviceGroupRel>> getDeviceGroupRelListByGroupIds(Collection<Long> groupIdList) {
        if (groupIdList.isEmpty()) {
            return Optional.empty();
        }
        QueryWrapper<DeviceGroupRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(DeviceGroupRel::getGroupId, groupIdList);
        return Optional.of(deviceGroupRelMapper.selectList(queryWrapper));
    }
}


