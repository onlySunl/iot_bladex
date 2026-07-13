

package org.springblade.modules.iot.persistence.base;

import cn.hutool.json.JSONObject;
import org.springblade.modules.iot.common.message.DownRequest;
import org.springblade.modules.iot.persistence.dto.IoTDeviceDTO;
import org.springblade.modules.iot.pojo.entity.IoTProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/** */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class BaseDownRequest extends DownRequest {

  private Object params;

  private transient IoTProduct ioTProduct;

  private transient IoTDeviceDTO ioTDeviceDTO;

  private JSONObject data;
}
