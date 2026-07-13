

package org.springblade.modules.iot.pojo.protocol.websocket;

import org.springblade.modules.iot.protocol.websocket.enums.WebSocketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * WebSocket 会话实体
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2026/1/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketSession {

    /** 会话ID */
    private String sessionId;

    /** 设备ID */
    private String deviceId;

    /** 产品Key */
    private String productKey;

    /** 设备名称 */
    private String deviceName;

    /** 连接状态 */
    private WebSocketStatus status;

    /** 是否已认证 */
    private Boolean authenticated;

    /** 远程地址 */
    private String remoteAddress;

    /** 连接时间 */
    private LocalDateTime connectTime;

    /** 最后活跃时间 */
    private LocalDateTime lastActiveTime;

    /** 接收消息数 */
    private Long receivedMessageCount;

    /** 发送消息数 */
    private Long sentMessageCount;

    /** 附加属性 */
    private java.util.Map<String, Object> attributes;

    /**
     * 更新最后活跃时间
     */
    public void updateLastActiveTime() {
        this.lastActiveTime = LocalDateTime.now();
    }

    /**
     * 增加接收消息计数
     */
    public void incrementReceivedCount() {
        if (this.receivedMessageCount == null) {
            this.receivedMessageCount = 0L;
        }
        this.receivedMessageCount++;
    }

    /**
     * 增加发送消息计数
     */
    public void incrementSentCount() {
        if (this.sentMessageCount == null) {
            this.sentMessageCount = 0L;
        }
        this.sentMessageCount++;
    }
}
