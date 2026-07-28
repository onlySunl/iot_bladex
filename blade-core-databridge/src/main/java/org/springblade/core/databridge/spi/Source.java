package org.springblade.core.databridge.spi;

import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.SourceMessage;

import java.util.function.Consumer;

/**
 * 数据源接口
 *
 * @author Chill
 */
public interface Source {

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
     * 订阅消息
     */
    void subscribe(Consumer<SourceMessage> consumer);
}
