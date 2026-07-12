package org.springblade.modules.iot.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.modules.iot.pojo.entity.DeviceLog;
import org.springblade.modules.iot.pojo.vo.DeviceLogVO;
import org.springblade.core.tool.utils.Func;

public class DeviceLogWrapper extends BaseEntityWrapper<DeviceLog, DeviceLogVO> {

	public static DeviceLogWrapper build() {
		return new DeviceLogWrapper();
	}

	@Override
	public DeviceLogVO entityVO(DeviceLog entity) {
		return Func.copyProperties(entity, DeviceLogVO.class);
	}
}
