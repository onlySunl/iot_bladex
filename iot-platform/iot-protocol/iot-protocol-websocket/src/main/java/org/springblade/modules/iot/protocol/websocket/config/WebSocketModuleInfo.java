

package org.springblade.modules.iot.protocol.websocket.config;

import org.springframework.stereotype.Component;

import org.springblade.modules.iot.common.protocol.ProtocolModuleInfo;

/**
 * WebSocket 协议模块信息
 *
 * <p>提供WebSocket协议模块的基本信息和元数据
 *
 * @author gitee.com/NexIoT
 * @version 2.0
 * @since 2026/1/14
 */
@Component
public class WebSocketModuleInfo implements ProtocolModuleInfo {

    @Override
    public String getCode() {
        return WebSocketConstant.PROTOCOL_CODE;
    }

    @Override
    public String getName() {
        return WebSocketConstant.PROTOCOL_NAME;
    }

    @Override
    public String getDescription() {
        return "WebSocket 协议模块，支持全双工实时通信，适用于需要低延迟、高实时性的物联网场景";
    }

    @Override
    public String getVersion() {
        return "2.0.0";
    }

    @Override
    public String getVendor() {
        return "Universal IoT";
    }

    @Override
    public boolean isCore() {
        return false; // WebSocket是可选协议
    }

    @Override
    public ProtocolCategory getCategory() {
        return ProtocolCategory.APPLICATION; // WebSocket属于应用层协议
    }
}
