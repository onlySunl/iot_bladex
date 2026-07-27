package org.springblade.modules.iot.broker.facade.impl;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.broker.DeviceDownlinkFacade;
import org.springblade.modules.iot.broker.api.DeviceDownlinkApi;
import org.springblade.modules.iot.vo.query.DownlinkCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 设备下行派发 Facade ── cloud 部署:代理到 {@link DeviceDownlinkApi}(Feign 调 broker)。
 *
 * @author mqttsnet
 */
@Service
public class DeviceDownlinkFacadeImpl implements DeviceDownlinkFacade {

    @Autowired
    @Lazy
    private DeviceDownlinkApi deviceDownlinkApi;

    @Override
    public R<?> dispatch(DownlinkCommand command) {
        return deviceDownlinkApi.dispatch(command);
    }
}
