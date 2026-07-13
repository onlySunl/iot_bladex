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

@TableName("iot_device_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceGroup extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 分组ID，非自增 */
  @KeySql(genId = SQenGenId.class)
  private Long id;

  /** 分组名称 */
  @TableField(value = "group_name")
  @AutoColumn(comment = "分组名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String groupName;

  /** 分组标识 */
  @TableField(value = "group_code")
  @AutoColumn(comment = "分组标识", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String groupCode;

  /** 群组描述 */
  @TableField(value = "group_describe")
  @AutoColumn(comment = "群组描述", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String groupDescribe;

  /** 父id */
  @TableField(value = "parent_id")
  @AutoColumn(comment = "父id", defaultValueType = DefaultValueEnum.NULL)
  private Long parentId;

  /** 是否有子分组 */
  @TableField(value = "has_child")
  @AutoColumn(comment = "是否有子分组", defaultValueType = DefaultValueEnum.NULL)
  private Integer hasChild;

  /** 分组级别 */
  @TableField(value = "group_level")
  @AutoColumn(comment = "分组级别", defaultValueType = DefaultValueEnum.NULL)
  private Integer groupLevel;

  /** 激活设备数 */
  @TableField(value = "relat_dev_count")
  @AutoColumn(comment = "激活设备数", defaultValueType = DefaultValueEnum.NULL)
  private Integer relatDevCount;

  /** 关联设备树 */
  @TableField(value = "active_dev_count")
  @AutoColumn(comment = "关联设备树", defaultValueType = DefaultValueEnum.NULL)
  private Integer activeDevCount;

  /** 创建人 */
  @TableField(value = "creator_id")
  @AutoColumn(comment = "创建人", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String creatorId;

  /** 实例编号 */
  @TableField(value = "instance")
  @AutoColumn(comment = "实例编号", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String instance;

  /** 标签 */
  @TableField(value = "tag")
  @AutoColumn(comment = "标签", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String tag;
}
