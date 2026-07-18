

package org.springblade.modules.iot.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @Author: EnjoyIot
 * @Date: 2025/1/8 17:00
 * @Version: V1.0
 * @Description: id请求
 */
@Data
public class IdReqVo {
    @Schema(description = "id")
    @NotNull(message = "id不许为空")
    private Long id;
}
