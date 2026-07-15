package org.springblade.modules.iot.component.modbusCustom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModbusConfig {
    private Integer port;

    private Integer timer;

}
