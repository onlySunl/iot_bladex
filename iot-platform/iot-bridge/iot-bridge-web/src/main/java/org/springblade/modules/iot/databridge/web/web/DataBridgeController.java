package org.springblade.modules.iot.databridge.web.web;
import org.springblade.modules.iot.common.enums.SourceScope;
import org.springblade.modules.iot.common.enums.ResourceType;
import org.springblade.modules.iot.common.enums.Direction;
import org.springblade.modules.iot.common.enums.DataDirection;
import org.springblade.modules.iot.common.enums.BridgeType;
import SourceScope;
import ResourceType;
import Direction;
import DataDirection;
import BridgeType;

import org.springblade.modules.iot.common.annotation.Log;
import org.springblade.modules.iot.common.enums.BusinessType;
import org.springblade.modules.iot.pojo.entity.DataBridgeConfig;
import org.springblade.modules.iot.pojo.entity.PluginInfo;
import org.springblade.modules.iot.pojo.entity.ResourceConnection;
import org.springblade.modules.iot.databridge.core.enums.PluginStatus;
import org.springblade.modules.iot.databridge.core.manager.DataBridgeManager;
import org.springblade.modules.iot.databridge.core.service.DataBridgeConfigService;
import org.springblade.modules.iot.databridge.core.service.ResourceConnectionService;
import org.springblade.modules.iot.databridge.core.util.ConnectionTester;
import org.springblade.modules.iot.databridge.core.util.ConnectionTester.ConnectionTestResult;
import org.springblade.modules.iot.databridge.core.vo.DataBridgeConfigVO;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.security.BaseController;
import org.springblade.modules.iot.security.utils.SecurityUtils;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/databridge")
public class DataBridgeController extends BaseController {

  @Resource private DataBridgeManager dataBridgeManager;

  @Resource private ResourceConnectionService resourceConnectionService;

  @Resource private DataBridgeConfigService dataBridgeConfigService;

  @Resource private ConnectionTester connectionTester;

  @GetMapping("/plugins/status")
  public Map<String, PluginStatus> listStatuses() {
    return dataBridgeManager.getPluginStatuses();
  }

  @GetMapping("/plugins/info")
  public Map<String, PluginInfo> listInfos() {
    return dataBridgeManager.getPluginInfos();
  }

  /** 根据资源类型获取支持的插件类型选项 */
  @GetMapping("/resource-types/{resourceType}/plugin-types")
  public R<List<Map<String, Object>>> getPluginTypesByResourceType(
      @PathVariable String resourceType) {
    try {
      ResourceType type =
          ResourceType.valueOf(resourceType.toUpperCase());
      List<Map<String, Object>> options = new ArrayList<>();

      // 从所有插件中查找支持该资源类型的插件
      Map<String, PluginInfo> pluginInfos = dataBridgeManager.getPluginInfos();
      for (PluginInfo pluginInfo : pluginInfos.values()) {
        // 检查 supportedResourceTypes 是否为 null
        if (pluginInfo.getSupportedResourceTypes() != null
            && pluginInfo.getSupportedResourceTypes().contains(type.name())) {
          Map<String, Object> option = new HashMap<>();
          option.put("value", pluginInfo.getPluginType());
          option.put("label", pluginInfo.getName());
          option.put("category", pluginInfo.getCategory());
          option.put("dataDirection", pluginInfo.getDataDirection());
          option.put("description", pluginInfo.getDescription());
          option.put("icon", pluginInfo.getIcon());
          options.add(option);
        }
      }

      return R.data(options);
    } catch (Exception e) {
      logger.error("获取插件类型选项失败", e);
      return R.fail("获取插件类型选项失败: " + e.getMessage());
    }
  }

