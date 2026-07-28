package org.springblade.core.databridge.model;

import lombok.Data;
import java.util.Map;

/**
 * 连接器数据载体
 *
 * @author Chill
 */
@Data
public class ConnectorPayload {

    /**
     * 主题
     */
    private String topic;

    /**
     * 数据
     */
    private Map<String, Object> data;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 消息ID
     */
    private String messageId;
}
