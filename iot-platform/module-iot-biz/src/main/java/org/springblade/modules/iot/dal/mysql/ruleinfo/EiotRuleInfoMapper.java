package org.springblade.modules.iot.dal.mysql.ruleinfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.entity.EiotRuleInfoDO;
import org.springblade.modules.iot.api.rule.dto.RuleInfoPageReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 规则信息 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface EiotRuleInfoMapper extends BladeMapper<EiotRuleInfoDO> {

    IPage<EiotRuleInfoDO> selectPage(IPage<EiotRuleInfoDO> page, @Param("reqVO") RuleInfoPageReqVO reqVO);

}
