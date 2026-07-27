package org.springblade.modules.iot.productcommandrequest.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 表单查询条件VO
 * 产品模型服务命令属性请求参数
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "ProductCommandRequestPageQuery", description = "产品模型服务命令属性请求参数")
public class ProductCommandRequestPageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    /**
     * 服务ID
     */
    @Schema(description = "服务ID")
    private Long serviceId;
    /**
     * 命令ID
     */
    @Schema(description = "命令ID")
    private Long commandId;
    /**
     * 参数编码
     */
    @Schema(description = "参数编码")
    private String parameterCode;
    /**
     * 命令中参数的名字。
     */
    @Schema(description = "命令中参数的名字。")
    private String parameterName;
    /**
     * 命令中参数的描述，不影响实际功能，可配置为空字符串。
     */
    @Schema(description = "命令中参数的描述，不影响实际功能，可配置为空字符串。")
    private String parameterDescription;
    /**
     * 指示数据类型。取值范围：string、int、decimal
     */
    @Schema(description = "指示数据类型。取值范围：string、int、decimal")
    private String datatype;
    /**
     * 指示枚举值。如开关状态status可有如下取值enumList" : [OPEN,CLOSE]目前本字段是非功能性字段，仅起到描述作用。建议准确定义。
     */
    @Schema(description = "指示枚举值。如开关状态status可有如下取值enumList : [OPEN,CLOSE]目前本字段是非功能性字段，仅起到描述作用。建议准确定义。")
    private String enumlist;
    /**
     * 指示最大值。仅当dataType为int、decimal时生效，逻辑小于等于。
     */
    @Schema(description = "指示最大值。仅当dataType为int、decimal时生效，逻辑小于等于。")
    private String max;
    /**
     * 指示字符串长度。仅当dataType为string时生效。
     */
    @Schema(description = "指示字符串长度。仅当dataType为string时生效。")
    private String maxlength;
    /**
     * 指示最小值。仅当dataType为int、decimal时生效，逻辑大于等于。
     */
    @Schema(description = "指示最小值。仅当dataType为int、decimal时生效，逻辑大于等于。")
    private String min;
    /**
     * 指示本条属性是否必填，取值为0或1，默认取值1（必填）。目前本字段是非功能性字段，仅起到描述作用。
     */
    @Schema(description = "指示本条属性是否必填，取值为0或1，默认取值1（必填）。目前本字段是非功能性字段，仅起到描述作用。")
    private String required;
    /**
     * 指示步长。
     */
    @Schema(description = "指示步长。")
    private String step;
    /**
     * 指示单位。取值根据参数确定，如：•温度单位：“C”或“K”•百分比单位：“%”•压强单位：“Pa”或“kPa”
     */
    @Schema(description = "指示单位。取值根据参数确定，如：•温度单位：“C”或“K”•百分比单位：“%”•压强单位：“Pa”或“kPa”")
    private String unit;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}
