package org.springblade.modules.iot.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.pojo.entity.DeviceSubscribe;
import org.springblade.modules.iot.pojo.vo.DeviceSubscribeVO;

public class DeviceSubscribeWrapper extends BaseEntityWrapper<DeviceSubscribe, DeviceSubscribeVO> {

	public static DeviceSubscribeWrapper build() {
		return new DeviceSubscribeWrapper();
	}

	@Override
	public DeviceSubscribeVO entityVO(DeviceSubscribe entity) {
		return Func.copyProperties(entity, DeviceSubscribeVO.class);
	}
}
