

package org.springblade.modules.iot.persistence.base;

import org.springblade.modules.iot.core.message.UPRequest;
import org.springblade.modules.iot.persistence.dto.IoTDeviceDTO;
import org.springblade.modules.iot.pojo.entity.IoTProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2023/1/12
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class BaseUPRequest extends UPRequest {

  private transient IoTDeviceDTO ioTDeviceDTO;

  private transient IoTProduct ioTProduct;

  /** 指令 */
  private String commandId;

  /** 指令 */
  private Integer commandStatus;
}
