package org.springblade.modules.iot.link.api.anytenant;

import org.springblade.basic.constant.Constants;
import org.springblade.modules.iot.device.vo.query.DeviceAuthenticationQuery;
import org.springblade.modules.iot.link.api.anytenant.hystrix.DeviceOpenAnyTenantApiFallback;
import org.springblade.modules.iot.protocol.vo.result.DeviceAuthenticationResultVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: iot-platform
 * @description: 设备-开放接口API
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-06 12:35
 **/
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.tenant-server:iot-link-server}",
        fallback = DeviceOpenAnyTenantApiFallback.class, path = "/anyTenant/deviceOpen")
public interface DeviceOpenAnyTenantApi {


    @Operation(summary = "Device connection aluthentication", description = "If the authentication succeeds, the status code 200 is returned. If the authentication fails, the advanced status code is returned")
    @PostMapping(value = "/clientConnectionAuthentication", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DeviceAuthenticationResultVO> clientConnectionAuthentication(@RequestBody DeviceAuthenticationQuery deviceAuthenticationQuery);

}
