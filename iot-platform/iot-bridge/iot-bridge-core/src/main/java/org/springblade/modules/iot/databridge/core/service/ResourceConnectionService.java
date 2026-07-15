package org.springblade.modules.iot.databridge.core.service;

import org.springblade.core.mp.service.BladeService;
import org.springblade.modules.iot.common.enums.DataDirection;
import org.springblade.modules.iot.common.enums.Direction;
import org.springblade.modules.iot.common.enums.ResourceType;
import org.springblade.modules.iot.databridge.core.util.ConnectionTester;
import org.springblade.modules.iot.pojo.bridge.entity.ResourceConnection;

import java.util.List;

/**
 * ResourceConnection 服务接口
 */
public interface ResourceConnectionService extends BladeService<ResourceConnection> {

    Long createResourceConnection(ResourceConnection connection);
    void updateResourceConnection(ResourceConnection connection);
    ResourceConnection getById(Long id);
    ResourceConnection getResouceForRunning(Long id);
    List<ResourceConnection> getAllConnections();
    List<ResourceConnection> getActiveConnectionsByType(ResourceType type);
    List<ResourceConnection> getConnectionsByDirection(Direction direction);
    void updateConnectionStatus(Long id, Integer status);
    void deleteConnection(Long id);
    boolean isNameExists(String name, Long excludeId);
    ConnectionTester.ConnectionTestResult testConnection(Long id);

}
