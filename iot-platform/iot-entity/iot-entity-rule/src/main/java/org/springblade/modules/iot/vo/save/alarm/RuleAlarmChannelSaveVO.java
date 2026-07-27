package org.springblade.modules.iot.vo.save.alarm;

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

import java.io.Serializable;

/**
 * <p>
 * 表单保存方法VO
 * 告警规则渠道表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:14:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "RuleAlarmChannelSaveVO", description = "告警规则渠道表")
public class RuleAlarmChannelSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 渠道名称
     */
    @Schema(description = "渠道名称")
    @NotEmpty(message = "请填写渠道名称")
    @Size(max = 200, message = "渠道名称长度不能超过{max}")
    private String channelName;
    /**
     * 渠道类型
     */
    @Schema(description = "渠道类型")
    @NotNull(message = "请填写渠道类型")
    private Integer channelType;
    /**
     * 告警配置
     */
    @Schema(description = "告警配置")
    @NotEmpty(message = "请填写告警配置")
    @Size(max = 2147483647, message = "告警配置长度不能超过{max}")
    private String channelConfig;
    /**
     * 启用状态
     */
    @Schema(description = "启用状态")
    @NotNull(message = "请填写启用状态")
    private Integer status;
    /**
     * 描述
     */
    @Schema(description = "描述")
    @Size(max = 255, message = "描述长度不能超过{max}")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;


}
