package org.springblade.core.databridge.registry;

import org.springblade.core.databridge.spi.Connector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认连接器注册中心
 *
 * @author Chill
 */
@Component
public class DefaultConnectorRegistry implements ConnectorRegistry {

    private final Map<String, Connector> connectors = new ConcurrentHashMap<>();

    @Override
    public void register(Connector connector) {
        connectors.put(connector.getConfig().getId(), connector);
    }

    @Override
    public void unregister(String connectorId) {
        Connector connector = connectors.remove(connectorId);
        if (connector != null) {
            connector.stop();
        }
    }

    @Override
    public Connector get(String connectorId) {
        return connectors.get(connectorId);
    }

    @Override
    public Map<String, Connector> getAll() {
        return connectors;
    }

    @Override
    public boolean exists(String connectorId) {
        return connectors.containsKey(connectorId);
    }
}
