package org.springblade.modules.iot.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum MessageType {
    ALL("ALL", "全部类型"),
    PROPERTIES("properties", "设备属性"),
    EVENT("event", "设备事件"),
    FUNCTIONS("functions", "功能调用回复");

    private final String value;
    private final String desc;

    @JsonValue
    public String getValue() {
        return value;
    }

    public static Optional<MessageType> find(String value) {
        if (value == null) {
            return Optional.empty();
        }
        return Arrays.stream(values())
                .filter(item -> item.getValue().equals(value))
                .findFirst();
    }

    public static MessageType getByValue(String value) {
        return find(value).orElseThrow(() -> new IllegalArgumentException("消息类型不存在：" + value));
    }
}