

package org.springblade.modules.iot.pojo.vo;

import org.springblade.modules.iot.pojo.entity.IoTDeviceFunctionTask;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/3/21
 */
@Data
public class IoTDeviceFunctionTaskVO extends IoTDeviceFunctionTask implements Serializable {

  



  private Integer successNum;
  private Integer totalNum;
  private Integer status;
}
