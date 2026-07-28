package org.springblade.core.databridge.spi;

import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorType;

/**
 * 连接器接口
 *
 * @author Chill
 */
public interface Connector extends Source, Sink {

    /**
     * 获取连接器类型
     */
    ConnectorType getType();

    /**
     * 获取连接器配置
     */
    ConnectorConfig getConfig();

    /**
     * 是否已连接
     */
    boolean isConnected();
}
