package org.springblade.modules.iot.broker;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.broker.downlink.DeviceDownlinkDispatchService;
import org.springblade.modules.iot.vo.query.DownlinkCommand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 设备下行派发 Facade ── boot 部署:本地直调 broker-biz 的 {@link DeviceDownlinkDispatchService}。
 *
 * @author mqttsnet
 */
@Service
@RequiredArgsConstructor
public class DeviceDownlinkFacadeImpl implements DeviceDownlinkFacade {

    private final DeviceDownlinkDispatchService deviceDownlinkDispatchService;

    @Override
    public R<?> dispatch(DownlinkCommand command) {
        return deviceDownlinkDispatchService.dispatch(command);
    }
}
