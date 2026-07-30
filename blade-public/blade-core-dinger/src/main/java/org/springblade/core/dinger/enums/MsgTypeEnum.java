package org.springblade.core.dinger.enums;

/**
 * 消息类型枚举
 */
public enum MsgTypeEnum {

    TEXT("text"), MARKDOWN("markdown");

    private final String msgType;

    MsgTypeEnum(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgType() {
        return msgType;
    }
}
