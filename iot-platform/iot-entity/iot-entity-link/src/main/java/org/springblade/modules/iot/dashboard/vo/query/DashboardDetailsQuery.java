package org.springblade.modules.iot.dashboard.vo.query;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <p>
 * 表单查询条件VO
 * 仪表板数据详细查询参数
 * </p>
 *
 * @author mqttsnet
 * @date 2023-11-26 19:35:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "DashboardDetailsQuery", description = "仪表板数据详细查询参数")
public class DashboardDetailsQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 增加最大值限制
    @NotNull(message = "时间数量不能为空")
    @Max(value = 100, message = "时间数量不能超过100")
    @Schema(description = "时间数量", required = true)
    private Long limit;

    // 确保时间单位是有效的 TimeUnitEnum 代码
    @NotBlank(message = "时间单位不能为空")
    @Pattern(regexp = "1m|1h|1d", message = "时间单位必须是1m, 1h, 1d")
    @Schema(description = "时间单位", required = true)
    private String time;

    // 确保开始和结束时间格式正确
    @NotNull(message = "开始时间不能为空")
    @Pattern(regexp = "\\d{12}", message = "开始时间格式必须是yyyyMMddHHmm")
    @Schema(description = "开始时间", required = true)
    private String startTime;

    @NotNull(message = "结束时间不能为空")
    @Pattern(regexp = "\\d{12}", message = "结束时间格式必须是yyyyMMddHHmm")
    @Schema(description = "结束时间", required = true)
    private String endTime;
}
