/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 本文件由 gitee.com/NexIoT 开发并拥有版权，未经授权严禁擅自商用、复制或传播。
 * @Author: gitee.com/NexIoT
 * @Email: wo8335224@gmail.com
 * @Wechat: outlookFil
 *
 *
 */

package org.springblade.modules.iot.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.pojo.entity.RuleModel;
import org.springblade.modules.iot.pojo.bo.RuleModelBO;
import org.springblade.modules.iot.pojo.vo.RuleModelVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * 规则模型Mapper @Author gitee.com/NexIoT
 *
 * @since 2023/1/13 14:31
 */
@Mapper
public interface RuleModelMapper extends BaseMapper<RuleModel> {

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
