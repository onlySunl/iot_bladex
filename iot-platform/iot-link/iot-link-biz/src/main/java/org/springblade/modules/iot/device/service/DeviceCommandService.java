package org.springblade.modules.iot.device.service;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.device.entity.DeviceCommand;
import org.springblade.modules.iot.device.vo.query.DeviceCommandPageQuery;
import org.springblade.modules.iot.device.vo.save.DeviceCommandSaveVO;
import org.springblade.modules.iot.protocol.vo.param.DeviceCommandWrapperParam;
import org.springblade.modules.iot.protocol.vo.param.PublishMqttMessageRequestParam;
import org.springblade.modules.iot.protocol.vo.param.PublishWebSocketMessageRequestParam;
import org.springblade.modules.iot.protocol.vo.result.DeviceCommandResultVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 设备命令下发及响应表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-10-20 17:27:25
 * @create [2023-10-20 17:27:25] [mqttsnet]
 */
public interface DeviceCommandService extends BaseService<Long, DeviceCommand> {


    /**
     * Save device command data.
     *
     * @param deviceCommandSaveVO device command data
     * @return DeviceCommand saved device command data
     */
    DeviceCommand saveDeviceCommand(DeviceCommandSaveVO deviceCommandSaveVO);

    /**
     * Fetch a list of device command result VOs.
     *
     * @param query the query parameters
     * @return a list of DeviceCommandResultVOs
     */
    List<DeviceCommandResultVO> getDeviceCommandResultVOList(DeviceCommandPageQuery query);


    /**
     * Processes both serial and parallel device command requests.
     *
     * @param commandWrapper wrapper containing both serial and parallel command requests
     * @return List of device command results
     */
    List<DeviceCommandResultVO> processDeviceCommands(DeviceCommandWrapperParam commandWrapper);

    /**
     * Send a Mqtt custom message to a device.
     *
     * @param publishMqttMessageRequestParam the custom message to be sent
     */
    void sendMqttCustomMessage(PublishMqttMessageRequestParam publishMqttMessageRequestParam);


    /**
     * Send a WebSocket custom message to a device.
     *
     * @param publishWebSocketMessageRequestParam the custom message to be sent
     */
    void sendWebSocketCustomMessage(PublishWebSocketMessageRequestParam publishWebSocketMessageRequestParam);

    /**
     * 调试台下发记录:命令下发(0)与命令响应(1),按时间倒序;设备标识为空则取当前租户全部。
     *
     * @param deviceIdentification 设备标识(可空 = 当前租户全部)
     * @param topic                topic 关键字(可空 = 不过滤)
     * @param limit                返回条数上限(默认 100,最大 500)
     * @return 下发/响应记录列表
     */
    List<DeviceCommandResultVO> listDebugHistory(String deviceIdentification, String topic, Integer limit);
}

