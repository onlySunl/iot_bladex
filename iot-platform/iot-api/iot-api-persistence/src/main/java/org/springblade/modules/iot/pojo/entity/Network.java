

package org.springblade.modules.iot.pojo.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_network")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Network extends CustomBaseEntity {

  /** TCP_CLIENT,MQTT_CLIENT,HTTP_CLIENT,WEB_SOCKET_CLIENT */
  private String type;

  /** 唯一标识 */
  @TableField("product_key")
  private String productKey;

  @TableField("union_id")
  private String unionId;

  private String name;

  /** 详细描述 */
  private String description;


  /** enable,disable */
  private Boolean state;

  /** 配置内容 */
  private String configuration;

}
