package org.springblade.modules.iot.dashboard.vo.result;

import java.io.Serial;

import org.springblade.model.vo.AuditableResultVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * @author xiaonannet
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "DashboardTopologySummaryResultVO", description = "拓扑数据统计信息统计VO")
public class DashboardTopologySummaryResultVO extends AuditableResultVO {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "上行数据总量")
    private Long totalUplinkData;

    @Schema(description = "下行数据总量")
    private Long totalDownlinkData;

    @Schema(description = "上行下行数据详细统计")
    private DashboardDetailsResultVO dashboardDetailsResultVo;

}
