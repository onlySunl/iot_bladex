package org.springblade.modules.iot.api.alert.factory;

import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.api.alert.dto.Message;
import org.springblade.modules.iot.api.alert.service.RemoteIotAlertService;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 告警远程接口熔断降级处理工厂
 */
@Component
public class RemoteIotAlertFallbackFactory implements FallbackFactory<RemoteIotAlertService> {

    @Override
    public RemoteIotAlertService create(Throwable cause) {
        return new RemoteIotAlertService() {
            @Override
            public PageResult<AlertConfig> getAlertConfigPage(AlertConfigPageReqVO reqVO) {
                return new PageResult<>();
            }

            @Override
            public Message getNotifyMessage(AlertConfig alertConfig) {
                return null;
            }
        };
    }
}