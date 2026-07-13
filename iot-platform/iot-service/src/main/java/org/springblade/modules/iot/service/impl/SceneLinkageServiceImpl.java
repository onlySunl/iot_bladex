package org.springblade.modules.iot.service.impl;

import org.springblade.core.mp.service.impl.BladeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.mapper.SceneLinkageMapper;
import org.springblade.modules.iot.pojo.entity.SceneLinkage;
import org.springblade.modules.iot.service.ISceneLinkageService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SceneLinkageServiceImpl extends BladeServiceImpl<SceneLinkageMapper, SceneLinkage> implements ISceneLinkageService {

	@Override
	public boolean enable(Long id) {
		SceneLinkage linkage = getById(id);
		if (linkage == null) {
			return false;
		linkage.setEnabled(1);
		return updateById(linkage);
	}

	@Override
	public boolean disable(Long id) {
		SceneLinkage linkage = getById(id);
		if (linkage == null) {
			return false;
		linkage.setEnabled(0);
		return updateById(linkage);
	}
}
