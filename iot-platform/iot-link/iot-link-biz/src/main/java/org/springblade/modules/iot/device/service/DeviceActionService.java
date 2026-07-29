package org.springblade.modules.iot.device.service;

import org.springblade.core.mvc.service.SuperService;
import org.springblade.modules.iot.device.entity.DeviceAction;
import org.springblade.modules.iot.device.vo.query.DeviceActionPageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceActionResultVO;
import org.springblade.modules.iot.device.vo.save.DeviceActionSaveVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 设备动作数据
 * </p>
 *
 * @author mqttsnet
 * @date 2023-06-10 16:38:09
 * @create [2023-06-10 16:38:09] [mqttsnet]
 */
public interface DeviceActionService extends SuperService<Long, DeviceAction> {

    /**
     * 保存设备动作数据
     *
     * @param deviceActionSaveVO 设备动作数据
     * @return {@link DeviceAction} 保存完成的设备动作数据
     */
    DeviceAction saveDeviceAction(DeviceActionSaveVO deviceActionSaveVO);

    /**
     * 查询设备动作数据VO列表
     *
     * @param query 查询参数
     * @return {@link List<DeviceActionResultVO>} 设备动作数据VO列表
     */
    List<DeviceActionResultVO> getDeviceActionResultVOList(DeviceActionPageQuery query);

    /**
     * Disconnects a device using the MQTT protocol.
     *
     * @param deviceIdentification The identification of the device to be disconnected.
     * @return {@link Boolean} The result of the disconnection operation.
     */
    Boolean disconnectDevice(String deviceIdentification);

}