  /** 获取所有资源类型和插件类型的映射关系 */
  @GetMapping("/resource-plugin-mappings")
  public R<Map<String, List<String>>> getResourcePluginMappings() {
    try {
      Map<String, List<String>> mappings = new HashMap<>();

      // 从所有插件中构建映射关系
      Map<String, PluginInfo> pluginInfos = dataBridgeManager.getPluginInfos();
      for (PluginInfo pluginInfo : pluginInfos.values()) {
        // 检查 supportedResourceTypes 是否为 null
        if (pluginInfo.getSupportedResourceTypes() != null) {
          for (String resourceType : pluginInfo.getSupportedResourceTypes()) {
            mappings
                .computeIfAbsent(resourceType, k -> new ArrayList<>())
                .add(pluginInfo.getPluginType());
          }
        }
      }

      return R.data(mappings);
    } catch (Exception e) {
      logger.error("获取映射关系失败", e);
      return R.fail("获取映射关系失败: " + e.getMessage());
    }
  }

  //
  //  @GetMapping("/plugins/debug")
  //  public R<Map<String, Object>> debugPlugins() {
  //    try {
  //      Map<String, Object> result = new HashMap<>();
  //
  //      // 获取插件信息
  //      Map<String, PluginInfo> pluginInfos = dataBridgeManager.getPluginInfos();
  //      result.put("pluginInfos", pluginInfos);
  //      result.put("pluginCount", pluginInfos.size());
  //
  //      // 获取插件状态
  //      Map<String, PluginStatus> pluginStatuses = dataBridgeManager.getPluginStatuses();
  //      result.put("pluginStatuses", pluginStatuses);
  //
  //      // 获取所有插件bean名称
  //      List<String> pluginBeanNames = new ArrayList<>();
  //      for (String key : pluginInfos.keySet()) {
  //        pluginBeanNames.add(key);
  //      }
  //      result.put("pluginBeanNames", pluginBeanNames);
  //
  //      return R.data(result);
  //    } catch (Exception e) {
  //      logger.error("获取插件调试信息失败", e);
  //      return R.fail("获取插件调试信息失败: " + e.getMessage());
  //    }
  //  }

  @Log(title = "测试资源连接", businessType = BusinessType.OTHER)
  @PostMapping("/resources/{id}/test")
  public R<Void> testResource(@PathVariable Long id) {
    ConnectionTestResult connectionTestResult = resourceConnectionService.testConnection(id);
    if (connectionTestResult.isSuccess()) {
      return R.data(connectionTestResult.getMessage());
    }
    return R.fail(connectionTestResult.getMessage());
  }

  /** 测试资源连接配置（未保存的配置） */
  @PostMapping("/resources/test")
  public R<Void> testResourceConfig(@RequestBody ResourceConnection connection) {
    try {
      ConnectionTester.ConnectionTestResult result = connectionTester.testConnection(connection);
      if (result.isSuccess()) {
        return R.data(result.getMessage());
      }
      return R.fail(result.getMessage());
    } catch (Exception e) {
      logger.error("测试资源连接配置失败", e);
      return R.fail("测试连接失败: " + e.getMessage());
    }
  }

  // 资源连接管理
  @GetMapping("/resources")
  public R<List<ResourceConnection>> listResources() {
    try {
      IoTUser currentUser = loginIoTUnionUser(SecurityUtils.getUnionId());
      List<ResourceConnection> connections;

      if (currentUser.isAdmin()) {
        // 管理员可以看到所有连接
        connections = resourceConnectionService.getAllConnections();
      } else {
        // 普通用户只能看到自己创建的连接
        connections =
            resourceConnectionService.getAllConnections().stream()
                .filter(conn -> SecurityUtils.getUnionId().equals(conn.getCreateBy()))
                .toList();
      }

      return R.data(connections);
    } catch (Exception e) {
      logger.error("获取资源连接列表失败", e);
      R<List<ResourceConnection>> result = new R<>();
      result.setCode(500);
      result.setMsg("获取资源连接列表失败: " + e.getMessage());
      return result;
    }
  }

