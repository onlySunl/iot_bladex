package org.springblade.modules.iot.vo.update.plugin;

import org.springblade.basic.base.entity.Entity;
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
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 表单修改方法VO
 * 插件与实例及端口管理表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:30:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(description = "插件与实例及端口管理表")
public class PluginInstanceMappingUpdateVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = Entity.Update.class)
    private Long id;

    /**
     * 插件唯一标识插件唯一标识
     */
    @Schema(description = "插件唯一标识插件唯一标识")
    @NotEmpty(message = "请填写插件唯一标识插件唯一标识")
    @Size(max = 255, message = "插件唯一标识插件唯一标识长度不能超过{max}")
    private String pluginIdentification;
    /**
     * 实例唯一标识
     */
    @Schema(description = "实例唯一标识")
    @NotEmpty(message = "请填写实例唯一标识")
    @Size(max = 255, message = "实例唯一标识长度不能超过{max}")
    private String instanceIdentification;
    /**
     * 插件在该实例上使用的端口号
     */
    @Schema(description = "插件在该实例上使用的端口号")
    @NotNull(message = "请填写插件在该实例上使用的端口号")
    private Integer port;
    /**
     * 端口类型或用途（如 HTTP, HTTPS, 管理端口等）
     */
    @Schema(description = "端口类型或用途（如 HTTP, HTTPS, 管理端口等）")
    @Size(max = 50, message = "端口类型或用途（如 HTTP, HTTPS, 管理端口等）长度不能超过{max}")
    private String portType;
    /**
     * 状态：0-正常，1-异常
     */
    @Schema(description = "状态：0-正常，1-异常")
    @NotNull(message = "请填写状态：0-正常，1-异常")
    private Integer status;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过{max}")
    private String remark;


}
