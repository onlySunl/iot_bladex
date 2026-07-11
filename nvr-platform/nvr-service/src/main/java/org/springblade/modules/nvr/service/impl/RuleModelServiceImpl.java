package org.springblade.modules.nvr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.nvr.mapper.RuleModelMapper;
import org.springblade.modules.nvr.pojo.entity.RuleModel;
import org.springblade.modules.nvr.service.IRuleModelService;
import org.springframework.stereotype.Service;

/**
 * IoT规则模型 服务实现类
 */
@Service
public class RuleModelServiceImpl extends ServiceImpl<RuleModelMapper, RuleModel> implements IRuleModelService {
}
