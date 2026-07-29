package org.springblade.modules.iot.device.service;

import org.springblade.core.mvc.service.SuperService;
import org.springblade.modules.iot.device.entity.DeviceLocation;
import org.springblade.modules.iot.device.vo.query.DeviceLocationPageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceLocationResultVO;
import org.springblade.modules.iot.device.vo.save.DeviceLocationSaveVO;
import org.springblade.modules.iot.device.vo.update.DeviceLocationUpdateVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 设备位置表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-05-30 23:05:31
 * @create [2023-05-30 23:05:31] [mqttsnet]
 */
public interface DeviceLocationService extends SuperService<Long, DeviceLocation> {

    /**
     * 保存设备位置信息
     *
     * @param deviceLocationSaveVO 设备位置信息
     * @return {@link DeviceLocationSaveVO} 保存完成的设备位置信息
     */
    DeviceLocationSaveVO saveDeviceLocation(DeviceLocationSaveVO deviceLocationSaveVO);

    /**
     * 更新设备位置信息
     *
     * @param deviceLocationUpdateVO 设备位置信息
     * @return {@link DeviceLocationUpdateVO} 更新完成的设备位置信息
     */
    DeviceLocationUpdateVO updateDeviceLocation(DeviceLocationUpdateVO deviceLocationUpdateVO);

    /**
     * 查询设备位置信息VO列表
     *
     * @param query 查询参数
     * @return {@link List<DeviceLocationResultVO>} 设备位置信息VO列表
     */
    List<DeviceLocationResultVO> getDeviceLocationResultVOList(DeviceLocationPageQuery query);
}


