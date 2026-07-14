package org.springblade.modules.iot.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum TriggerType implements Serializable {
    // 设备消息
    device(Arrays.asList(MessageType.properties, MessageType.event)),
    // 定时,定时获取只支持获取设备属性和调用功能.
    timer(Arrays.asList(MessageType.properties, MessageType.functions));

    final List<MessageType> supportMessageTypes;
}