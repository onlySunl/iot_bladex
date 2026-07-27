package org.springblade.modules.iot.vo.result.alarm;

import org.springblade.model.vo.AuditableResultVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 * <p>
 * 表单查询方法返回值VO
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
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "RuleAlarmChannelResultVO", description = "告警规则渠道表")
public class RuleAlarmChannelResultVO extends AuditableResultVO {

    @Serial
    private static final long serialVersionUID = 1L;

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
    /**
     * 描述
     */


}
