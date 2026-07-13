/*
 *
 *
 *
 *
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
@TableName("video_platform_instance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoPlatformInstance extends CustomBaseEntity {

  

  /** 平台类型：wvp/ics/icc */
  @TableField("platform_type")
  private String platformType;

  /** 实例唯一标识 */
  @TableField("instance_key")
  private String instanceKey;

  /** 实例名称 */
  @TableField("name")
  private String name;

  /** 平台API地址或域 */
  @TableField("endpoint")
  private String endpoint;

  /** 鉴权配置（JSON） */
  @TableField("auth")
  private String auth;

  /** 平台版本 */
  @TableField("version")
  private String version;

  /** 其他选项配置（JSON） */
  @TableField("options")
  private String options;

  /** WVP是否自动创建GB/级联产品 */
  @TableField("auto_create_products")
  private Integer autoCreateProducts;

  /** 是否启用 */
  @TableField("enabled")
  private Integer enabled;

  /** 创建者ID */
  @TableField("creator_id")
  private String creatorId;
}
