

package org.springblade.modules.iot.persistence.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.RuleModel;
import org.springblade.modules.iot.persistence.entity.bo.RuleModelBO;
import org.springblade.modules.iot.persistence.entity.vo.RuleModelVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * 规则模型Mapper @Author gitee.com/NexIoT
 *
 * @since 2023/1/13 14:31
 */
@Mapper
public interface RuleModelMapper extends BladeMapper<RuleModel> {

  /**
   * 查询设备相关的规则模型
   *
   * @param ruleModelBo
   * @return
   */
  List<RuleModel> selectRuleByBo(@Param("bo") RuleModelBO ruleModelBo);

  /**
   * 查询规则模型
   *
   * @param ruleModelBo
   * @return
   */
  List<RuleModelVO> selectRuleListByBo(@Param("bo") RuleModelBO ruleModelBo);
}
