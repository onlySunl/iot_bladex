package org.springblade.modules.iot.vo.param.plugin;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * -----------------------------------------------------------------------------
 * File Name: PluginActionRequestParamVO
 * -----------------------------------------------------------------------------
 * Description:
 * 插件安装或卸载操作请求参数
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/11/15       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/11/15 17:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "PluginActionRequestParamVO", description = "插件安装或卸载操作请求参数")
public class PluginActionRequestParamVO {
    /**
     * 插件ID
     */
    @Schema(description = "插件ID", required = true)
    @NotNull(message = "插件ID不能为空")
    private Long pluginId;

    /**
     * 插件实例ID
     */
    @Schema(description = "插件实例ID", required = true)
    @NotNull(message = "插件实例ID")
    private Long instanceId;

    /**
     * 操作状态（安装或卸载）
     */
    @Schema(description = "插件操作状态（安装:0,卸载:1）", required = true)
    @NotNull(message = "操作状态不能为空")
    @Min(value = 0, message = "操作状态不能小于0")
    @Max(value = 1, message = "操作状态不能大于1")
    private Integer status;

}
