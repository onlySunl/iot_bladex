

package org.springblade.modules.iot.pojo.entity;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springblade.common.entity.CustomBaseEntity;

import java.util.stream.Collectors;
@TableName("iot_device_protocol")
@Data
@EqualsAndHashCode(callSuper = true)
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

}
