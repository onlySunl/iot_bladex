package org.springblade.modules.iot.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.pojo.entity.DeviceTags;
import org.springblade.modules.iot.pojo.vo.DeviceTagsVO;

public class DeviceTagsWrapper extends BaseEntityWrapper<DeviceTags, DeviceTagsVO> {

	public static DeviceTagsWrapper build() {
		return new DeviceTagsWrapper();
	}

	@Override
	public DeviceTagsVO entityVO(DeviceTags entity) {
		return Func.copyProperties(entity, DeviceTagsVO.class);
	}
}
