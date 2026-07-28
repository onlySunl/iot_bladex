package org.springblade.core.databridge.model;

import lombok.Data;
import java.util.Map;

/**
 * 源消息
 *
 * @author Chill
 */
@Data
public class SourceMessage {

    /**
     * 主题
     */
    private String topic;

    /**
     * 消息体
     */
    private byte[] payload;

    /**
     * 消息头
     */
    private Map<String, String> headers;

    /**
     * 时间戳
     */
    private Long timestamp;
}
