package org.springblade.modules.iot.pojo.base;

import org.springblade.modules.iot.core.message.UPRequest;
import org.springblade.modules.iot.persistence.dto.IoTDeviceDTO;
import org.springblade.modules.iot.pojo.entity.IoTProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class BaseUPRequest extends UPRequest {

    private transient IoTDeviceDTO ioTDeviceDTO;

    private transient IoTProduct ioTProduct;

    private String commandId;

    private Integer commandStatus;
}
