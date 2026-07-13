

package org.springblade.modules.iot.pojo.vo;

import org.springblade.modules.iot.pojo.entity.IoTDeviceLogMetadata;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Schema(description = "设备属性视图对象")
public class IoTDeviceLogMetadataVO extends IoTDeviceLogMetadata {


  /*-----------------日志信息---------------------*/

  /** 日志ID，非自增 */
  //  private Long id;






  private LocalDateTime createTime;
}
