package org.springblade.modules.nvr.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.nvr.pojo.entity.RuleModel;

/**
 * IoT规则模型视图类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "IoT规则模型VO")
public class RuleModelVO extends RuleModel {

	@Schema(description = "状态名")
	private String statusName;

	@Schema(description = "产品名称")
	private String productName;
}
