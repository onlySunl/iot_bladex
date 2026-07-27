package org.springblade.modules.iot.protocol.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 查询设备信息数据模型
 * @Author: ShiHuan SUN
 * @E-mail: 13733918655@163.com
 * @Website: https://mqttsnet.com
 * @CreateDate: 2022/4/25$ 12:52$
 * @UpdateUser: ShiHuan SUN
 * @UpdateDate: 2024/01/10$ 12:52$
 * @UpdateRemark: 修改内容
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "TopoQueryDeviceParam", description = "查询设备信息数据模型")
public class TopoQueryDeviceParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "设备标识集合")
    @NotNull(message = "设备标识集合不能为空")
    private List<String> deviceIds;
}
