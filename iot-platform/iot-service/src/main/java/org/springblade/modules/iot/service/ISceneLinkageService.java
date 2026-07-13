package org.springblade.modules.iot.service;

import org.springblade.core.mp.service.BladeService;
import org.springblade.modules.iot.pojo.entity.SceneLinkage;

public interface ISceneLinkageService extends BladeService<SceneLinkage> {
	boolean enable(Long id);
	boolean disable(Long id);
}
