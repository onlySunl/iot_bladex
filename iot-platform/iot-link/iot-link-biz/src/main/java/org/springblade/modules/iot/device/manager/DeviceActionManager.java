package org.springblade.modules.iot.device.manager;

import org.springblade.common.base.manager.SuperManager;
import org.springblade.modules.iot.device.entity.DeviceAction;
import org.springblade.modules.iot.device.vo.query.DeviceActionPageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceActionResultVO;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 设备动作数据
 * </p>
 *
 * @author mqttsnet
 * @date 2023-06-10 16:38:09
 * @create [2023-06-10 16:38:09] [mqttsnet]
 */
public interface DeviceActionManager extends SuperManager<DeviceAction> {

    /**
     * 查询设备动作数据VO列表
     *
     * @param query 查询参数
     * @return {@link List<DeviceActionResultVO>} 设备动作数据VO列表
     */
    List<DeviceActionResultVO> getDeviceActionResultVOList(DeviceActionPageQuery query);
}


