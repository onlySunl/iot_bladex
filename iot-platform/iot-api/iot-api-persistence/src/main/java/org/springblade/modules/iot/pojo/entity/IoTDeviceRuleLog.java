

package org.springblade.modules.iot.pojo.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_rule_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceRuleLog extends CustomBaseEntity {

  /** 业务ID */
  @TableField("c_id")
  private String cId;

  /** 业务名称 */
  @TableField("c_name")
  private String cName;

  /** 执行状态 */
  @TableField("c_status")
  private Byte cStatus;

  /** 1-场景联动，2-数据流转 */
  @TableField("c_type")
  private Byte cType;

  /** 条件 */
  private String conditions;

  @TableField("c_device_meta")
  private String cDeviceMeta;

  private String content;
}
