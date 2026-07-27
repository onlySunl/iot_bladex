package org.springblade.modules.iot.link.api.anytenant.hystrix;

import org.springblade.modules.iot.device.vo.query.DeviceAuthenticationQuery;
import org.springblade.modules.iot.link.api.anytenant.DeviceOpenAnyTenantApi;
import org.springblade.modules.iot.protocol.vo.result.DeviceAuthenticationResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @program: thinglinks-cloud
 * @description: 设备开放API熔断
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-06 12:37
 **/
@Component
public class DeviceOpenAnyTenantApiFallback implements DeviceOpenAnyTenantApi {

    @Override
    public ResponseEntity<DeviceAuthenticationResultVO> clientConnectionAuthentication(DeviceAuthenticationQuery deviceAuthenticationQuery) {
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(new DeviceAuthenticationResultVO());
    }

}
