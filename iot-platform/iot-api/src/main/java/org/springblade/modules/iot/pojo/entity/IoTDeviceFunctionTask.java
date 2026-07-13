/*
 *
 *
 *
 *
 */

package org.springblade.modules.iot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_function_task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceFunctionTask extends CustomBaseEntity {

  private String taskName;
  private String productKey;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date beginTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date endTime;

  private String creator;
  private String creatorId;
  private String command;
  private String commandData;

}
