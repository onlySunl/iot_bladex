package org.springblade.modules.iot.device.manager.group;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.device.entity.group.DeviceGroupRel;

/**
 * <p>
 * 通用业务接口
 * 设备分组关系表
 * </p>
 *
 * @author mqttsnet
 * @since 2025-06-23 14:06:46
 */
public interface DeviceGroupRelManager extends SuperManager<DeviceGroupRel> {


    /**
     * 根据分组ID列表查询设备分组关系列表
     *
     * @param groupIdList 分组ID列表
     * @return {@link Optional<List<DeviceGroupRel>>} 设备分组关系列表
     */
    Optional<List<DeviceGroupRel>> getDeviceGroupRelListByGroupIds(Collection<Long> groupIdList);
}


