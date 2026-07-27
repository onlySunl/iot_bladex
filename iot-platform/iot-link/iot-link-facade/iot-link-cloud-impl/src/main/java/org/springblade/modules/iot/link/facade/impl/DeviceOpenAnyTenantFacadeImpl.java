package org.springblade.modules.iot.link.facade.impl;

import org.springblade.modules.iot.device.vo.query.DeviceAuthenticationQuery;
import org.springblade.modules.iot.link.api.anytenant.DeviceOpenAnyTenantApi;
import org.springblade.modules.iot.link.facade.DeviceOpenAnyTenantFacade;
import org.springblade.modules.iot.protocol.vo.result.DeviceAuthenticationResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author tangyh
 * @since 2024/12/24 17:02
 */
@Service
public class DeviceOpenAnyTenantFacadeImpl implements DeviceOpenAnyTenantFacade {
    @Lazy
    @Autowired
    private DeviceOpenAnyTenantApi deviceOpenAnyTenantApi;

    @Override
    public ResponseEntity<DeviceAuthenticationResultVO> clientConnectionAuthentication(DeviceAuthenticationQuery deviceAuthenticationQuery) {
        return deviceOpenAnyTenantApi.clientConnectionAuthentication(deviceAuthenticationQuery);
    }
}
