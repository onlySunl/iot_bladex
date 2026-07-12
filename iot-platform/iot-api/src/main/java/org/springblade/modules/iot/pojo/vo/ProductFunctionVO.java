package org.springblade.modules.iot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.pojo.entity.ProductFunction;

/**
 * IoT产品功能定义视图类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "IoT产品功能定义VO")
public class ProductFunctionVO extends ProductFunction {

	@Schema(description = "标签名")
	private String tagName;
}
