package org.springblade.modules.iot.device.vo.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springblade.basic.base.entity.Entity;
import org.springblade.modules.iot.device.entity.Device;

import java.io.Serial;
import java.io.Serializable;

/**
 * 设备更新VO - 只包含设备实体不存在的属性
 *
 * @author mqttsnet
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Schema(title = "DeviceUpdateVO", description = "设备档案信息表")
public class DeviceUpdateVO extends Device {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "设备位置信息")
    private DeviceLocationUpdateVO deviceLocationUpdateVO;
}
