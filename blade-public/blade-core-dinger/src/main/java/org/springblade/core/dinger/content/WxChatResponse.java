package org.springblade.core.dinger.content;

import lombok.Data;

@Data
public class WxChatResponse {
    /**
     * 错误码
     */
    private int errcode;
    /**
     * 错误信息
     */
    private String errmsg;
    /**
     * 消息类型
     */
    private String type;
    /**
     * 媒体ID
     */
    private String mediaId;
    /**
     * 创建时间
     */
    private long createdAt;
}
