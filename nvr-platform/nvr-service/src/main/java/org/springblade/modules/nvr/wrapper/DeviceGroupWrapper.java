package org.springblade.modules.nvr.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.nvr.pojo.entity.DeviceGroup;
import org.springblade.modules.nvr.pojo.vo.DeviceGroupVO;

import java.util.Objects;

/**
 * IoT设备分组包装类
 */
public class DeviceGroupWrapper extends BaseEntityWrapper<DeviceGroup, DeviceGroupVO> {

	public static DeviceGroupWrapper build() {
		return new DeviceGroupWrapper();
	}

	@Override
	public DeviceGroupVO entityVO(DeviceGroup group) {
		DeviceGroupVO vo = Objects.requireNonNull(BeanUtil.copyProperties(group, DeviceGroupVO.class));
		return vo;
	}
}
