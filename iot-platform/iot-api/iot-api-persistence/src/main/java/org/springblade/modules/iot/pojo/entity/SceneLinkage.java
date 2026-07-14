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

import cn.universal.common.annotation.Excel;
import cn.universal.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
/**
 * scene_linkage表 SceneLinkage @Author gitee.com/NexIoT
 *
 * @since 2023-03-01
 */
@TableName("scene_linkage")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SceneLinkage extends BaseEntity {

  /** 场景名称 */
  @Excel(name = "场景名称")
  @TableField("scene_name")
  private String sceneName;

  /** 触发条件 all.全部 one.任意一个 */
  @Excel(name = "触发条件 all.全部 one.任意一个")
  @TableField("touch")
  private String touch;

  /** 触发条件 */
  @Excel(name = "触发条件")
  @TableField("trigger_condition")
  private String triggerCondition;

  /** 执行动作 */
  @Excel(name = "执行动作")
  @TableField("exec_action")
  private String execAction;

  /** 沉默周期 */
  @Excel(name = "沉默周期")
  @TableField("sleep_cycle")
  private Integer sleepCycle;

  /** 0启用 1停用 */
  @Excel(name = "0启用 1停用")

  /** 设备id */
  @Excel(name = "设备序列号")
  @TableField("deviceId")
  private String devId;
}
