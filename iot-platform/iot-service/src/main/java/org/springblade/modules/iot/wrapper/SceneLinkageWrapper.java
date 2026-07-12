package org.springblade.modules.iot.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.pojo.entity.SceneLinkage;
import org.springblade.modules.iot.pojo.vo.SceneLinkageVO;

public class SceneLinkageWrapper extends BaseEntityWrapper<SceneLinkage, SceneLinkageVO> {

	public static SceneLinkageWrapper build() {
		return new SceneLinkageWrapper();
	}

	@Override
	public SceneLinkageVO entityVO(SceneLinkage entity) {
		return Func.copyProperties(entity, SceneLinkageVO.class);
	}
}
