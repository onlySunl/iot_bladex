package org.springblade.core.databridge.connector;

import lombok.extern.slf4j.Slf4j;
import org.springblade.core.databridge.model.*;
import org.springblade.core.databridge.spi.Connector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

/**
 * HTTP 连接器
 *
 * @author Chill
 */
@Slf4j
public class HttpConnector implements Connector {

    private ConnectorConfig config;
    private WebClient webClient;
    private volatile boolean connected = false;

    @Override
    public void init(ConnectorConfig config) {
        this.config = config;
        this.webClient = WebClient.builder()
            .baseUrl(config.getUrl())
            .build();
    }

    @Override
    public void start() {
        connected = true;
        log.info("HTTP 连接器已启动: {}", config.getUrl());
    }

    @Override
    public void stop() {
        connected = false;
        log.info("HTTP 连接器已停止: {}", config.getUrl());
    }

    @Override
    public void subscribe(Consumer<SourceMessage> consumer) {
        // HTTP 是请求-响应模式，不支持订阅
        log.warn("HTTP 连接器不支持订阅模式");
    }

    @Override
    public SendResult send(ConnectorPayload payload) {
        if (!connected) {
            return SendResult.fail("连接器未启动");
        }

        try {
            String result = webClient.post()
                .uri(payload.getTopic() != null ? payload.getTopic() : "")
                .bodyValue(payload.getData())
                .retrieve()
                .bodyToMono(String.class)
                .block();

            return SendResult.success(result);
        } catch (Exception e) {
            log.error("HTTP 发送失败: {}", e.getMessage());
            return SendResult.fail(e.getMessage());
        }
    }

    @Override
    public ConnectorType getType() {
        return ConnectorType.HTTP;
    }

    @Override
    public ConnectorConfig getConfig() {
        return config;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }
}
