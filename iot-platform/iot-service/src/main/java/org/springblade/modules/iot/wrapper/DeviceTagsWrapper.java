package org.springblade.modules.iot.wrapper;

import org.springblade.modules.iot.pojo.entity.DeviceTags;
import org.springblade.modules.iot.pojo.vo.DeviceTagsVO;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.wrapper.BaseEntityWrapper;

public class DeviceTagsWrapper extends BaseEntityWrapper<DeviceTags, DeviceTagsVO> {

	public static DeviceTagsWrapper build() {
		return new DeviceTagsWrapper();
	}

	@Override
	public DeviceTagsVO entityVO(DeviceTags entity) {
		DeviceTagsVO vo = new DeviceTagsVO();
		Func.copyProperties(entity, vo);
		return vo;
	}
}