  @Log(title = "创建资源连接", businessType = BusinessType.INSERT)
  @PostMapping("/resources")
  public R<Long> createResource(@RequestBody ResourceConnection connection) {
    try {
      // 设置创建者
      connection.setCreateBy(SecurityUtils.getUnionId());
      Long id = resourceConnectionService.createResourceConnection(connection);
      return R.data(id);
    } catch (Exception e) {
      logger.error("创建资源连接失败", e);
      R<Long> result = new R<>();
      result.setCode(500);
      result.setMsg("创建资源连接失败: " + e.getMessage());
      return result;
    }
  }

  @GetMapping("/resources/{id}")
  public ResourceConnection getResource(@PathVariable Long id) {
    return resourceConnectionService.getById(id);
  }

  @Log(title = "更新资源连接", businessType = BusinessType.UPDATE)
  @PutMapping("/resources/{id}")
  public R<Void> updateResource(
      @PathVariable Long id, @RequestBody ResourceConnection connection) {
    try {
      // 检查权限：只有创建者或管理员可以更新
      ResourceConnection existing = resourceConnectionService.getById(id);
      if (existing == null) {
        return R.fail("资源连接不存在");
      }

      IoTUser currentUser = loginIoTUnionUser(SecurityUtils.getUnionId());
      if (!currentUser.isAdmin() && !SecurityUtils.getUnionId().equals(existing.getCreateBy())) {
        return R.fail("无权限更新此资源连接");
      }

      connection.setId(id);
      connection.setUpdateBy(SecurityUtils.getUnionId());
      resourceConnectionService.updateResourceConnection(connection);
      return R.data("更新资源连接成功");
    } catch (Exception e) {
      logger.error("更新资源连接失败", e);
      return R.fail("更新资源连接失败: " + e.getMessage());
    }
  }

  @Log(title = "更新资源连接状态", businessType = BusinessType.UPDATE)
  @PutMapping("/resources/{id}/status")
  public R<Void> updateResourceStatus(
      @PathVariable Long id, @RequestParam Integer status) {
    try {
      // 检查权限：只有创建者或管理员可以更新状态
      ResourceConnection existing = resourceConnectionService.getById(id);
      if (existing == null) {
        return R.fail("资源连接不存在");
      }

      IoTUser currentUser = loginIoTUnionUser(SecurityUtils.getUnionId());
      if (!currentUser.isAdmin() && !SecurityUtils.getUnionId().equals(existing.getCreateBy())) {
        return R.fail("无权限更新此资源连接状态");
      }

      resourceConnectionService.updateConnectionStatus(id, status, SecurityUtils.getUnionId());
      return R.data("更新资源连接状态成功");
    } catch (Exception e) {
      logger.error("更新资源连接状态失败", e);
      return R.fail("更新资源连接状态失败: " + e.getMessage());
    }
  }

  @Log(title = "删除资源连接", businessType = BusinessType.DELETE)
  @DeleteMapping("/resources/{id}")
  public R<Void> deleteResource(@PathVariable Long id) {
    try {
      // 检查权限：只有创建者或管理员可以删除
      ResourceConnection existing = resourceConnectionService.getById(id);
      if (existing == null) {
        return R.fail("资源连接不存在");
      }

      IoTUser currentUser = loginIoTUnionUser(SecurityUtils.getUnionId());
      if (!currentUser.isAdmin() && !SecurityUtils.getUnionId().equals(existing.getCreateBy())) {
        return R.fail("无权限删除此资源连接");
      }

      resourceConnectionService.deleteConnection(id);
      return R.data("删除资源连接成功");
    } catch (Exception e) {
      logger.error("删除资源连接失败", e);
      return R.fail("删除资源连接失败: " + e.getMessage());
    }
  }

  // 桥接配置管理
  @GetMapping("/configs")
  public R<List<DataBridgeConfigVO>> listConfigs() {
    try {
      IoTUser currentUser = loginIoTUnionUser(SecurityUtils.getUnionId());
      List<DataBridgeConfigVO> configs;

      if (currentUser.isAdmin()) {
        // 管理员可以看到所有配置
        configs = dataBridgeConfigService.getAllConfigVOs();
      } else {
        // 普通用户只能看到自己创建的配置
        configs = dataBridgeConfigService.getConfigVOsByCreateBy(SecurityUtils.getUnionId());
      }

      return R.data(configs);
    } catch (Exception e) {
      logger.error("获取桥接配置列表失败", e);
      R<List<DataBridgeConfigVO>> result = new R<>();
      result.setCode(500);
      result.setMsg("获取桥接配置列表失败: " + e.getMessage());
      return result;
    }
  }

