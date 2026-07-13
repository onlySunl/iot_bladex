

package org.springblade.modules.iot.pojo.entity;

import org.springblade.modules.iot.persistence.common.inteceptor.SQenGenId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.util.Date;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/3/21
 */
@Data
@TableName("iot_device_function_history")
public class IoTDeviceFunctionHistory extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @KeySql(genId = SQenGenId.class)
@TableField(value = "id")
@AutoColumn(comment = "id", defaultValueType = DefaultValueEnum.NULL)
  private Long id;

@TableField(value = "iot_id")
@AutoColumn(comment = "iotId", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String iotId;
@TableField(value = "product_key")
@AutoColumn(comment = "productKey", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productKey;
@TableField(value = "device_id")
@AutoColumn(comment = "deviceId", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceId;
@TableField(value = "device_name")
@AutoColumn(comment = "deviceName", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceName;

  @Schema(description = "指令配置状态  0.待下发；1.下发中；2.已下发")
@TableField(value = "down_state")
@AutoColumn(comment = "downState", defaultValueType = DefaultValueEnum.NULL)
  private Integer downState;

  @Schema(description = "下发结果 0.失败  1.成功")
@TableField(value = "down_result")
@AutoColumn(comment = "downResult", defaultValueType = DefaultValueEnum.NULL)
  private Integer downResult;

@TableField(value = "down_error")
@AutoColumn(comment = "downError", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String downError;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@TableField(value = "update_time")
@AutoColumn(comment = "updateTime", defaultValueType = DefaultValueEnum.NULL)
  private Date updateTime;

@TableField(value = "task_id")
@AutoColumn(comment = "taskId", defaultValueType = DefaultValueEnum.NULL)
  private Long taskId;
@TableField(value = "retry")
@AutoColumn(comment = "retry", defaultValueType = DefaultValueEnum.NULL)
  private Integer retry;
@TableField(value = "ext_param")
@AutoColumn(comment = "extParam", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String extParam;
}
