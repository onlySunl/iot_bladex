

package org.springblade.modules.iot.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_group")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceGroup extends CustomBaseEntity {

  /** 分组ID，非自增 */

  /** 分组名称 */
  @TableField("group_name")
  private String groupName;

  /** 分组标识 */
  @TableField("group_code")
  private String groupCode;

  /** 群组描述 */
  @TableField("group_describe")
  private String groupDescribe;

  /** 父id */
  @TableField("parent_id")
  private Long parentId;

  /** 是否有子分组 */
  @TableField("has_child")
  private Integer hasChild;

  /** 分组级别 */
  @TableField("group_level")
  private Integer groupLevel;

  /** 激活设备数 */
  @TableField("relat_dev_count")
  private Integer relatDevCount;

  /** 关联设备树 */
  @TableField("active_dev_count")
  private Integer activeDevCount;

  /** 创建人 */
  @TableField("creator_id")
  private String creatorId;

  /** 实例编号 */
  @TableField("instance")
  private String instance;

  /** 标签 */
  @TableField("tag")
  private String tag;
}
