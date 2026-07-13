package org.springblade.modules.iot.service.impl;

import org.springblade.core.mp.service.impl.BladeServiceImpl;
import org.springblade.core.mp.service.impl.BladeServiceImpl;
import org.springblade.modules.iot.mapper.RuleModelMapper;
import org.springblade.modules.iot.pojo.entity.RuleModel;
import org.springblade.modules.iot.service.IRuleModelService;
import org.springframework.stereotype.Service;

/**
 * IoT规则模型 服务实现类
 */
@Service
public class RuleModelServiceImpl extends BladeServiceImpl<RuleModelMapper, RuleModel> implements IRuleModelService {
}
