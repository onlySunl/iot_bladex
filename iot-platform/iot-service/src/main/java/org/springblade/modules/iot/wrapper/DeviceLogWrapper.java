package org.springblade.modules.iot.wrapper;

import org.springblade.modules.iot.pojo.entity.DeviceLog;
import org.springblade.modules.iot.pojo.vo.DeviceLogVO;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.wrapper.BaseEntityWrapper;

public class DeviceLogWrapper extends BaseEntityWrapper<DeviceLog, DeviceLogVO> {

	public static DeviceLogWrapper build() {
		return new DeviceLogWrapper();
	}

	@Override
	public DeviceLogVO entityVO(DeviceLog entity) {
		DeviceLogVO vo = new DeviceLogVO();
		Func.copyProperties(entity, vo);
		return vo;
	}
}
