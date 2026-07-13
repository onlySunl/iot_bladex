/*
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 */
package org.springblade.modules.iot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("video_platform_org_cache")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoPlatformOrgCache extends CustomBaseEntity {
  private static final long serialVersionUID = 1L;


  @TableField(value = "instance_key")
  @AutoColumn(comment = "instanceKey", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String instanceKey;

  @TableField(value = "org_id")
  @AutoColumn(comment = "orgId", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String orgId;

  @TableField(value = "parent_org_id")
  @AutoColumn(comment = "parentOrgId", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String parentOrgId;

  @TableField(value = "org_name")
  @AutoColumn(comment = "orgName", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String orgName;

  /** 组织路径（可选） */
  @TableField(value = "path")
  @AutoColumn(comment = "组织路径（可选）", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String path;

  /** 创建者ID */
  @TableField(value = "create_id")
  @AutoColumn(comment = "创建者ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String createId;

  /** 更新者ID */
  @TableField(value = "update_id")
  @AutoColumn(comment = "更新者ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String updateId;

  @TableField(value = "create_time")
  @AutoColumn(comment = "更新者ID", defaultValueType = DefaultValueEnum.NULL)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

  @TableField(value = "update_time")
  @AutoColumn(comment = "updateTime", defaultValueType = DefaultValueEnum.NULL)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
}
