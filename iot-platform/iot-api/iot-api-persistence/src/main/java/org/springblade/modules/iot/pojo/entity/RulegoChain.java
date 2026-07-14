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

package org.springblade.modules.iot.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
/**
 * rulego规则链管理实体
 *
 * @author gitee.com/NexIoT
 * @since 2025/01/15
 */
@TableName("rulego_chain")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RulegoChain extends CustomBaseEntity {

  /** 主键ID */

  /** rulego规则链ID */
  @Schema(description = "rulego规则链ID")
  @TableField("rulego_id")
  private String rulegoId;

  /** 规则链名称 */
  @Schema(description = "规则链名称")
  @TableField("chain_name")
  private String chainName;

  /** 规则链描述 */
  @Schema(description = "规则链描述")
  @TableField("description")
  private String description;

  /** 创建人unionId */
  @Schema(description = "创建人unionId")
  @TableField("creator_id")
  private String creatorId;

  /** 创建人姓名 */
  @Schema(description = "创建人姓名")
  @TableField("creator_name")
  private String creatorName;

  /** 状态：draft-草稿，deployed-已部署，stopped-已停止 */
  @Schema(description = "状态")

  /** 规则链DSL内容(JSON格式) */
  @Schema(description = "规则链DSL内容")
  @TableField("dsl_content")
  private String dslContent;

  /** 最后同步时间 */
  @Schema(description = "最后同步时间")
  @TableField("last_sync_time")
  private Date lastSyncTime;

  /** 创建时间 */
  @Schema(description = "创建时间")

  /** 更新时间 */
  @Schema(description = "更新时间")

  /** 是否删除：0-未删除，1-已删除 */
  @Schema(description = "是否删除")
  @TableField("deleted")
  private Integer deleted;
}
