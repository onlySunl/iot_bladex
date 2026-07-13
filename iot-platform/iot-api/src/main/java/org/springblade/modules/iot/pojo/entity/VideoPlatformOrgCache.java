/*
 */
package org.springblade.modules.iot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("video_platform_org_cache")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoPlatformOrgCache extends CustomBaseEntity {

  

  @TableField("instance_key")
  private String instanceKey;

  @TableField("org_id")
  private String orgId;

  @TableField("parent_org_id")
  private String parentOrgId;

  @TableField("org_name")
  private String orgName;

  /** 组织路径（可选） */
  @TableField("path")
  private String path;

  /** 创建者ID */
  @TableField("create_id")
  private String createId;

  /** 更新者ID */
  @TableField("update_id")
  private String updateId;
}
