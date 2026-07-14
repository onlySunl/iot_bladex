

package org.springblade.modules.iot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.common.entity.CustomBaseEntity;
/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/3/21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("iot_device_function_history")
public class IoTDeviceFunctionHistory extends CustomBaseEntity {

  private String iotId;
  private String productKey;
  private String deviceId;
  private String deviceName;

  @Schema(description = "指令配置状态  0.待下发；1.下发中；2.已下发")
  private Integer downState;

  @Schema(description = "下发结果 0.失败  1.成功")
  private Integer downResult;

  private String downError;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

  private Long taskId;
  private Integer retry;
  private String extParam;
}