  @Log(title = "创建桥接配置", businessType = BusinessType.INSERT)
  @PostMapping("/configs")
  public R<Long> createConfig(@RequestBody DataBridgeConfig config) {
    try {
      // 设置创建者
      config.setCreateBy(SecurityUtils.getUnionId());
      Long id = dataBridgeConfigService.createDataBridgeConfig(config);
      return R.data(id);
    } catch (Exception e) {
      logger.error("创建桥接配置失败", e);
      R<Long> result = new R<>();
      result.setCode(500);
      result.setMsg("创建桥接配置失败: " + e.getMessage());
      return result;
    }
  }

  @GetMapping("/configs/{id}")
  public DataBridgeConfig getConfig(@PathVariable Long id) {
    return dataBridgeConfigService.getById(id);
  }

  @Log(title = "更新桥接配置", businessType = BusinessType.UPDATE)
  @PutMapping("/configs/{id}")
  public R<Void> updateConfig(
      @PathVariable Long id, @RequestBody DataBridgeConfig config) {
    try {
      // 检查权限：只有创建者或管理员可以更新
      DataBridgeConfig existing = dataBridgeConfigService.getById(id);
      if (existing == null) {
        return R.fail("桥接配置不存在");
      }

      IoTUser currentUser = loginIoTUnionUser(SecurityUtils.getUnionId());
      if (!currentUser.isAdmin() && !SecurityUtils.getUnionId().equals(existing.getCreateBy())) {
        return R.fail("无权限更新此桥接配置");
      }

      config.setId(id);
      config.setUpdateBy(SecurityUtils.getUnionId());
      dataBridgeConfigService.updateDataBridgeConfig(config);
      return R.data("更新桥接配置成功");
    } catch (Exception e) {
      logger.error("更新桥接配置失败", e);
      return R.fail("更新桥接配置失败: " + e.getMessage());
    }
  }

  @Log(title = "更新桥接配置状态", businessType = BusinessType.UPDATE)
  @PutMapping("/configs/{id}/status")
  public R<Void> updateConfigStatus(@PathVariable Long id, @RequestParam Integer status) {
    try {
      // 检查权限：只有创建者或管理员可以更新状态
      DataBridgeConfig existing = dataBridgeConfigService.getById(id);
      if (existing == null) {
        return R.fail("桥接配置不存在");
      }

      IoTUser currentUser = loginIoTUnionUser(SecurityUtils.getUnionId());
      if (!currentUser.isAdmin() && !SecurityUtils.getUnionId().equals(existing.getCreateBy())) {
        return R.fail("无权限更新此桥接配置状态");
      }

      dataBridgeConfigService.updateConfigStatus(id, status, SecurityUtils.getUnionId());
      return R.data("更新桥接配置状态成功");
    } catch (Exception e) {
      logger.error("更新桥接配置状态失败", e);
      return R.fail("更新桥接配置状态失败: " + e.getMessage());
    }
  }

  @Log(title = "删除桥接配置", businessType = BusinessType.DELETE)
  @DeleteMapping("/configs/{id}")
  public R<Void> deleteConfig(@PathVariable Long id) {
    try {
      // 检查权限：只有创建者或管理员可以删除
      DataBridgeConfig existing = dataBridgeConfigService.getById(id);
      if (existing == null) {
        return R.fail("桥接配置不存在");
      }

      IoTUser currentUser = loginIoTUnionUser(SecurityUtils.getUnionId());
      if (!currentUser.isAdmin() && !SecurityUtils.getUnionId().equals(existing.getCreateBy())) {
        return R.fail("无权限删除此桥接配置");
      }

      dataBridgeConfigService.deleteConfig(id);
      return R.data("删除桥接配置成功");
    } catch (Exception e) {
      logger.error("删除桥接配置失败", e);
      return R.fail("删除桥接配置失败: " + e.getMessage());
    }
  }

