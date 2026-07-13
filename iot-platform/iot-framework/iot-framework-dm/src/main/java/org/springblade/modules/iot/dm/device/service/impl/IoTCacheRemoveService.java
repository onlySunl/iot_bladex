

package org.springblade.modules.iot.dm.device.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

/**
 * 缓存操作类
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/8/12 16:10
 */
@Component
@Slf4j
public class IoTCacheRemoveService {

  @CacheEvict(
      cacheNames = {
        "iot_dev_instance_bo",
        "iot_dev_metadata_bo",
        "iot_dev_shadow_bo",
        "iot_dev_action",
        "selectDevCount"
      },
      allEntries = true)
  public void removeDevInstanceBOCache() {}

  @CacheEvict(
      cacheNames = {
        "iot_dev_metadata_bo",
        "iot_dev_shadow_bo",
        "iot_dev_action",
        "iot_dev_product_list",
        "selectDevProductV4List",
        "selectDevCount"
      },
      allEntries = true)
  public void removeProductCache() {}

  @CacheEvict(
      cacheNames = {"iot_protocol_def", "selectProtocolDefNoScript"},
      allEntries = true)
  public void removeDevProtocolCache() {}

  @CacheEvict(
      cacheNames = {"iot_dev_subscribe"},
      allEntries = true)
  public void removeIotDeviceSubscribeCache() {}
}
