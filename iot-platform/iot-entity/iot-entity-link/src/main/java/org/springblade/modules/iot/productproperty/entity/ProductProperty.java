package org.springblade.modules.iot.productproperty.entity;
import org.springblade.basic.base.entity.Entity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 产品模型服务属性表
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
@AutoTable(value = "iot_product_property", comment = "ProductProperty table")
public class ProductProperty extends Entity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 服务ID
     */
    @AutoColumn(value = "service_id", comment = "服务ID")
    private Long serviceId;
    /**
     * 指示属性编码
     */
    @AutoColumn(value = "property_code", comment = "指示属性编码")
    private String propertyCode;
    /**
     * 指示属性名称
     */
    @AutoColumn(value = "property_name", comment = "指示属性名称")
    private String propertyName;
    /**
     * 指示数据类型：取值范围：string、int、decimal（float和double都可以使用此类型）、DateTime、jsonObject上报数据时，复杂类型数据格式如下：•DateTime:yyyyMMdd’T’HHmmss’Z’如:20151212T121212Z•jsonObject：自定义json结构体，平台不理解只透传
     */
    @AutoColumn(value = "datatype", comment = "指示数据类型：取值范围：string、int、decimal（float和double都可以使用此类型）、DateTime、jsonObject上报数据时，复杂类型数据格式如下：•DateTime:yyyyMMdd’T’HHmmss’Z’如:20151212T121212Z•jsonObject：自定义json结构体，平台不理解只透传")
    private String datatype;
    /**
     * 属性描述，不影响实际功能，可配置为空字符串。
     */
    @AutoColumn(value = "description", comment = "属性描述，不影响实际功能，可配置为空字符串。")
    private String description;
    /**
     * 指示枚举值:如开关状态status可有如下取值enumList: [OPEN,CLOSE]目前本字段是非功能性字段，仅起到描述作用。建议准确定义。
     */
    @AutoColumn(value = "enumlist", comment = "指示枚举值:如开关状态status可有如下取值enumList: [OPEN,CLOSE]目前本字段是非功能性字段，仅起到描述作用。建议准确定义。")
    private String enumlist;
    /**
     * 指示最大值。支持长度不超过50的数字。仅当dataType为int、decimal时生效，逻辑小于等于。
     */
    @AutoColumn(value = "max", comment = "指示最大值。支持长度不超过50的数字。仅当dataType为int、decimal时生效，逻辑小于等于。")
    private String max;
    /**
     * 指示字符串长度。仅当dataType为string、DateTime时生效。
     */
    @AutoColumn(value = "maxlength", comment = "指示字符串长度。仅当dataType为string、DateTime时生效。")
    private String maxlength;
    /**
     * 指示访问模式。R:可读；W:可写；E属性值更改时上报数据取值范围：R、RW、RE、RWE
     */
    @AutoColumn(value = "method", comment = "指示访问模式。R:可读；W:可写；E属性值更改时上报数据取值范围：R、RW、RE、RWE")
    private String method;
    /**
     * 指示最小值。支持长度不超过50的数字。仅当dataType为int、decimal时生效，逻辑大于等于。
     */
    @AutoColumn(value = "min", comment = "指示最小值。支持长度不超过50的数字。仅当dataType为int、decimal时生效，逻辑大于等于。")
    private String min;
    /**
     * 指示本条属性是否必填，取值为0或1，默认取值1（必填）。目前本字段是非功能性字段，仅起到描述作用。(字典值link_product_isRequired：0非必填 1必填)
     */
    @AutoColumn(value = "required", comment = "指示本条属性是否必填，取值为0或1，默认取值1（必填）。目前本字段是非功能性字段，仅起到描述作用。(字典值link_product_isRequired：0非必填 1必填)")
    private String required;
    /**
     * 指示步长。
     */
    @AutoColumn(value = "step", comment = "指示步长。")
    private String step;
    /**
     * 指示单位。支持长度不超过50。取值根据参数确定，如：•温度单位：“C”或“K”•百分比单位：“%”•压强单位：“Pa”或“kPa”
     */
    @AutoColumn(value = "unit", comment = "指示单位。支持长度不超过50。取值根据参数确定，如：•温度单位：“C”或“K”•百分比单位：“%”•压强单位：“Pa”或“kPa”")
    private String unit;
    /**
     * 图标png图
     */
    @AutoColumn(value = "icon", comment = "图标png图")
    private String icon;
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
