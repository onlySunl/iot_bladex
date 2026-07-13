

package org.springblade.modules.iot.protocol.websocket.config;

/**
 * WebSocket 协议常量定义
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2026/1/13
 */
public class WebSocketConstant {

    /** 协议代码 */
    public static final String PROTOCOL_CODE = "websocket";

    /** 协议名称 */
    public static final String PROTOCOL_NAME = "WebSocket";

    /** 默认路径 */
    public static final String DEFAULT_PATH = "/ws";

    /** 默认端口 */
    public static final int DEFAULT_PORT = 8081;

    /** 最大帧大小 (64KB) */
    public static final int MAX_FRAME_SIZE = 65536;

    /** 默认空闲超时 (5分钟) */
    public static final int DEFAULT_IDLE_TIMEOUT = 300000;

    /** 心跳间隔 (30秒) */
    public static final int HEARTBEAT_INTERVAL = 30000;

    /** 消息类型 - 文本 */
    public static final String MESSAGE_TYPE_TEXT = "TEXT";

    /** 消息类型 - 二进制 */
    public static final String MESSAGE_TYPE_BINARY = "BINARY";

    /** 消息类型 - PING */
    public static final String MESSAGE_TYPE_PING = "PING";

    /** 消息类型 - PONG */
    public static final String MESSAGE_TYPE_PONG = "PONG";

    /** 会话属性 - 设备ID */
    public static final String SESSION_ATTR_DEVICE_ID = "deviceId";

    /** 会话属性 - 产品KEY */
    public static final String SESSION_ATTR_PRODUCT_KEY = "productKey";

    /** 会话属性 - 认证状态 */
    public static final String SESSION_ATTR_AUTHENTICATED = "authenticated";

    /** 会话属性 - 连接时间 */
    public static final String SESSION_ATTR_CONNECT_TIME = "connectTime";

    private WebSocketConstant() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
