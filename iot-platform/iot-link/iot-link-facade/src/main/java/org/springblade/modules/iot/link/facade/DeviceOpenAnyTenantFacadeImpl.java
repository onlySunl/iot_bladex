package org.springblade.modules.iot.link.facade;

import java.util.Optional;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import org.springblade.common.context.ContextConstants;
import org.springblade.common.context.ContextUtil;
import org.springblade.common.utils.ArgumentAssert;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.vo.query.DeviceAuthenticationQuery;
import org.springblade.modules.iot.product.enumeration.ProtocolTypeEnum;
import org.springblade.modules.iot.protocol.vo.result.DeviceAuthenticationResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author tangyh
 * @since 2024/12/24 17:02
 */
@Slf4j
@Service
public class DeviceOpenAnyTenantFacadeImpl implements DeviceOpenAnyTenantFacade {
    @Autowired
    private DeviceService deviceService;


    @Override
    public ResponseEntity<DeviceAuthenticationResultVO> clientConnectionAuthentication(DeviceAuthenticationQuery deviceAuthenticationQuery) {
        log.info("clientConnectionAuthentication clientIdentifier: {} param：{}", deviceAuthenticationQuery.getClientIdentifier(), JSON.toJSONString(deviceAuthenticationQuery));
        ArgumentAssert.notBlank(deviceAuthenticationQuery.getProtocolType(), "The protocol type cannot be null");

        try {
            if (ProtocolTypeEnum.fromValue(deviceAuthenticationQuery.getProtocolType()).isPresent()) {
                // Check parameter
                ArgumentAssert.notBlank(deviceAuthenticationQuery.getClientIdentifier(), "clientIdentifier Cannot be null");
                ArgumentAssert.notBlank(deviceAuthenticationQuery.getUsername(), "username Cannot be null");
                ArgumentAssert.notBlank(deviceAuthenticationQuery.getPassword(), "password Cannot be null");

                // Resolve the tenant ID based on the device ID, for example, clientId: 1000000000000000001@tenantId
                String tenantId = Optional.ofNullable(deviceAuthenticationQuery.getClientIdentifier())
                        .filter(StrUtil::isNotBlank)
                        .map(id -> StrUtil.subAfter(id, ContextConstants.SPECIAL_CHARACTER, true))
                        .orElse(ContextConstants.BUILT_IN_TENANT_ID_STR);

                ContextUtil.setTenantId(tenantId);


                DeviceAuthenticationResultVO authenticationResult = deviceService.authClient(deviceAuthenticationQuery);

                if (authenticationResult.getCertificationResult()) {
                    log.info("clientConnectionAuthentication {} Device connection authentication result：{}", deviceAuthenticationQuery.getClientIdentifier(), authenticationResult.getCertificationResult());
                    return ResponseEntity.ok().body(authenticationResult);
                } else {
                    log.warn("clientConnectionAuthentication {} The device connection authentication failed. Procedure：{}", deviceAuthenticationQuery.getClientIdentifier(), authenticationResult.getErrorMessage());
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(authenticationResult);
                }
            }
        } catch (Exception e) {
            log.error("An exception occurred during authentication. Procedure: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DeviceAuthenticationResultVO<>());
        }

        log.info("clientConnectionAuthentication skipped for unsupported protocol type: {}", deviceAuthenticationQuery.getProtocolType());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DeviceAuthenticationResultVO<>());
    }
}
