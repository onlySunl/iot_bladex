

package org.springblade.modules.iot.pojo.vo;

import org.springblade.modules.iot.pojo.entity.IoTProductSort;

import org.springblade.modules.iot.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 产品分类对象 iot_product_sort @Author gitee.com/NexIoT
 *
 * @since 2025-12-29
 */
public class IoTProductSortVO extends IoTProductSort implements Serializable {






  /** 分类名称 */
  @Excel(name = "分类名称")
  private String label;




  /** 子分类 */
  private List<IoTProductSortVO> children = new ArrayList<IoTProductSortVO>();

  public List<IoTProductSortVO> getChildren() {
    return children;
  }

  public void setChildren(List<IoTProductSortVO> children) {
    this.children = children;
  }

  public static long getSerialVersionUID() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public Integer getHasChild() {
    return hasChild;
  }

  public void setHasChild(Integer hasChild) {
    this.hasChild = hasChild;
  }

  public String getIdentification() {
    return identification;
  }

  public void setIdentification(String identification) {
    this.identification = identification;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }
}
