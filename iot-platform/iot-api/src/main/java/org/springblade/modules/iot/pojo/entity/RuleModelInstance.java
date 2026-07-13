/*
 *
 *
 *
 *
 */

package org.springblade.modules.iot.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("rule_model_instance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleModelInstance extends CustomBaseEntity {

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
