package org.springblade.modules.iot.databridge.core.service;

import org.springblade.core.mp.service.BladeService;
import org.springblade.modules.iot.common.enums.BridgeType;
import org.springblade.modules.iot.common.enums.SourceScope;
import org.springblade.modules.iot.databridge.core.vo.DataBridgeConfigVO;
import org.springblade.modules.iot.pojo.bridge.entity.DataBridgeConfig;

import java.util.List;

/**
 * DataBridgeConfig 服务接口
 */
public interface DataBridgeConfigService extends BladeService<DataBridgeConfig> {

    Long createDataBridgeConfig(DataBridgeConfig config);
    Boolean validateConfig(DataBridgeConfig config);
    List<DataBridgeConfig> getConfigsBySourceScope(SourceScope sourceScope);
    List<DataBridgeConfig> getActiveConfigsByProductKey(String productKey);
    List<DataBridgeConfig> getActiveConfigsByProductKeyAndDeviceId(String productKey, String deviceId);
    List<DataBridgeConfig> getAllConfigs();
    List<DataBridgeConfig> getConfigsByCreateBy(Long createBy);
    List<DataBridgeConfigVO> getAllConfigVOs();
    List<DataBridgeConfigVO> getConfigVOsByCreateBy(Long createBy);
    void updateConfigStatus(Long id, Integer status, Long updateBy);
    void deleteConfig(Long id);
    DataBridgeConfig getById(Long id);
    void updateDataBridgeConfig(DataBridgeConfig config);
    List<DataBridgeConfig> getConfigsByBridgeType(BridgeType bridgeType);
    List<DataBridgeConfig> getConfigsByTargetResourceId(Long targetResourceId);
    void batchUpdateConfigStatus(List<Long> ids, Integer status, Long updateBy);
    boolean validateBasicFields(DataBridgeConfig config);
}
