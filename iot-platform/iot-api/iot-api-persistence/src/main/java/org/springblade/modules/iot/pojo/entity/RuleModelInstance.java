

package org.springblade.modules.iot.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
/**
 * 规则实例 @Author gitee.com/NexIoT
 *
 * @since 2023/1/13 14:28
 */
@TableName("rule_model_instance")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleModelInstance extends CustomBaseEntity {

  /** 主键ID */

  /** 模型id */
  @TableField("model_id")
  private Long modelId;

  /** 关联类型 */
  @TableField("relation_type")
  private String relationType;

  /** 关联id */
  @TableField("relation_id")
  private String relationId;
}
