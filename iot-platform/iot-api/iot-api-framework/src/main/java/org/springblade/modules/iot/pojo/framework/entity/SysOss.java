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

package org.springblade.modules.iot.pojo.framework.entity;

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
import lombok.experimental.Accessors;

/** OSS云存储对象 @Author Lion Li */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@TableName("sys_oss")
public class SysOss extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 云存储主键 */
  //  @TableId(value = "oss_id", type = IdType.AUTO)
  @Id private Long ossId;

  /** 文件名 */
  @TableField(value = "file_name")
  @AutoColumn(comment = "文件名", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String fileName;

  /** 原名 */
  @TableField(value = "original_name")
  @AutoColumn(comment = "原名", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String originalName;

  /** 文件后缀名 */
  @TableField(value = "file_suffix")
  @AutoColumn(comment = "文件后缀名", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String fileSuffix;

  /** URL地址 */
  @TableField(value = "url")
  @ColumnType("text")
  @AutoColumn(comment = "URL地址", defaultValueType = DefaultValueEnum.NULL)
  private String url;

  /** 创建时间 */
  //  @TableField(fill = FieldFill.INSERT)
  @TableField(value = "create_time")
  @AutoColumn(comment = "创建时间", defaultValueType = DefaultValueEnum.NULL)
  private Date createTime;

  /** 上传人 */
  //  @TableField(fill = FieldFill.INSERT)
  @TableField(value = "create_by")
  @AutoColumn(comment = "上传人", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String createBy;

  /** 更新时间 */
  //  @TableField(fill = FieldFill.INSERT_UPDATE)
  @TableField(value = "update_time")
  @AutoColumn(comment = "更新时间", defaultValueType = DefaultValueEnum.NULL)
  private Date updateTime;

  /** 更新人 */
  //  @TableField(fill = FieldFill.INSERT_UPDATE)
  @TableField(value = "update_by")
  @AutoColumn(comment = "更新人", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String updateBy;

  /** 服务商 */
  @TableField(value = "service")
  @AutoColumn(comment = "服务商", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String service;
}
