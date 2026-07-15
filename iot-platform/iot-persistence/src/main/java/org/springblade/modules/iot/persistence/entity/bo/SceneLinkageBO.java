

package org.springblade.modules.iot.persistence.entity.bo;

import org.springblade.common.entity.CustomBaseEntity;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * scene_linkage表 SceneLinkage @Author gitee.com/NexIoT
 *
 * @since 2023-03-01
 */
@Table(name = "scene_linkage")
@Data
@EqualsAndHashCode(callSuper = false)
public class SceneLinkageBO extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /** $column.columnComment */
  @Id private Long id;

  /** 场景名称 */
  @Column(name = "scene_name")
  private String sceneName;

  /** 触发条件 all.全部 one.任意一个 */
  @Column(name = "touch")
  private String touch;

  /** 触发条件 */
  @Column(name = "trigger_condition")
  private List<TriggerBO> triggerCondition;

  /** 执行动作 */
  @Column(name = "exec_action")
  private List<TriggerBO> execAction;

  /** 沉默周期 */
  @Column(name = "sleep_cycle")
  private Long sleepCycle;

  /** 0启用 1停用 */
  @Column(name = "status")
  private Integer status;

  /** 设备id */
  @Column(name = "dev_id")
  private String devId;
}
