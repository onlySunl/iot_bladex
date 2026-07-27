package org.springblade.modules.iot.common.cache.broker.ws;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * WebSocket device session metadata stored in Redis for multi-node sharing.
 *
 * <p>Stub during domain migration. Full fields (nodeId, connectedAt, etc.)
 * will be restored from the original thinglinks source.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WsDeviceSessionInfo implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 存入 jakarta session userProperties 的 key(本地鉴权门用)。
     */
    public static final String SESSION_KEY = "wsDeviceSessionInfo";

    /**
     * 设备 clientId(含 {@code @租户} 后缀)。
     */
    private String clientId;

    /**
     * 租户 ID(字符串形式,与下游事件 / 缓存 key 命名空间对齐)。
     */
    private String tenantId;

    /**
     * 账号模式用户名。
     */
    private String username;

    /**
     * 鉴权结果带回的设备标识(下游直接用,免再解析)。
     */
    private String deviceIdentification;

    /**
     * 鉴权结果带回的产品标识。
     */
    private String productIdentification;

    /**
     * 接入协议(当前固定 {@code WEBSOCKET};预留多协议扩展)。
     */
    private String protocol;

    /**
     * jakarta websocket session id(channelId)── 同节点定位具体连接用。
     */
    private String channelId;

    /**
     * 接入建立时间(毫秒戳)。
     */
    private Long connectTime;

    /**
     * 最近活跃时间(毫秒戳)── 心跳续期时刷新。
     */
    private Long lastActiveTime;
}
