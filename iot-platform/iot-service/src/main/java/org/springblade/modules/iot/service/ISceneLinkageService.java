package org.springblade.modules.iot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.iot.pojo.entity.SceneLinkage;

public interface ISceneLinkageService extends IService<SceneLinkage> {
	boolean enable(Long id);
	boolean disable(Long id);
}
