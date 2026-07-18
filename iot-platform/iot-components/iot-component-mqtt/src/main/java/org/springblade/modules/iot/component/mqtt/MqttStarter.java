

package org.springblade.modules.iot.component.mqtt;


import org.springblade.modules.iot.component.mqtt.service.MqttVerticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

@Slf4j
@Component
public class MqttStarter {

    @Resource
    private MqttVerticle mqttVerticle;

    @PostConstruct
    public void init() {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(mqttVerticle, ar -> {
            if (ar.succeeded()) {
                log.info("start mqtt component success!");
            } else {
                log.error("start mqtt component failed", ar.cause());
            }
        });
    }
}