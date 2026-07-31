package org.springblade.modules.iot.broker.api.hystrix;

import org.springblade.basic.base.R;
import org.springblade.modules.iot.broker.api.DeviceDownlinkApi;
import org.springblade.modules.iot.vo.query.DownlinkCommand;

import org.springframework.stereotype.Component;

/**
 * 设备下行派发 Feign 熔断降级。
 *
 * @author mqttsnet
 */
@Component
public class DeviceDownlinkApiFallback implements DeviceDownlinkApi {

    @Override
    public R<?> dispatch(DownlinkCommand command) {
        return R.fail("超时");
    }
}
