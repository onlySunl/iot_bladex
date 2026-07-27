package org.springblade.modules.iot.dashboard.vo.result;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <p>
 * 表单查询条件VO
 * 仪表板数据详细统计VO
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
@Schema(title = "DashboardDetailsResultVo", description = "仪表板数据详细统计VO")
public class DashboardDetailsResultVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "上行数据明细")
    private List<LinkDataDetail> uplinkDetails;

    @Schema(description = "下行数据明细")
    private List<LinkDataDetail> downlinkDetails;

    // 内部类用于表示单个时间点的数据明细
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LinkDataDetail implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
        @Schema(description = "计数")
        private Integer value;
        @Schema(description = "时间名称")
        private String timeString;

        public static LinkDataDetail of(String timeString, Integer count) {
            return new LinkDataDetail(count, timeString);
        }
    }

}
