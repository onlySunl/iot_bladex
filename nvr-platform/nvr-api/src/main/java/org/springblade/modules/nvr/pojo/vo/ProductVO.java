package org.springblade.modules.nvr.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.nvr.pojo.entity.Product;

/**
 * IoT产品视图类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "IoT产品VO")
public class ProductVO extends Product {

	@Schema(description = "产品状态名")
	private String stateName;

	@Schema(description = "设备数量")
	private Integer deviceCount;
}
