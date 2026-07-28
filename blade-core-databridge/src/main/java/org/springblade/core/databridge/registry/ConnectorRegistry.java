package org.springblade.core.databridge.registry;

import org.springblade.core.databridge.spi.Connector;

import java.util.Map;

/**
 * 连接器注册中心
 *
 * @author Chill
 */
public interface ConnectorRegistry {

    /**
     * 注册连接器
     */
    void register(Connector connector);

    /**
     * 注销连接器
     */
    void unregister(String connectorId);

    /**
     * 获取连接器
     */
    Connector get(String connectorId);

    /**
     * 获取所有连接器
     */
    Map<String, Connector> getAll();

    /**
     * 是否存在连接器
     */
    boolean exists(String connectorId);
}
