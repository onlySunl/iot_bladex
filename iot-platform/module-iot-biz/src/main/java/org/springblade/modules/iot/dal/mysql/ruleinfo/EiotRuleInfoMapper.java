

package org.springblade.modules.iot.dal.mysql.ruleinfo;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.mybatis.core.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.api.rule.dto.RuleInfoPageReqVO;
import org.springblade.modules.iot.entity.EiotRuleInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 规则引擎 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface EiotRuleInfoMapper extends BaseMapperX<EiotRuleInfoDO> {

    default PageResult<EiotRuleInfoDO> selectPage(RuleInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EiotRuleInfoDO>()
                .eqIfPresent(EiotRuleInfoDO::getTyp, reqVO.getTyp())
                .eqIfPresent(EiotRuleInfoDO::getState, reqVO.getState())
                .eqIfPresent(EiotRuleInfoDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(EiotRuleInfoDO::getId));
    }

}
