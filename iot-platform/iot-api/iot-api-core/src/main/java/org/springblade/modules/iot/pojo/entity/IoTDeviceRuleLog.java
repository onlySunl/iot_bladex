

package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("iot_device_rule_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceRuleLog extends CustomBaseEntity {


  /** 业务ID */
  @TableField(value = "c_id")
  @AutoColumn(comment = "业务ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String cId;

  /** 业务名称 */
  @TableField(value = "c_name")
  @AutoColumn(comment = "业务名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String cName;

  /** 执行状态 */
  @TableField(value = "c_status")
  @AutoColumn(comment = "执行状态", defaultValueType = DefaultValueEnum.NULL)
  private Byte cStatus;

  /** 1-场景联动，2-数据流转 */
  @TableField(value = "c_type")
  @AutoColumn(comment = "1-场景联动，2-数据流转", defaultValueType = DefaultValueEnum.NULL)
  private Byte cType;

  /** 条件 */
  private String conditions;




  @TableField(value = "c_device_meta")
  @AutoColumn(comment = "cDeviceMeta", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String cDeviceMeta;

  private String content;

  private static final long serialVersionUID = 1L;
}
