package org.springblade.modules.iot.vo.param.linkage;

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
 * 告警记录处理Param
 * </p>
 *
 * @author mqttsnet
 * @date 2023-09-09 21:15:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "RuleAlarmRecordHandleParamVO", description = "告警记录处理参数")
public class RuleAlarmRecordHandleParamVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "主键")
    @NotNull(message = "请填写主键")
    private Long id;

    @Schema(description = "处理状态", allowableValues = {"0", "1", "2"}, example = "0")
    @NotNull(message = "请填写处理状态")
    private Integer handledStatus;

    @Schema(description = "处理或解决记录")
    @Size(max = 2147483647, message = "记录内容长度不能超过{max}")
    @NotEmpty(message = "请填写处理或解决记录")
    private String handleNotes;


}
