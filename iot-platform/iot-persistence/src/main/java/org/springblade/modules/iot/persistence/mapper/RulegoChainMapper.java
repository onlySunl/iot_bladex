

package org.springblade.modules.iot.persistence.mapper;

import org.springblade.modules.iot.pojo.entity.RulegoChain;
import org.springblade.modules.iot.pojo.bo.RulegoChainBO;
import org.springblade.modules.iot.pojo.vo.RulegoChainVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;

/**
 * rulego规则链Mapper接口
 *
 * @author gitee.com/NexIoT
 * @since 2025/01/15
 */
@Mapper
public interface RulegoChainMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<RulegoChain> {

  /**
   * 根据条件查询规则链列表
   *
   * @param bo 查询条件
   * @return 规则链列表
   */
  List<RulegoChainVO> selectRulegoChainListByBo(@Param("bo") RulegoChainBO bo);

  /**
   * 根据rulegoId查询规则链
   *
   * @param rulegoId rulego规则链ID
   * @return 规则链信息
   */
  RulegoChain selectByRulegoId(@Param("rulegoId") String rulegoId);

  /**
   * 根据创建人查询规则链列表
   *
   * @param creatorId 创建人ID
   * @return 规则链列表
   */
  List<RulegoChain> selectByCreatorId(@Param("creatorId") String creatorId);

  /**
   * 更新DSL内容
   *
   * @param rulegoId rulego规则链ID
   * @param dslContent DSL内容
   * @return 更新行数
   */
  int updateDslContent(@Param("rulegoId") String rulegoId, @Param("dslContent") String dslContent);
}
