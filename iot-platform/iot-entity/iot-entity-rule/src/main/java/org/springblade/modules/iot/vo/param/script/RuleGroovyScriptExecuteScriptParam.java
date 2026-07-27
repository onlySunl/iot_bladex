package org.springblade.modules.iot.vo.param.script;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 规则脚本直接编译参数 实体
 *
 * @author mqttsnet 2025/03/18 12:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "RuleGroovyScriptExecuteScriptParam", description = "规则脚本执行脚本参数")
public class RuleGroovyScriptExecuteScriptParam {


    /**
     * 脚本唯一标识
     * 唯一键定义(命名空间:平台编码:产品编码:渠道编码:业务编码:业务标识)
     * 中间通过:分割 StrPool.COLON
     */
    @Schema(description = "脚本唯一键")
    @NotEmpty(message = "脚本唯一键不能为空")
    @Size(max = 100, message = "脚本唯一键长度不能超过{max}")
    private String scriptUniqueKey;

    /**
     * 执行参数
     */
    @Schema(description = "执行参数")
    @NotEmpty(message = "请填写执行参数")
    @Size(max = 2147483647, message = "执行参数长度不能超过{max}")
    private String executeParams;

}
