

package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.modules.iot.common.annotation.Excel;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品分类对象 iot_product_sort @Author gitee.com/NexIoT
 *
 * @since 2025-12-29
 */
@TableName("iot_product_sort")
@Data
@EqualsAndHashCode(callSuper = true)
public class IoTProductSort extends CustomBaseEntity {

  /** id */

  /** 父id */
  @Excel(name = "父id")
  private String parentId;

  /** 是否有子节点 */
  @Excel(name = "是否有子节点")
  private Integer hasChild;

  /** 标识 */
  @Excel(name = "标识")
  private String identification;

  /** 分类名称 */
  @Excel(name = "分类名称")
  private String classifiedName;

  /** 说明 */
  @Excel(name = "说明")
  private String description;

  /** 子分类 */
  private List<IoTProductSort> children = new ArrayList<IoTProductSort>();

  public List<IoTProductSort> getChildren() {
    return children;
  }
}
