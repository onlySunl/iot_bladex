package org.springblade.core.dinger.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 钉钉消息
 *
 * @author Chill
 */
@Data
public class DingerMessage {

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 文本内容
     */
    private String content;

    /**
     * 标题
     */
    private String title;

    /**
     * 文本内容（Markdown）
     */
    private String text;

    /**
     * 链接URL
     */
    private String messageUrl;

    /**
     * 图片URL
     */
    private String picUrl;

    /**
     * 创建文本消息
     */
    public static DingerMessage text(String content) {
        DingerMessage message = new DingerMessage();
        message.setMsgType("text");
        message.setContent(content);
        return message;
    }

    /**
     * 创建Markdown消息
     */
    public static DingerMessage markdown(String title, String text) {
        DingerMessage message = new DingerMessage();
        message.setMsgType("markdown");
        message.setTitle(title);
        message.setText(text);
        return message;
    }

    /**
     * 创建链接消息
     */
    public static DingerMessage link(String title, String text, String messageUrl, String picUrl) {
        DingerMessage message = new DingerMessage();
        message.setMsgType("link");
        message.setTitle(title);
        message.setText(text);
        message.setMessageUrl(messageUrl);
        message.setPicUrl(picUrl);
        return message;
    }

    /**
     * 转换为Map
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("msgtype", msgType);

        switch (msgType) {
            case "text":
                Map<String, Object> textMap = new HashMap<>();
                textMap.put("content", content);
                map.put("text", textMap);
                break;
            case "markdown":
                Map<String, Object> markdownMap = new HashMap<>();
                markdownMap.put("title", title);
                markdownMap.put("text", text);
                map.put("markdown", markdownMap);
                break;
            case "link":
                Map<String, Object> linkMap = new HashMap<>();
                linkMap.put("title", title);
                linkMap.put("text", text);
                linkMap.put("messageUrl", messageUrl);
                linkMap.put("picUrl", picUrl);
                map.put("link", linkMap);
                break;
        }

        return map;
    }

}
