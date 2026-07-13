

package org.springblade.modules.iot.pojo.vo;

import org.springblade.modules.iot.pojo.entity.IoTDeviceLog;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Schema(description = "设备日志视图对象")
public class IoTDeviceLogVO extends IoTDeviceLog {


  /*-----------------日志信息---------------------*/

  /** 日志ID，非自增 */
  private Long id;











  /** 创建时间 */
  private LocalDateTime createTime;
}
