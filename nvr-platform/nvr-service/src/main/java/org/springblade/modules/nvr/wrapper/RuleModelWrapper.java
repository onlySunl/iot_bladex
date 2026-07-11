package org.springblade.modules.nvr.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.nvr.pojo.entity.RuleModel;
import org.springblade.modules.nvr.pojo.vo.RuleModelVO;

import java.util.Objects;

/**
 * IoT规则模型包装类
 */
public class RuleModelWrapper extends BaseEntityWrapper<RuleModel, RuleModelVO> {

	public static RuleModelWrapper build() {
		return new RuleModelWrapper();
	}

	@Override
	public RuleModelVO entityVO(RuleModel ruleModel) {
		RuleModelVO vo = Objects.requireNonNull(BeanUtil.copyProperties(ruleModel, RuleModelVO.class));
		return vo;
	}
}
