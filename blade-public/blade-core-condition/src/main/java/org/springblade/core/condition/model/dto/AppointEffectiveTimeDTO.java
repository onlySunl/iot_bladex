package org.springblade.core.condition.model.dto;

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
 * -----------------------------------------------------------------------------
 * File Name: RuleScheduleDTO.java
 * -----------------------------------------------------------------------------
 * Description:
 * 生效时间DTO
 * -----------------------------------------------------------------------------
 *
 * @author ShiHuan Sun
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * <p>
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023-10-22 23:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "AppointEffectiveTimeDTO", description = "指定生效时间DTO")
public class AppointEffectiveTimeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "频率（Second）", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer frequency = 60;

    @Schema(description = "时间范围, 00:00-23:59")
    private TimeFrame timeframe;

    @Schema(description = "周中信息, 1-7")
    private List<WeekDay> week;

    @Schema(description = "调度任务ID")
    private String taskId;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString(callSuper = true)
    @Accessors(chain = true)
    @EqualsAndHashCode
    @Builder
    @Schema(title = "TimeFrame", description = "时间范围")
    public static class TimeFrame implements Serializable {

        private static final long serialVersionUID = 1L;

        @Schema(description = "开始时间, 00:00")
        private String startTime;

        @Schema(description = "结束时间, 23:59")
        private String endTime;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString(callSuper = true)
    @Accessors(chain = true)
    @EqualsAndHashCode
    @Builder
    @Schema(title = "WeekDay", description = "周信息")
    public static class WeekDay implements Serializable {

        private static final long serialVersionUID = 1L;

        @Schema(description = "周中的名称")
        private String name;

        @Schema(description = "英文名称")
        private String eg;

        @Schema(description = "是否被选中")
        private Boolean checked;
    }

}
