package org.springblade.modules.iot.wrapper;

import org.springblade.modules.iot.pojo.entity.DeviceSubscribe;
import org.springblade.modules.iot.pojo.vo.DeviceSubscribeVO;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.wrapper.BaseEntityWrapper;

public class DeviceSubscribeWrapper extends BaseEntityWrapper<DeviceSubscribe, DeviceSubscribeVO> {

	public static DeviceSubscribeWrapper build() {
		return new DeviceSubscribeWrapper();
	}

	@Override
	public DeviceSubscribeVO entityVO(DeviceSubscribe entity) {
		DeviceSubscribeVO vo = new DeviceSubscribeVO();
		Func.copyProperties(entity, vo);
		return vo;
	}
}
