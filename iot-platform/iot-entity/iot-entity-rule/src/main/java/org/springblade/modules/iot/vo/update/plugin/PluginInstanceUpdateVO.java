package org.springblade.modules.iot.vo.update.plugin;

import org.springblade.common.entity.CustomBaseEntity;
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
 * 插件实例信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:02:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(description = "插件实例信息表")
public class PluginInstanceUpdateVO implements Serializable {

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
     * 实例名称，用于标识实例的友好名称
     */
    @Schema(description = "实例名称，用于标识实例的友好名称")
    @NotEmpty(message = "请填写实例名称，用于标识实例的友好名称")
    @Size(max = 255, message = "实例名称，用于标识实例的友好名称长度不能超过{max}")
    private String instanceName;
    /**
     * 应用名称，SpringBoot应用名称
     */
    @Schema(description = "应用名称，SpringBoot应用名称")
    @NotEmpty(message = "请填写应用名称，SpringBoot应用名称")
    @Size(max = 255, message = "应用名称，SpringBoot应用名称长度不能超过{max}")
    private String applicationName;
    /**
     * 实例运行所在的机器 IP 地址
     */
    @Schema(description = "实例运行所在的机器 IP 地址")
    @NotEmpty(message = "请填写实例运行所在的机器 IP 地址")
    @Size(max = 45, message = "实例运行所在的机器 IP 地址长度不能超过{max}")
    private String machineIp;
    /**
     * 实例可用端口范围起始值
     */
    @Schema(description = "实例可用端口范围起始值")
    @NotNull(message = "请填写实例可用端口范围起始值")
    private Integer portRangeStart;
    /**
     * 实例可用端口范围结束值
     */
    @Schema(description = "实例可用端口范围结束值")
    @NotNull(message = "请填写实例可用端口范围结束值")
    private Integer portRangeEnd;
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
    /**
     * 实例的权重
     */
    @Schema(description = "实例的权重")
    @NotNull(message = "请填写实例的权重")
    private Integer weight;
    /**
     * 实例的健康状态
     */
    @Schema(description = "实例的健康状态")
    @NotNull(message = "请填写实例的健康状态")
    private Boolean healthy;
    /**
     * 实例是否启用
     */
    @Schema(description = "实例是否启用")
    @NotNull(message = "请填写实例是否启用")
    private Boolean enabled;
    /**
     * 实例是否为临时实例
     */
    @Schema(description = "实例是否为临时实例")
    @NotNull(message = "请填写实例是否为临时实例")
    private Boolean ephemeral;
    /**
     * 实例所在集群名称
     */
    @Schema(description = "实例所在集群名称")
    @NotEmpty(message = "请填写实例所在集群名称")
    @Size(max = 50, message = "实例所在集群名称长度不能超过{max}")
    private String clusterName;
    /**
     * 实例心跳间隔时间(毫秒)
     */
    @Schema(description = "实例心跳间隔时间(毫秒)")
    private Long heartBeatInterval;
    /**
     * 实例心跳超时时间(毫秒)
     */
    @Schema(description = "实例心跳超时时间(毫秒)")
    private Long heartBeatTimeOut;
    /**
     * 实例IP删除超时时间(毫秒)
     */
    @Schema(description = "实例IP删除超时时间(毫秒)")
    private Long ipDeleteTimeOut;
    /**
     * 实例机器端口
     */
    @Schema(description = "实例机器端口")
    @NotEmpty(message = "请填写实例机器端口")
    @Size(max = 20, message = "实例机器端口长度不能超过{max}")
    private String machinePort;


}
