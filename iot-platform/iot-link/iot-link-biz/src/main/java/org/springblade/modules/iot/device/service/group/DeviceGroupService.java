package org.springblade.modules.iot.device.service.group;

import java.util.List;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.device.entity.group.DeviceGroup;
import org.springblade.modules.iot.device.vo.query.group.DeviceGroupPageQuery;
import org.springblade.modules.iot.device.vo.result.group.DeviceGroupResultVO;


/**
 * <p>
 * 业务接口
 * 设备分组表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-19 18:05:14
 * @create [2025-06-19 18:05:14] [mqttsnet]
 */
public interface DeviceGroupService extends BaseService<Long, DeviceGroup> {

    /**
     * 查询树结构
     *
     * @param query 参数
     * @return 树
     */
    List<DeviceGroupResultVO> findTree(DeviceGroupPageQuery query);


    /**
     * 获取设备分组结果VO列表
     *
     * @param query 查询参数
     * @return {@link List<DeviceGroupResultVO>} 列表结果
     */
    List<DeviceGroupResultVO> getDeviceGroupResultVOList(DeviceGroupPageQuery query);
}


