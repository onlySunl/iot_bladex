

package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.modules.iot.common.annotation.Excel;

/**
 * scene_linkage表 SceneLinkage @Author gitee.com/NexIoT
 *
 * @since 2023-03-01
 */
@TableName("scene_linkage")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SceneLinkage extends CustomBaseEntity {

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

  /** 设备id */
  @Excel(name = "设备序列号")
  @TableField("deviceId")
  private String devId;
}
