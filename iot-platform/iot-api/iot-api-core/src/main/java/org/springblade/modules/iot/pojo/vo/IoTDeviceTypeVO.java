

package org.springblade.modules.iot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

/**
 * 设备类型视图对象 @Author gitee.com/NexIoT
 *
 * @since 2023/1/5 11:36
 */
@Data
@Schema(description = "设备类型视图对象")
public class IoTDeviceTypeVO {

  private static final long serialVersionUID = 1L;

  /** 设备类型 */
  @Schema(description = "设备类型")
  private String deviceType;

  /** 设备类型名称 */
  @Schema(description = "设备类型名称")
  private String deviceTypeName;

  @Schema(description = "设备型号")
  private List<IoTDeviceModelVO> models;
}
