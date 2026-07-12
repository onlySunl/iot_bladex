package org.springblade.modules.iot.push.strategy;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.protocol.common.message.DeviceMessage;
import org.springblade.modules.iot.push.PushStrategy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * HTTP 推送策略 - 将设备数据 POST 到指定 URL
 *
 * @author blade-iot
 */
@Slf4j
public class HttpPushStrategy implements PushStrategy {

    private String url;
    private Map<String, String> headers;
    private RestTemplate restTemplate;
    private boolean available = false;

    @Override
    public String getType() {
        return "HTTP";
    }

    @Override
    public void init(String config) {
        try {
            Map<String, Object> cfg = JSON.parseObject(config, Map.class);
            this.url = (String) cfg.get("url");
            this.headers = (Map<String, String>) cfg.get("headers");
            this.restTemplate = new RestTemplate();
            this.available = url != null && !url.isEmpty();
            log.info("[HTTP推送] 初始化完成, url: {}", url);
        } catch (Exception e) {
            log.error("[HTTP推送] 初始化失败", e);
            this.available = false;
        }
    }

    @Override
    public boolean push(DeviceMessage message) {
        if (!available) {
            return false;
        }
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            if (headers != null) {
                headers.forEach(httpHeaders::set);
            }

            String body = JSON.toJSONString(message);
            HttpEntity<String> entity = new HttpEntity<>(body, httpHeaders);
            restTemplate.postForEntity(url, entity, String.class);
            return true;
        } catch (Exception e) {
            log.error("[HTTP推送] 推送失败, url: {}", url, e);
            return false;
        }
    }

    @Override
    public void close() {
        available = false;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }
}
