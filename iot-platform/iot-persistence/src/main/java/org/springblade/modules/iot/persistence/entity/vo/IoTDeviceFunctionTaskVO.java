

package org.springblade.modules.iot.persistence.entity.vo;

import org.springblade.modules.iot.pojo.entity.IoTDeviceFunctionTask;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/3/21
 */
@Data
public class IoTDeviceFunctionTaskVO extends IoTDeviceFunctionTask {

  private Integer successNum;
  private Integer totalNum;
}
