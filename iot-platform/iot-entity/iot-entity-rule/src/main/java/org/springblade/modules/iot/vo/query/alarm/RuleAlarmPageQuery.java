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
@Schema(title = "RuleAlarmPageQuery", description = "告警规则表")
public class RuleAlarmPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;
    /**
     * 告警名称
     */
    @Schema(description = "告警名称")
    private String alarmName;
    /**
     * 告警编码
     */
    @Schema(description = "告警编码")
    private String alarmIdentification;

    /**
     * 告警编码集合
     */
    @Schema(description = "告警编码集合")
    private List<String> alarmIdentificationList;

    /**
     * 告警场景
     */
    @Schema(description = "告警场景")
    private String alarmScene;
    /**
     * 告警渠道ID集合
     */
    @Schema(description = "告警渠道ID集合")
    private String alarmChannelIds;
    /**
     * 告警级别
     */
    @Schema(description = "告警级别")
    private Integer level;
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
