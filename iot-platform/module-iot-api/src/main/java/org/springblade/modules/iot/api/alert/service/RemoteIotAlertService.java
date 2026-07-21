package org.springblade.modules.iot.api.alert.service;


import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.api.alert.dto.Message;
import org.springblade.modules.iot.api.alert.factory.RemoteIotAlertFallbackFactory;
import org.springblade.modules.iot.common.constant.IotServiceNameConstants;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 告警模块远程Feign接口，对齐 {@link org.springblade.modules.iot.api.alert.AlertApi}
 */
@FeignClient(contextId = "remoteIotAlertService",
        value = IotServiceNameConstants.IOT_ALERT,
        fallbackFactory = RemoteIotAlertFallbackFactory.class,
        url = IotServiceNameConstants.SERVICE_URL
)
public interface RemoteIotAlertService {

    /** 分页查询告警配置 */
    @PostMapping("/api/alertApi/getAlertConfigPage")
    PageResult<AlertConfig> getAlertConfigPage(@RequestBody AlertConfigPageReqVO reqVO);

    /** 根据告警配置生成通知消息 */
    @PostMapping("/api/api/alertApi/getNotifyMessage")
    Message getNotifyMessage(@RequestBody AlertConfig alertConfig);
}