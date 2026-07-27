package org.springblade.modules.iot.vo.query.alarm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 表单查询条件VO
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
@Schema(title = "RuleAlarmChannelPageQuery", description = "告警规则渠道表")
public class RuleAlarmChannelPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "主键集合")
    private List<Long> ids;
    /**
     * 渠道名称
     */
    @Schema(description = "渠道名称")
    private String channelName;
    /**
     * 渠道类型
     */
    @Schema(description = "渠道类型")
    private Integer channelType;
    /**
     * 告警配置
     */
    @Schema(description = "告警配置")
    private String channelConfig;
    /**
     * 启用状态
     */
    @Schema(description = "启用状态")
    private Integer status;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;


}
