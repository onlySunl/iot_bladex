package org.springblade.modules.nvr.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.nvr.pojo.entity.Device;
import org.springblade.modules.nvr.pojo.vo.DeviceVO;

import java.util.Objects;

/**
 * IoT设备包装类
 */
public class DeviceWrapper extends BaseEntityWrapper<Device, DeviceVO> {

	public static DeviceWrapper build() {
		return new DeviceWrapper();
	}

	@Override
	public DeviceVO entityVO(Device device) {
		DeviceVO deviceVO = Objects.requireNonNull(BeanUtil.copyProperties(device, DeviceVO.class));
		return deviceVO;
	}
}
