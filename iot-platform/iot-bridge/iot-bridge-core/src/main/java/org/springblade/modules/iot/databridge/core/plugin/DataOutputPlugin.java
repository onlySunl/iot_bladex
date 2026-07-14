

package org.springblade.modules.iot.databridge.core.plugin;

import org.springblade.modules.iot.pojo.bridge.entity.DataBridgeConfig;
import org.springblade.modules.iot.pojo.bridge.entity.ResourceConnection;
import org.springblade.modules.iot.persistence.base.BaseUPRequest;
import java.util.List;

/**
 * 数据输出插件接口 - 输出方向 (IoT -> 外部系统)
 *
 * @version 2.0 @Author gitee.com/NexIoT
 * @since 2025/1/15
 */
public interface DataOutputPlugin extends DataBridgePlugin {

  /** 批量处理数据输出 - 输出方向 (IoT -> 外部系统) */
  void batchProcessOutput(
          List<BaseUPRequest> requests, DataBridgeConfig config, ResourceConnection connection);
}
