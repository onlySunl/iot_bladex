package org.springblade.modules.iot.databridge.plugin.http;
import org.springblade.modules.iot.common.enums.SourceScope;
import org.springblade.modules.iot.common.enums.ResourceType;
import org.springblade.modules.iot.common.enums.MessageType;
import org.springblade.modules.iot.common.enums.DataDirection;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springblade.modules.iot.databridge.core.plugin.AbstractDataInputPlugin;
import org.springblade.modules.iot.pojo.bridge.entity.DataBridgeConfig;
import org.springblade.modules.iot.pojo.bridge.entity.PluginInfo;
import org.springblade.modules.iot.pojo.bridge.entity.ResourceConnection;

import org.springblade.modules.iot.persistence.base.BaseUPRequest;
import org.springblade.modules.iot.persistence.dto.IoTDeviceDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/** HTTP数据输入插件 支持从HTTP接口拉取数据 */
@Component("httpInput")
@ConditionalOnProperty(
    prefix = "databridge.plugins.http-input",
    name = "enabled",
    havingValue = "true",
    matchIfMissing = true)
@Slf4j
public class HttpDataInputPlugin extends AbstractDataInputPlugin {

  private final RestTemplate restTemplate = new RestTemplate();

  @Override
  public PluginInfo getPluginInfo() {
    return PluginInfo.builder()
        .name("HTTP数据输入插件")
        .version("1.0.0")
        .description("支持从HTTP接口拉取数据，实现数据输入功能")
        .author("gitee.com/NexIoT")
        .pluginType("HTTP_INPUT")
        .supportedResourceTypes(List.of("HTTP"))
        .dataDirection(DataDirection.INPUT)
        .category("网络")
        .icon("api")
        .build();
  }

  @Override
  public List<SourceScope> getSupportedSourceScopes() {
    return List.of(
        SourceScope.ALL_PRODUCTS, SourceScope.SPECIFIC_PRODUCTS, SourceScope.APPLICATION);
  }

  @Override
  protected void sendToIoTPlatform(Object processedData, DataBridgeConfig config) {
    // TODO: 实现推送到IoT平台的逻辑
    // 这里可以通过消息队列、HTTP API等方式将数据推送到IoT平台
    log.debug("推送数据到IoT平台: 配置ID={}", config.getId());
  }

  @Override
  protected void processExternalDataWithDefaultLogic(
      Object externalData, DataBridgeConfig config, ResourceConnection connection) {
    try {
      // 处理外部数据，转换为IoT平台格式
      BaseUPRequest request = transformToIoTData(externalData, config);
      if (request != null) {
        sendToIoTPlatform(request, config);
      }
    } catch (Exception e) {
      log.error("处理外部数据失败: {}", e.getMessage(), e);
    }
  }

  @Override
  public Boolean testConnection(ResourceConnection connection) {
    try {
      String url = buildUrl(connection);
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
      return response.getStatusCode().is2xxSuccessful();
    } catch (Exception e) {
      log.error("HTTP连接测试失败: {}", e.getMessage(), e);
      return false;
    }
  }

  @Override
  public Boolean validateConfig(DataBridgeConfig config) {
    // 验证统一配置
    if (StrUtil.isBlank(config.getConfig())) {
      return false;
    }

    try {
      JSONObject configJson = JSONUtil.parseObj(config.getConfig());
      // 验证必要的配置项
      return configJson.containsKey("endpoint")
          && StrUtil.isNotBlank(configJson.getStr("endpoint"));
    } catch (Exception e) {
      log.error("验证HTTP输入配置失败: {}", e.getMessage(), e);
      return false;
    }
  }

  private BaseUPRequest transformToIoTData(Object externalData, DataBridgeConfig config) {
    try {
      if (externalData instanceof String) {
        String payload = (String) externalData;
        JSONObject data = JSONUtil.parseObj(payload);

        // 构建BaseUPRequest
        BaseUPRequest request = new BaseUPRequest();
        request.setMessageType(MessageType.PROPERTIES);
        request.setProperties(new HashMap<>());

        // 设置设备信息
        IoTDeviceDTO deviceDTO = new IoTDeviceDTO();
        deviceDTO.setDeviceId(data.getStr("deviceId", "unknown"));
        deviceDTO.setProductKey(data.getStr("productKey", "unknown"));
        request.setIoTDeviceDTO(deviceDTO);

        // 设置属性
        if (data.containsKey("properties")) {
          Object properties = data.get("properties");
          if (properties instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> props = (Map<String, Object>) properties;
            request.setProperties(props);
          }
        }

        return request;
      }
    } catch (Exception e) {
      log.error("转换外部数据为IoT格式失败: {}", e.getMessage(), e);
    }
    return null;
  }

  private String buildUrl(ResourceConnection connection) {
    // 默认使用http协议，可以通过extraConfig配置SSL
    String protocol = "http";
    if (StrUtil.isNotBlank(connection.getExtraConfig())) {
      try {
        JSONObject extraConfig = JSONUtil.parseObj(connection.getExtraConfig());
        if (extraConfig.getBool("ssl", false)) {
          protocol = "https";
        }
      } catch (Exception e) {
        log.warn("解析SSL配置失败: {}", e.getMessage());
      }
    }

    String path = "/";
    if (StrUtil.isNotBlank(connection.getExtraConfig())) {
      try {
        JSONObject extraConfig = JSONUtil.parseObj(connection.getExtraConfig());
        path = extraConfig.getStr("path", "/");
      } catch (Exception e) {
        log.warn("解析路径配置失败: {}", e.getMessage());
      }
    }

    return String.format(
        "%s://%s:%d%s", protocol, connection.getHost(), connection.getPort(), path);
  }

  // TODO: 提取endpoint的方法，用于HTTP数据拉取
  // private String extractEndpoint(DataBridgeConfig config) {
  //     try {
  //         JSONObject configJson = JSONUtil.parseObj(config.getConfig());
  //         return configJson.getStr("endpoint", "/api/data");
  //     } catch (Exception e) {
  //         log.warn("解析HTTP输入配置失败，使用默认endpoint: {}", e.getMessage());
  //         return "/api/data";
  //     }
  // }
}
