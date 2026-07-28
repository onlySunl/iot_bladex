package org.springblade.modules.iot.productcommandrequest.entity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableLogic;
import org.springblade.basic.base.entity.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;

/**
 * <p>
 * 实体类
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
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "iot_product_command_request", comment = "ProductCommandRequest table")
public class ProductCommandRequest extends Entity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 服务ID
     */
    @AutoColumn(value = "service_id", comment = "服务ID")
    private Long serviceId;
    /**
     * 命令ID
     */
    @AutoColumn(value = "command_id", comment = "命令ID")
    private Long commandId;
    /**
     * 参数编码
     */
    @AutoColumn(value = "parameter_code", comment = "参数编码")
    private String parameterCode;
    /**
     * 命令中参数的名字。
     */
    @AutoColumn(value = "parameter_name", comment = "命令中参数的名字。")
    private String parameterName;
    /**
     * 命令中参数的描述，不影响实际功能，可配置为空字符串。
     */
    @AutoColumn(value = "parameter_description", comment = "命令中参数的描述，不影响实际功能，可配置为空字符串。")
    private String parameterDescription;
    /**
     * 指示数据类型。取值范围：string、int、decimal
     */
    @AutoColumn(value = "datatype", comment = "指示数据类型。取值范围：string、int、decimal")
    private String datatype;
    /**
     * 指示枚举值。如开关状态status可有如下取值enumList" : [OPEN,CLOSE]目前本字段是非功能性字段，仅起到描述作用。建议准确定义。
     */
    @AutoColumn(value = "enumlist", comment = "指示枚举值。如开关状态status可有如下取值enumList\" : [OPEN,CLOSE]目前本字段是非功能性字段，仅起到描述作用。建议准确定义。")
    private String enumlist;
    /**
     * 指示最大值。仅当dataType为int、decimal时生效，逻辑小于等于。
     */
    @AutoColumn(value = "max", comment = "指示最大值。仅当dataType为int、decimal时生效，逻辑小于等于。")
    private String max;
    /**
     * 指示字符串长度。仅当dataType为string时生效。
     */
    @AutoColumn(value = "maxlength", comment = "指示字符串长度。仅当dataType为string时生效。")
    private String maxlength;
    /**
     * 指示最小值。仅当dataType为int、decimal时生效，逻辑大于等于。
     */
    @AutoColumn(value = "min", comment = "指示最小值。仅当dataType为int、decimal时生效，逻辑大于等于。")
    private String min;
    /**
     * 指示本条属性是否必填，取值为0或1，默认取值1（必填）。目前本字段是非功能性字段，仅起到描述作用。
     */
    @AutoColumn(value = "required", comment = "指示本条属性是否必填，取值为0或1，默认取值1（必填）。目前本字段是非功能性字段，仅起到描述作用。")
    private String required;
    /**
     * 指示步长。
     */
    @AutoColumn(value = "step", comment = "指示步长。")
    private String step;
    /**
     * 指示单位。取值根据参数确定，如：•温度单位：“C”或“K”•百分比单位：“%”•压强单位：“Pa”或“kPa”
     */
    @AutoColumn(value = "unit", comment = "指示单位。取值根据参数确定，如：•温度单位：“C”或“K”•百分比单位：“%”•压强单位：“Pa”或“kPa”")
    private String unit;
    /**
     * 备注
     */
    /**
     * 创建人组织
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织")
    private Long createdOrgId;

    /**
     * 逻辑删除标识:0-未删除 1-已删除
     */
    @TableLogic
    @AutoColumn(value = "deleted", comment = "逻辑删除标识:0-未删除 1-已删除")
    private Integer deleted;
}
