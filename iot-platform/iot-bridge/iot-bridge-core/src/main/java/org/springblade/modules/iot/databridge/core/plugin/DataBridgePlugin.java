

package org.springblade.modules.iot.databridge.core.plugin;
import org.springblade.modules.iot.common.enums.SourceScope;

import org.springblade.modules.iot.pojo.bridge.entity.DataBridgeConfig;
import org.springblade.modules.iot.pojo.bridge.entity.PluginInfo;
import org.springblade.modules.iot.pojo.bridge.entity.ResourceConnection;
import java.util.List;

/**
 * 数据桥接插件接口 - 核心接口 只定义最核心的方法，其他功能通过抽象基类实现
 *
 * @version 2.0 @Author gitee.com/NexIoT
 * @since 2025/1/15
 */
public interface DataBridgePlugin {

  /** 获取插件信息 */
  PluginInfo getPluginInfo();

  /** 测试资源连接 */
  Boolean testConnection(ResourceConnection connection);

  /** 验证配置 */
  Boolean validateConfig(DataBridgeConfig config);

  /** 获取支持的源范围 */
  List<SourceScope> getSupportedSourceScopes();
}
