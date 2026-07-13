/*
 *
 *
 *
 *
 */

package org.springblade.modules.iot.pojo.entity;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.universal.core.protocol.support.ProtocolSupportDefinition;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_protocol")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IoTDeviceProtocol extends CustomBaseEntity {
  private String name;
  private String description;
  private Byte state;
  
  private String type;
  private String configuration;
  private String example;

  /**
   * 版本号
   */
  @TableField("version")
  private String version;

  /**
   * 创建时间
   */
  public ProtocolSupportDefinition toDefinition() {
    ProtocolSupportDefinition definition = new ProtocolSupportDefinition();
    JSONObject object = JSONUtil.parseObj(getConfiguration());
    definition.setConfiguration(object);
    definition.setDescription(getDescription());
    definition.setId(getId());
    definition.setName(getName());
    definition.setProvider(object.getStr("provider"));
    definition.setType(getType());
    definition.setState(getState());
    if (object != null && object.containsKey("supportMethods")) {
      JSONArray jsonArray = object.getJSONArray("supportMethods");
      definition.setSupportMethods(jsonArray.stream().map(s -> {
        return (String) s;
      }).collect(Collectors.toSet()));
    }
    return definition;
  }

  public ProtocolSupportDefinition toDefinitionNoScript() {
    ProtocolSupportDefinition definition = new ProtocolSupportDefinition();
    JSONObject object = JSONUtil.parseObj(getConfiguration());
    if (object != null && object.containsKey("location")) {
      object.remove("location");
    }
    definition.setConfiguration(object);
    definition.setDescription(getDescription());
    definition.setId(getId());
    definition.setName(getName());
    definition.setProvider(object.getStr("provider"));
    definition.setType(getType());
    definition.setState(getState());
    if (object != null && object.containsKey("supportMethods")) {
      JSONArray jsonArray = object.getJSONArray("supportMethods");
      definition.setSupportMethods(jsonArray.stream().map(s -> {
        return (String) s;
      }).collect(Collectors.toSet()));
    }
    return definition;
  }
}
