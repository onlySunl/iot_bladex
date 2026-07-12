package org.springblade.modules.iot.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.pojo.entity.DeviceShadow;
import org.springblade.modules.iot.pojo.vo.DeviceShadowVO;

public class DeviceShadowWrapper extends BaseEntityWrapper<DeviceShadow, DeviceShadowVO> {

	public static DeviceShadowWrapper build() {
		return new DeviceShadowWrapper();
	}

	@Override
	public DeviceShadowVO entityVO(DeviceShadow entity) {
		return Func.copyProperties(entity, DeviceShadowVO.class);
	}
}
