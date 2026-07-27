package org.springblade.modules.iot.protocol.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @program: thinglinks-cloud
 * @description: MQTT协议Topo 状态枚举
 * @packagename: com.mqttsnet.thinglinks.device.enumeration
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-20 17:51
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "MqttProtocolTopoStatusEnum", description = "MQTT协议Topo 状态枚举")
public enum MqttProtocolTopoStatusEnum {
    /**
     * 成功
     */
    SUCCESS(0, "success"),

    /**
     * 失败
     */
    FAILURE(1, "failure"),
    ;

    private Integer value;
    private String desc;

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
