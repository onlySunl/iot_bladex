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

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rulego规则链VO
 *
 * @author gitee.com/NexIoT
 * @since 2025/01/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema
public class RulegoChainVO extends RulegoChain {

  @Schema(description = "主键ID")
  private Long id;

  @Schema(description = "状态")
  private String status;

  @Schema(description = "状态描述")
  private String statusDesc;

  @Schema(description = "创建时间")
  private String createTime;

  @Schema(description = "更新时间")
  private String updateTime;

  @Schema(description = "设计器URL")
  private String designerUrl;
}
