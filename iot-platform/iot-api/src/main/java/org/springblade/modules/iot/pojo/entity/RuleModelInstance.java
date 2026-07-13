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

import org.springblade.modules.iot.persistence.common.inteceptor.SQenGenId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 规则实例 @Author gitee.com/NexIoT
 *
 * @since 2023/1/13 14:28
 */
@TableName("rule_model_instance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleModelInstance extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 主键ID */
  @KeySql(genId = SQenGenId.class)
  private Long id;

  /** 模型id */
  @TableField(value = "model_id")
  @AutoColumn(comment = "模型id", defaultValueType = DefaultValueEnum.NULL)
  private Long modelId;

  /** 关联类型 */
  @TableField(value = "relation_type")
  @AutoColumn(comment = "关联类型", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String relationType;

  /** 关联id */
  @TableField(value = "relation_id")
  @AutoColumn(comment = "关联id", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String relationId;
}
