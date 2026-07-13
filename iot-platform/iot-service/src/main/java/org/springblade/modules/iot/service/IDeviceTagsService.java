package org.springblade.modules.iot.service;

import org.springblade.core.mp.service.BladeService;
import org.springblade.modules.iot.pojo.entity.DeviceTags;
import java.util.List;

public interface IDeviceTagsService extends BladeService<DeviceTags> {
	List<DeviceTags> getByDeviceId(Long deviceId);
}
