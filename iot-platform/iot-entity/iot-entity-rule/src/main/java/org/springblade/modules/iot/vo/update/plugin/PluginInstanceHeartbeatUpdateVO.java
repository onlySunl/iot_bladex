package org.springblade.modules.iot.vo.update.plugin;

import org.springblade.basic.base.entity.CustomBaseEntity;
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
import java.time.LocalDateTime;

/**
 * <p>
 * 表单修改方法VO
 * 插件实例心跳表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:31:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(description = "插件实例心跳表")
public class PluginInstanceHeartbeatUpdateVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = CustomBaseEntity.Update.class)
    private Long id;

    /**
     * 实例唯一标识
     */
    @Schema(description = "实例唯一标识")
    @NotEmpty(message = "请填写实例唯一标识")
    @Size(max = 255, message = "实例唯一标识长度不能超过{max}")
    private String instanceIdentification;
    /**
     * 插件运行所在的机器 IP 地址
     */
    @Schema(description = "插件运行所在的机器 IP 地址")
    @NotEmpty(message = "请填写插件运行所在的机器 IP 地址")
    @Size(max = 45, message = "插件运行所在的机器 IP 地址长度不能超过{max}")
    private String machineIp;
    /**
     * 上次心跳时间
     */
    @Schema(description = "上次心跳时间")
    @NotNull(message = "请填写上次心跳时间")
    private LocalDateTime lastHeartbeatTime;
    /**
     * 心跳间隔时间（秒）
     */
    @Schema(description = "心跳间隔时间（秒）")
    @NotNull(message = "请填写心跳间隔时间（秒）")
    private Integer heartbeatInterval;
    /**
     * 心跳状态：0-正常，1-异常
     */
    @Schema(description = "心跳状态：0-正常，1-异常")
    @NotNull(message = "请填写心跳状态：0-正常，1-异常")
    private Integer status;
    /**
     * 心跳详细信息
     */
    @Schema(description = "心跳详细信息")
    @Size(max = 500, message = "心跳详细信息长度不能超过{max}")
    private String heartbeatMessage;
    /**
     * 扩展参数（预留）
     */
    @Schema(description = "扩展参数（预留）")
    @Size(max = 65535, message = "扩展参数（预留）长度不能超过{max}")
    private String extendParams;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过{max}")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;


}
