package org.springblade.modules.iot.core.protocol.support;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;


import java.util.Set;
import java.util.stream.Collectors;

/**
 * 协议对象转换器
 */
public class ProtocolConvert {

    private ProtocolConvert() {
    }

    /**
     * 完整转换，携带全部配置
     */
    public static ProtocolSupportDefinition toDefinition(IoTDeviceProtocol protocol) {
        return buildDefinition(protocol, false);
    }

    /**
     * 剔除location配置项
     */
    public static ProtocolSupportDefinition toDefinitionNoScript(IoTDeviceProtocol protocol) {
        return buildDefinition(protocol, true);
    }

    /**
     * 核心公共构建方法
     * @param removeLocation 是否移除location节点
     */
    private static ProtocolSupportDefinition buildDefinition(IoTDeviceProtocol protocol, boolean removeLocation) {
        ProtocolSupportDefinition definition = new ProtocolSupportDefinition();
        definition.setId(protocol.getId());
        definition.setName(protocol.getName());
        definition.setDescription(protocol.getDescription());
        definition.setType(protocol.getType());
        definition.setState(protocol.getState());

        String configStr = protocol.getConfiguration();
        if (StrUtil.isBlank(configStr)) {
            return definition;
        }
        JSONObject object = JSONUtil.parseObj(configStr);

        // 是否移除location配置
        if (removeLocation && object.containsKey("location")) {
            object.remove("location");
        }

        definition.setConfiguration(object);
        definition.setProvider(object.getStr("provider"));

        // 解析supportMethods
        if (object.containsKey("supportMethods")) {
            JSONArray jsonArray = object.getJSONArray("supportMethods");
            Set<String> supportMethods = jsonArray.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toSet());
            definition.setSupportMethods(supportMethods);
        }
        return definition;
    }
}