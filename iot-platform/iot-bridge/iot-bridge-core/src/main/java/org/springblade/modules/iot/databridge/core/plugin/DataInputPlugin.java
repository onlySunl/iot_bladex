

package org.springblade.modules.iot.databridge.core.plugin;

import org.springblade.modules.iot.pojo.bridge.entity.DataBridgeConfig;
import org.springblade.modules.iot.pojo.bridge.entity.ResourceConnection;
import java.util.List;

/**
 * 数据输入插件接口 - 输入方向 (外部系统 -> IoT)
 *
 * @version 2.0 @Author gitee.com/NexIoT
 * @since 2025/1/15
 */
public interface DataInputPlugin extends DataBridgePlugin {

  /** 批量处理数据输入 - 输入方向 (外部系统 -> IoT) */
  void batchProcessInput(
      List<Object> externalDataList, DataBridgeConfig config, ResourceConnection connection);
}