  @Log(title = "验证桥接配置", businessType = BusinessType.OTHER)
  @PostMapping("/configs/{id}/validate")
  public R<Boolean> validateConfig(@PathVariable Long id) {
    try {
      DataBridgeConfig config = dataBridgeConfigService.getById(id);
      if (config == null) {
        return R.fail("false);
      }

      Boolean isValid = dataBridgeManager.validateBridgeConfig(config);
      if (isValid) {
        return R.data(true);
      } else {
        return R.fail("false);
      }
    } catch (Exception e) {
      logger.error("验证桥接配置失败", e);
      return R.fail("验证桥接配置失败: " + e.getMessage(), false);
    }
  }

  // 查询API
  @GetMapping("/configs/product/{productKey}")
  public List<DataBridgeConfig> getConfigsByProduct(@PathVariable String productKey) {
    return dataBridgeConfigService.getActiveConfigsByProductKey(productKey);
  }

  @GetMapping("/configs/type/{bridgeType}")
  public List<DataBridgeConfig> getConfigsByType(@PathVariable String bridgeType) {
    return dataBridgeConfigService.getConfigsByBridgeType(
        BridgeType.valueOf(bridgeType.toUpperCase()));
  }

  @GetMapping("/resources/type/{type}")
  public List<ResourceConnection> getResourcesByType(@PathVariable String type) {
    return resourceConnectionService.getActiveConnectionsByType(
        ResourceType.valueOf(type.toUpperCase()));
  }

  // 双向数据流转相关API

  /** 根据源范围获取配置列表 */
  @GetMapping("/configs/scope/{scope}")
  public R<List<DataBridgeConfig>> getConfigsByScope(@PathVariable String scope) {
    try {
      IoTUser currentUser = loginIoTUnionUser(SecurityUtils.getUnionId());
      SourceScope sourceScope =
          SourceScope.valueOf(scope.toUpperCase());

      List<DataBridgeConfig> configs;
      if (currentUser.isAdmin()) {
        // TODO: 实现根据源范围获取配置的方法
        configs = List.of(); // 临时返回空列表
      } else {
        // TODO: 实现根据源范围获取配置的方法
        configs = List.of(); // 临时返回空列表
      }

      return R.data(configs);
    } catch (Exception e) {
      logger.error("根据方向获取配置列表失败", e);
      return R.fail("根据方向获取配置列表失败: " + e.getMessage(), (List<DataBridgeConfig>) null);
    }
  }

  /** 根据方向获取资源连接列表 */
  @GetMapping("/resources/direction/{direction}")
  public R<List<ResourceConnection>> getResourcesByDirection(
      @PathVariable String direction) {
    try {
      IoTUser currentUser = loginIoTUnionUser(SecurityUtils.getUnionId());
      Direction dir =
          Direction.valueOf(direction.toUpperCase());

      List<ResourceConnection> connections;
      if (currentUser.isAdmin()) {
        connections = resourceConnectionService.getConnectionsByDirection(dir);
      } else {
        connections =
            resourceConnectionService.getConnectionsByDirection(dir).stream()
                .filter(conn -> SecurityUtils.getUnionId().equals(conn.getCreateBy()))
                .toList();
      }

      return R.data(connections);
    } catch (Exception e) {
      logger.error("根据方向获取资源连接列表失败", e);
      return R.fail("根据方向获取资源连接列表失败: " + e.getMessage(), (List<ResourceConnection>) null);
    }
  }

  /** 获取支持双向的资源类型（动态从插件获取） */
  @GetMapping("/resources/bidirectional-types")
  public R<List<String>> getBidirectionalResourceTypes() {
    try {
      List<String> bidirectionalTypes = dataBridgeManager.getBidirectionalPluginTypes();
      return R.data(bidirectionalTypes);
    } catch (Exception e) {
      logger.error("获取双向资源类型失败", e);
      return R.fail("获取双向资源类型失败: " + e.getMessage(), (List<String>) null);
    }
  }

  /** 获取仅支持输出的资源类型（动态从插件获取） */
  @GetMapping("/resources/output-only-types")
  public R<List<String>> getOutputOnlyResourceTypes() {
    try {
      List<String> outputOnlyTypes = dataBridgeManager.getOutputOnlyPluginTypes();
      return R.data(outputOnlyTypes);
    } catch (Exception e) {
      logger.error("获取仅输出资源类型失败", e);
      return R.fail("获取仅输出资源类型失败: " + e.getMessage(), (List<String>) null);
    }
  }

  /** 获取所有可用的资源类型（动态从插件获取，带兜底数据） */
  @GetMapping("/resources/types")
  public R<Map<String, Object>> getAllResourceTypes(
      @RequestParam(required = false) String direction) {
    try {
      Map<String, Object> result = new HashMap<>();

      // 按类别分组资源类型
      Map<String, Map<String, String>> categorizedTypes = new HashMap<>();
      categorizedTypes.put("databases", new HashMap<>());
      categorizedTypes.put("messageQueues", new HashMap<>());
      categorizedTypes.put("timeSeries", new HashMap<>());
      categorizedTypes.put("searchEngines", new HashMap<>());
      categorizedTypes.put("cloudPlatforms", new HashMap<>());
      categorizedTypes.put("others", new HashMap<>());

      // 获取所有插件信息
      Map<String, PluginInfo> pluginInfos = dataBridgeManager.getPluginInfos();
      logger.info("获取到 {} 个插件信息", pluginInfos.size());

      // 根据数据流向过滤插件
      DataDirection targetDirection = null;
      if (direction != null) {
        try {
          targetDirection = DataDirection.valueOf(direction.toUpperCase());
        } catch (IllegalArgumentException e) {
          logger.warn("无效的数据流向参数: {}", direction);
        }
      }

      // 遍历插件信息，提取支持的资源类型
      for (Map.Entry<String, PluginInfo> entry : pluginInfos.entrySet()) {
        PluginInfo info = entry.getValue();
        logger.info(
            "插件: {}, 支持的资源类型: {}, 数据流向: {}",
            entry.getKey(),
            info.getSupportedResourceTypes(),
            info.getDataDirection());

        // 根据数据流向过滤
        if (targetDirection != null
            && info.getDataDirection() != null
            && info.getDataDirection() != targetDirection
            && info.getDataDirection() != DataDirection.BIDIRECTIONAL) {
          continue;
        }

        if (info.getSupportedResourceTypes() != null) {
          for (String resourceType : info.getSupportedResourceTypes()) {
            // 只处理 ResourceType 枚举中存在的类型
            if (isValidResourceType(resourceType)) {
              String displayName = getResourceTypeDisplayName(resourceType);
              String category = getResourceTypeCategory(resourceType);
              categorizedTypes.get(category).put(resourceType, displayName);
            }
          }
        }
      }

      // 如果没有从插件获取到资源类型，提供兜底数据
      if (categorizedTypes.values().stream().allMatch(Map::isEmpty)) {
        logger.warn("未从插件获取到资源类型，使用兜底数据");
        addFallbackResourceTypes(categorizedTypes, targetDirection);
      }

      // 添加分类后的资源类型
      result.putAll(categorizedTypes);

      // 获取数据流向信息
      Map<String, List<String>> directionTypes = new HashMap<>();
      directionTypes.put(
          "inputTypes", getResourceTypesByDirection(pluginInfos, DataDirection.INPUT));
      directionTypes.put(
          "outputTypes", getResourceTypesByDirection(pluginInfos, DataDirection.OUTPUT));
      directionTypes.put(
          "bidirectionalTypes",
          getResourceTypesByDirection(pluginInfos, DataDirection.BIDIRECTIONAL));
      result.putAll(directionTypes);

      return R.data(result);
    } catch (Exception e) {
      logger.error("获取资源类型失败", e);
      return R.fail("获取资源类型失败: " + e.getMessage(), (Map<String, Object>) null);
    }
  }

  /** 根据数据流向获取资源类型 */
  private List<String> getResourceTypesByDirection(
      Map<String, PluginInfo> pluginInfos, DataDirection direction) {
    List<String> types = new ArrayList<>();
    for (PluginInfo info : pluginInfos.values()) {
      if (info.getDataDirection() == direction
          || info.getDataDirection() == DataDirection.BIDIRECTIONAL) {
        if (info.getSupportedResourceTypes() != null) {
          for (String resourceType : info.getSupportedResourceTypes()) {
            if (isValidResourceType(resourceType) && !types.contains(resourceType)) {
              types.add(resourceType);
            }
          }
        }
      }
    }

    // 如果没有从插件获取到，提供兜底数据
    if (types.isEmpty()) {
      switch (direction) {
        case INPUT:
          types.addAll(List.of("ALIYUN_IOT", "TENCENT_IOT", "MYSQL", "KAFKA", "MQTT"));
          break;
        case OUTPUT:
          types.addAll(
              List.of(
                  "IOTDB", "INFLUXDB", "ELASTICSEARCH", "MYSQL", "KAFKA", "MQTT", "HTTP", "REDIS"));
          break;
        case BIDIRECTIONAL:
          types.addAll(List.of("MYSQL", "KAFKA", "MQTT", "HTTP", "REDIS"));
          break;
      }
    }

    return types;
  }

  /** 检查资源类型是否在枚举中存在 */
  private boolean isValidResourceType(String resourceType) {
    try {
      ResourceType.valueOf(resourceType.toUpperCase());
      return true;
    } catch (IllegalArgumentException e) {
      logger.warn("资源类型 {} 不在枚举中，跳过", resourceType);
      return false;
    }
  }

  /** 添加兜底的资源类型数据 */
  private void addFallbackResourceTypes(
      Map<String, Map<String, String>> categorizedTypes, DataDirection direction) {
    // 数据库类型
    categorizedTypes.get("databases").put("MYSQL", "MySQL数据库");
    categorizedTypes.get("databases").put("POSTGRESQL", "PostgreSQL数据库");
    categorizedTypes.get("databases").put("H2", "H2数据库");
    categorizedTypes.get("databases").put("ORACLE", "Oracle数据库");
    categorizedTypes.get("databases").put("SQLSERVER", "SQL Server数据库");
    categorizedTypes.get("databases").put("REDIS", "Redis缓存");

    // 消息队列类型
    categorizedTypes.get("messageQueues").put("KAFKA", "Kafka消息队列");
    categorizedTypes.get("messageQueues").put("MQTT", "MQTT消息代理");

    // 时序数据库类型（主要用于输出）
    if (direction == null || direction == DataDirection.OUTPUT) {
      categorizedTypes.get("timeSeries").put("IOTDB", "IoTDB时序数据库");
      categorizedTypes.get("timeSeries").put("INFLUXDB", "InfluxDB时序数据库");

      // 搜索引擎类型（主要用于输出）
      categorizedTypes.get("searchEngines").put("ELASTICSEARCH", "Elasticsearch搜索引擎");

      // HTTP接口类型
      categorizedTypes.get("others").put("HTTP", "HTTP接口");
    }

    // 云平台类型（主要用于输入）
    if (direction == null || direction == DataDirection.INPUT) {
      categorizedTypes.get("cloudPlatforms").put("ALIYUN_IOT", "阿里云IoT平台");
      categorizedTypes.get("cloudPlatforms").put("TENCENT_IOT", "腾讯云IoT平台");
    }
  }

  /** 获取资源类型的显示名称 */
  private String getResourceTypeDisplayName(String resourceType) {
    switch (resourceType.toUpperCase()) {
      case "MYSQL":
        return "MySQL数据库";
      case "POSTGRESQL":
        return "PostgreSQL数据库";
      case "H2":
        return "H2数据库";
      case "ORACLE":
        return "Oracle数据库";
      case "SQLSERVER":
        return "SQL Server数据库";
      case "REDIS":
        return "Redis缓存";
      case "KAFKA":
        return "Kafka消息队列";
      case "MQTT":
        return "MQTT消息代理";
      case "IOTDB":
        return "IoTDB时序数据库";
      case "INFLUXDB":
        return "InfluxDB时序数据库";
      case "ELASTICSEARCH":
        return "Elasticsearch搜索引擎";
      case "HTTP":
        return "HTTP接口";
      case "ALIYUN_IOT":
        return "阿里云IoT平台";
      case "TENCENT_IOT":
        return "腾讯云IoT平台";
      case "HUAWEI_IOT":
        return "华为云IoT平台";
      default:
        return resourceType + "资源";
    }
  }

  /** 获取资源类型的分类 */
  private String getResourceTypeCategory(String resourceType) {
    switch (resourceType.toUpperCase()) {
      case "MYSQL":
      case "POSTGRESQL":
      case "H2":
      case "ORACLE":
      case "SQLSERVER":
      case "REDIS":
        return "databases";
      case "KAFKA":
      case "MQTT":
        return "messageQueues";
      case "IOTDB":
      case "INFLUXDB":
        return "timeSeries";
      case "ELASTICSEARCH":
        return "searchEngines";
      case "ALIYUN_IOT":
      case "TENCENT_IOT":
      case "HUAWEI_IOT":
        return "cloudPlatforms";
      case "HTTP":
        return "others";
      default:
        return "others";
    }
  }

  /** 验证输入配置 */
  @PostMapping("/configs/{id}/validate-input")
  public R<Boolean> validateInputConfig(@PathVariable Long id) {
    try {
      DataBridgeConfig config = dataBridgeConfigService.getById(id);
      if (config == null) {
        return R.fail("(Boolean) null);
      }

      // 根据插件信息判断是否支持输入
      Map<String, PluginInfo> pluginInfos = dataBridgeManager.getPluginInfos();
      PluginInfo pluginInfo = pluginInfos.get(config.getBridgeType().name());

      if (pluginInfo == null) {
        return R.fail("false);
      }

      // 检查插件是否支持输入
      boolean supportsInput =
          pluginInfo.getDataDirection() == DataDirection.INPUT
              || pluginInfo.getDataDirection() == DataDirection.BIDIRECTIONAL;

      if (!supportsInput) {
        return R.fail("false);
      }

      // 使用通用的配置验证方法
      Boolean isValid = dataBridgeManager.validateBridgeConfig(config);
      return R.data(isValid);
    } catch (Exception e) {
      logger.error("验证输入配置失败", e);
      return R.fail("验证输入配置失败: " + e.getMessage(), (Boolean) null);
    }
  }

  /** 获取输入配置模板 */
  @GetMapping("/configs/input-template/{bridgeType}")
  public R<String> getInputConfigTemplate(@PathVariable String bridgeType) {
    try {
      // 根据插件信息判断是否支持输入
      Map<String, PluginInfo> pluginInfos = dataBridgeManager.getPluginInfos();
      PluginInfo pluginInfo = pluginInfos.get(bridgeType);

      if (pluginInfo == null) {
        return R.fail(""{}");
      }

      // 检查插件是否支持输入
      boolean supportsInput =
          pluginInfo.getDataDirection() == DataDirection.INPUT
              || pluginInfo.getDataDirection() == DataDirection.BIDIRECTIONAL;

      if (!supportsInput) {
        return R.fail(""{}");
      }

      // 返回默认的输入配置模板
      String template =
          "{\n"
              + "  \"input_config\": {\n"
              + "    \"enabled\": true,\n"
              + "    \"polling_interval\": 5000,\n"
              + "    \"batch_size\": 100\n"
              + "  }\n"
              + "}";

      return R.data(template);
    } catch (Exception e) {
      logger.error("获取输入配置模板失败", e);
      return R.fail("获取输入配置模板失败: " + e.getMessage(), (String) null);
    }
  }
}
