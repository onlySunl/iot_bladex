

package org.springblade.modules.iot.databridge.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.common.enums.ResourceType;
import org.springblade.modules.iot.pojo.bridge.entity.ResourceConnection;

/**
 * 资源连接工具类 提供资源连接相关的工具方法
 *
 * @version 2.0 @Author gitee.com/NexIoT
 * @since 2025/1/15
 */
@Slf4j
public class ResourceConnectionUtils {

  /** 资源类型到插件类型的默认映射 */
  private static final java.util.Map<ResourceType, String>
      DEFAULT_RESOURCE_PLUGIN_MAPPING =
          java.util.Map.of(
              ResourceType.MYSQL, "JDBC",
              ResourceType.KAFKA, "KAFKA",
              ResourceType.MQTT, "MQTT",
              ResourceType.HTTP, "HTTP",
              ResourceType.IOTDB, "IOTDB",
              ResourceType.INFLUXDB, "INFLUXDB",
              ResourceType.ELASTICSEARCH, "ELASTICSEARCH",
              ResourceType.REDIS, "REDIS");

  /** 为资源连接设置默认的插件类型（如果未设置） */
  public static void setDefaultPluginTypeIfMissing(ResourceConnection connection) {
    if (connection.getPluginType() == null || connection.getPluginType().trim().isEmpty()) {
      String defaultPluginType = DEFAULT_RESOURCE_PLUGIN_MAPPING.get(connection.getType());
      if (defaultPluginType != null) {
        connection.setPluginType(defaultPluginType);
        log.info("为资源连接 {} 设置默认插件类型: {}", connection.getName(), defaultPluginType);
      } else {
        log.warn("资源类型 {} 没有默认的插件类型", connection.getType());
      }
    }
  }

  /** 根据资源类型获取默认的插件类型 */
  public static String getDefaultPluginType(ResourceType resourceType) {
    return DEFAULT_RESOURCE_PLUGIN_MAPPING.get(resourceType);
  }
}
