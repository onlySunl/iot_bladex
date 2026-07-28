package org.springblade.core.databridge.model;

import lombok.Data;
import java.util.Map;

/**
 * 连接器配置
 *
 * @author Chill
 */
@Data
public class ConnectorConfig {

    /**
     * 连接器ID
     */
    private String id;

    /**
     * 连接器名称
     */
    private String name;

    /**
     * 连接器类型
     */
    private ConnectorType type;

    /**
     * 连接URL
     */
    private String url;

    /**
     * 配置参数
     */
    private Map<String, Object> properties;

    /**
     * 是否启用
     */
    private Boolean enabled;
}
