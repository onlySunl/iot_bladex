
package org.springblade.modules.iot.component.mqtt.service;


import org.springblade.modules.iot.component.mqtt.model.MqttConfig;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttServerOptions;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * mqtt官方协议文档：
 *
 * @author sjg
 */
@Slf4j
@Component
@Data
public class MqttVerticle extends AbstractVerticle {

    private MqttServer mqttServer;

    private boolean closed = true;

    private MqttComponent mqttComponent;

    public void startServer(MqttConfig config) {
        MqttServerOptions options = new MqttServerOptions()
                .setPort(config.getPort());
        if (config.isSsl()) {
            options = options.setSsl(true)
                    .setKeyCertOptions(new PemKeyCertOptions()
                            .setKeyPath(config.getSslKey())
                            .setCertPath(config.getSslCert()));
        }
        options.setUseWebSocket(config.isUseWebSocket());

        mqttServer = MqttServer.create(vertx, options);
        mqttServer.endpointHandler(mqttComponent).listen(ar -> {
            if (ar.succeeded()) {
                closed = false;
                log.info("MQTT server is listening on port " + ar.result().actualPort());
            } else {
                log.error("Error on starting the server", ar.cause());
            }
        });
    }

    public void stopServer() {
        try {
            mqttComponent.offlineAll();
            if (mqttServer != null) {
                mqttServer.close();
                closed = true;
            }
        } finally {
            log.info("组件关闭...");
        }
    }

}
