package org.springblade.modules.iot.wrapper;

import org.springblade.modules.iot.pojo.entity.DeviceShadow;
import org.springblade.modules.iot.pojo.vo.DeviceShadowVO;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.wrapper.BaseEntityWrapper;

public class DeviceShadowWrapper extends BaseEntityWrapper<DeviceShadow, DeviceShadowVO> {

	public static DeviceShadowWrapper build() {
		return new DeviceShadowWrapper();
	}

	@Override
	public DeviceShadowVO entityVO(DeviceShadow entity) {
		DeviceShadowVO vo = new DeviceShadowVO();
		Func.copyProperties(entity, vo);
		return vo;
	}
}
