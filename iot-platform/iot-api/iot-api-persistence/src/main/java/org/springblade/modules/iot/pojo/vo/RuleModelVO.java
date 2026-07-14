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

package org.springblade.modules.iot.pojo.vo;

import org.springblade.modules.iot.pojo.entity.RuleModel;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * 规则模型 @Author gitee.com/NexIoT
 *
 * @since 2023/1/13 14:28
 */
@Data
@Schema
@NoArgsConstructor
public class RuleModelVO extends RuleModel {

  @Schema(description = "分组id")
  private String groupId;

  @Schema(description = "关联设备id")
  private List<String> relationIds;

  public RuleModelVO(RuleModel ruleModel, List<RuleModelInstance> instances) {
    BeanUtils.copyProperties(ruleModel, this);

    if ("group".equals(this.dataLevel)) {
      this.groupId = instances.get(0).getRelationId();
    }

    if ("device".equals(this.dataLevel)) {
      this.relationIds =
          instances.stream().map(RuleModelInstance::getRelationId).collect(Collectors.toList());
    }
  }

  public JSONObject getConfig() {
    return JSONUtil.parseObj(config);
  }
}
