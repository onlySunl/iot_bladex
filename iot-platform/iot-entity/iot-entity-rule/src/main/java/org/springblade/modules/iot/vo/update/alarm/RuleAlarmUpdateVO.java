package org.springblade.modules.iot.vo.update.alarm;

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

import java.io.Serializable;

/**
 * <p>
 * 表单修改方法VO
 * 告警规则表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:14:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "RuleAlarmUpdateVO", description = "告警规则表")
public class RuleAlarmUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = CustomBaseEntity.Update.class)
    private Long id;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @NotEmpty(message = "请填写应用ID")
    @Size(max = 64, message = "应用ID长度不能超过{max}")
    private String appId;
    /**
     * 告警名称
     */
    @Schema(description = "告警名称")
    @NotEmpty(message = "请填写告警名称")
    @Size(max = 200, message = "告警名称长度不能超过{max}")
    private String alarmName;
    /**
     * 告警编码
     */
    @Schema(description = "告警编码")
    @NotEmpty(message = "请填写告警编码")
    @Size(max = 100, message = "告警编码长度不能超过{max}")
    private String alarmIdentification;
    /**
     * 告警场景
     */
    @Schema(description = "告警场景")
    @NotEmpty(message = "请填写告警场景")
    @Size(max = 255, message = "告警场景长度不能超过{max}")
    private String alarmScene;
    /**
     * 告警渠道ID集合
     */
    @Schema(description = "告警渠道ID集合")
    @NotEmpty(message = "请填写告警渠道ID集合")
    @Size(max = 255, message = "告警渠道ID集合长度不能超过{max}")
    private String alarmChannelIds;
    /**
     * 告警级别
     */
    @Schema(description = "告警级别")
    @NotNull(message = "请填写告警级别")
    private Integer level;
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
