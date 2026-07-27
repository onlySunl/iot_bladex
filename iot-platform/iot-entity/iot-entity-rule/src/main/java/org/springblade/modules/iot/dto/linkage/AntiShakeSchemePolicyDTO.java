package org.springblade.modules.iot.dto.linkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * -----------------------------------------------------------------------------
 * File Name: AntiShakeSchemePolicyDTO
 * -----------------------------------------------------------------------------
 * Description:
 * 防抖策略实体，用于定义如何处理连续的触发事件
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/1/1       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2024/1/1 21:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "AntiShakeSchemePolicyDTO", description = "防抖策略实体，用于定义如何处理连续的触发事件")
public class AntiShakeSchemePolicyDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "时间单位", example = "second")
    private String unit;

    @Schema(description = "频率")
    private Frequency frequency;

    @Schema(description = "发生")
    private Occurrence occurrence;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    @EqualsAndHashCode
    @Schema(title = "Frequency", description = "定义事件发生的频率")
    public static class Frequency implements Serializable {
        private static final long serialVersionUID = 1L;

        @Schema(description = "时间值", example = "1")
        private Integer timeValue;

        @Schema(description = "计数", example = "1")
        private Integer count;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    @EqualsAndHashCode
    @Schema(title = "Occurrence", description = "定义事件的发生次序")
    public static class Occurrence implements Serializable {
        private static final long serialVersionUID = 1L;

        @Schema(description = "是否为第一次发生", example = "true")
        private Boolean first;

        @Schema(description = "是否为最后一次发生", example = "false")
        private Boolean last;
    }
}
