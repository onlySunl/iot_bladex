package org.springblade.modules.iot.device.manager;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.device.entity.DeviceLocation;
import org.springblade.modules.iot.device.vo.query.DeviceLocationPageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceLocationResultVO;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 设备位置表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-05-30 23:05:31
 * @create [2023-05-30 23:05:31] [mqttsnet]
 */
public interface DeviceLocationManager extends SuperManager<DeviceLocation> {

    /**
     * 查询设备位置信息VO列表
     *
     * @param query 查询参数
     * @return {@link List<DeviceLocationResultVO>} 设备位置信息VO列表
     */
    List<DeviceLocationResultVO> getDeviceLocationResultVOList(DeviceLocationPageQuery query);
}


