package org.springblade.core.databridge.spi;

import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorPayload;
import org.springblade.core.databridge.model.SendResult;

/**
 * 数据目标接口
 *
 * @author Chill
 */
public interface Sink {

    /**
     * 初始化
     */
    void init(ConnectorConfig config);

    /**
     * 启动
     */
    void start();

    /**
     * 停止
     */
    void stop();

    /**
     * 发送数据
     */
    SendResult send(ConnectorPayload payload);
}
