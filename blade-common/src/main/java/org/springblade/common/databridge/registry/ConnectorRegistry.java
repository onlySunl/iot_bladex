package org.springblade.common.databridge.registry;
public interface ConnectorRegistry {
    void register(String id, Object connector);
    Object get(String id);
}
