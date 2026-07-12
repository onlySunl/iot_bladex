package org.springblade.modules.iot.wrapper;

import org.springblade.modules.iot.pojo.entity.SceneLinkage;
import org.springblade.modules.iot.pojo.vo.SceneLinkageVO;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.wrapper.BaseEntityWrapper;

public class SceneLinkageWrapper extends BaseEntityWrapper<SceneLinkage, SceneLinkageVO> {

	public static SceneLinkageWrapper build() {
		return new SceneLinkageWrapper();
	}

	@Override
	public SceneLinkageVO entityVO(SceneLinkage entity) {
		SceneLinkageVO vo = new SceneLinkageVO();
		Func.copyProperties(entity, vo);
		return vo;
	}
}
