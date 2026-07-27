package org.springblade.modules.iot.ws.controller;

import java.io.IOException;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.modules.iot.common.ws.WebSocketAuthGuard;
import org.springblade.modules.iot.common.ws.WebSocketAuthHeaderCaptor;
import org.springblade.modules.iot.device.service.DeviceShadowService;
import org.springblade.modules.iot.device.vo.query.DeviceShadowPageQuery;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 设备影子查询 WebSocket 端点(请求-响应模型,onMsg 直接 return 不广播)。
 *
 * @author mqttsnet
 */
@ServerEndpoint(
    value = "/anyone/deviceOpenSocket/queryDeviceShadow/{tenantId}/{deviceIdentification}",
    configurator = WebSocketAuthHeaderCaptor.class
)
@Component
@Slf4j
public class QueryDeviceShadowEndpoint {

    /** onOpen 校验 path tenantId == 登录 tenantId,不一致 close + 审计 */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config,
                       @PathParam("tenantId") String tenantId,
                       @PathParam("deviceIdentification") String deviceIdentification) {
        if (!WebSocketAuthGuard.requireSameTenant(session, config, tenantId)) {
            return;
        }
        log.info("WebSocket【QueryDeviceShadowEndpoint】连接成功, Session ID: {}, tenantId: {}, deviceIdentification: {}",
            session.getId(), tenantId, deviceIdentification);
    }

    @OnClose
    public void onClose(Session session,
                        @PathParam("tenantId") String tenantId,
                        @PathParam("deviceIdentification") String deviceIdentification) {
        log.info("WebSocket【QueryDeviceShadowEndpoint】连接关闭, Session ID: {}, tenantId: {}, deviceIdentification: {}",
            session.getId(), tenantId, deviceIdentification);
        closeSilently(session);
    }

    /**
     * 消息体协议:{"serviceCode":"...","versionNo":"..."};"ping"/空视为心跳;
     * 非 JSON 退化为纯 serviceCode(旧版兼容)。versionNo 空 fallback 设备绑定版本。
     */
    @OnMessage
    public String onMsg(String text,
                        @PathParam("tenantId") String tenantId,
                        @PathParam("deviceIdentification") String deviceIdentification) {
        if (StrUtil.isEmpty(text) || "ping".equals(text)) {
            return StringPool.EMPTY;
        }

        log.info("tenantId={}, deviceIdentification={}, text={}", tenantId, deviceIdentification, text);
        AuthUtil.setTenantId(tenantId);
        if (AuthUtil.isEmptyBasePool() || AuthUtil.isEmptyTenantId()) {
            return StringPool.EMPTY;
        }
        try {
            // 解析消息体:优先按 JSON 解析(新协议带 versionNo);非 JSON 退化为纯 serviceCode
            String serviceCode;
            String versionNo = null;
            if (JSON.isValidObject(text)) {
                JSONObject body = JSON.parseObject(text);
                serviceCode = body.getString("serviceCode");
                versionNo = body.getString("versionNo");
            } else {
                serviceCode = text;
            }

            ProductResultVO productResultVO = SpringUtils.getBean(DeviceShadowService.class)
                .queryDeviceShadow(DeviceShadowPageQuery.builder()
                    .build()
                    .setDeviceIdentification(deviceIdentification)
                    .setServiceCode(serviceCode)
                    .setVersionNo(versionNo));
            return JsonUtil.toJson(productResultVO);
        } catch (Exception e) {
            log.error("Failed to get device shadow", e);
        }
        return StringPool.EMPTY;
    }

    /** 出错不抛 RuntimeException(抛了 Tomcat 仍持有 session 泄漏更严重)*/
    @OnError
    public void onError(Session session, Throwable error) {
        log.warn("WebSocket【QueryDeviceShadowEndpoint】连接 error, Session ID: {}", session == null ? "null" : session.getId(), error);
        closeSilently(session);
    }

    private void closeSilently(Session session) {
        if (session == null) {
            return;
        }
        try {
            if (session.isOpen()) {
                session.close();
            }
        } catch (IOException e) {
            log.warn("close ws session failed, sessionId={}", session.getId(), e);
        }
    }
}
