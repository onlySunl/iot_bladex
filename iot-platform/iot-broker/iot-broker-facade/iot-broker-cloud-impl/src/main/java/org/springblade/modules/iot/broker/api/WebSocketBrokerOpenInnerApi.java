package org.springblade.modules.iot.broker.api;

import org.springblade.common.constant.Constants;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.modules.iot.broker.api.hystrix.WebSocketBrokerOpenInnerApiFallback;
import org.springblade.modules.iot.vo.query.PublishWebSocketMessageRequestVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: iot-platform
 * @description: WebSocket Broker-开放接口API
 * @packagename: org.springblade.modules.iot.broker.api
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-06 12:35
 **/
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.tenant-server:iot-broker-server}", fallback = WebSocketBrokerOpenInnerApiFallback.class, path = "/inner/webSocketBrokerOpen")
public interface WebSocketBrokerOpenInnerApi {


    /**
     * WebSocket 推送消息接口
     *
     * @param publishMessageRequestVO 推送消息请求参数
     * @return {@link R} 结果
     */
    @Operation(summary = "WebSocket 推送消息", description = "根据提供的主题、服务质量等级、保留标志和消息内容推送WebSocket消息")
    @PostMapping(path = "/sendMessage", consumes = MediaType.APPLICATION_JSON_VALUE)
    R<?> sendMessage(@RequestBody PublishWebSocketMessageRequestVO publishMessageRequestVO);
}
