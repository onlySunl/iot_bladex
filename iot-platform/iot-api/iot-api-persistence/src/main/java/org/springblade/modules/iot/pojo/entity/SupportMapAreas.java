

package org.springblade.modules.iot.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("support_map_areas")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupportMapAreas extends CustomBaseEntity {

  /** 父id */
  private String pid;

  /** 地址 */
  private String name;

  /** 详细地址 */
  private String fullName;

  /** 深度 */
  private String deep;

  /** 核心点坐标 */
  private String location;

  /** 范围坐标 */
  private String polygon;
}
