package org.springblade.modules.iot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.iot.pojo.entity.DeviceTags;
import java.util.List;

public interface IDeviceTagsService extends IService<DeviceTags> {
	List<DeviceTags> getByDeviceId(Long deviceId);
}
