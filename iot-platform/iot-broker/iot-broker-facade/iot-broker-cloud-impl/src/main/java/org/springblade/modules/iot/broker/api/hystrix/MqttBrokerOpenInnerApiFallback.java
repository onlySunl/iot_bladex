package org.springblade.modules.iot.broker.api.hystrix;

import org.springblade.basic.base.R;
import org.springblade.modules.iot.broker.api.MqttBrokerOpenInnerApi;
import org.springblade.modules.iot.vo.query.KillClientRequestVO;
import org.springblade.modules.iot.vo.query.PublishMessageRequestVO;
import org.springblade.modules.iot.vo.result.MqttSessionDetailsResultVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: iot-platform
 * @description: MqttBroker开放API熔断
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-06 12:37
 **/
@Component
public class MqttBrokerOpenInnerApiFallback implements MqttBrokerOpenInnerApi {

    /**
     * MQTT推送消息接口
     *
     * @param publishMessageRequestVO 推送消息请求参数
     * @return {@link R} 结果
     */
    @Override
    public R<?> sendMessage(PublishMessageRequestVO publishMessageRequestVO) {
        return R.fail("超时");
    }

    /**
     * 关闭客户端连接
     *
     * @param killClientRequestVO 关闭客户端请求参数
     * @return {@link R} 结果
     */
    @Override
    public R closeConnection(KillClientRequestVO killClientRequestVO) {
        return R.fail("超时");
    }

    @Override
    public R<MqttSessionDetailsResultVO> getSessionInfo(String tenantId, String userId, String clientId) {
        return R.fail("超时");
    }

    @Override
    public R<Boolean> isOnline(String tenantId, String deviceIdentification, String clientId) {
        return R.fail("超时");
    }
}
