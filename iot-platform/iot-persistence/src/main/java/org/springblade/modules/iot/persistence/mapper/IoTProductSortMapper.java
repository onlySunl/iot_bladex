

package org.springblade.modules.iot.persistence.mapper;

import org.springblade.modules.iot.pojo.entity.IoTProductSort;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 产品分类Mapper接口 @Author gitee.com/NexIoT
 *
 * @since 2025-12-29
 */
@Mapper
public interface IoTProductSortMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<org.springblade.modules.iot.pojo.entity.IoTProductSort> {

  /**
   * 查询产品分类
   *
   * @param id 产品分类主键
   * @return 产品分类
   */
  public IoTProductSort selectDevProductSortById(String id);

  /**
   * 查询产品分类列表
   *
   * @param ioTProductSort 产品分类
   * @return 产品分类集合
   */
  public List<IoTProductSort> selectDevProductSortList(IoTProductSort ioTProductSort);

  /**
   * 新增产品分类
   *
   * @param ioTProductSort 产品分类
   * @return 结果
   */
  public int insertDevProductSort(IoTProductSort ioTProductSort);

  /**
   * 修改产品分类
   *
   * @param ioTProductSort 产品分类
   * @return 结果
   */
  public int updateDevProductSort(IoTProductSort ioTProductSort);

  /**
   * 删除产品分类
   *
   * @param id 产品分类主键
   * @return 结果
   */
  public int deleteDevProductSortById(String id);

  /**
   * 批量删除产品分类
   *
   * @param ids 需要删除的数据主键集合
   * @return 结果
   */
  public int deleteDevProductSortByIds(String[] ids);

  /**
   * 根据父id倒序查出列表
   *
   * @param parentId 需要删除的数据主键集合
   * @return 结果
   */
  public List<IoTProductSort> getListByParentId(String parentId);
}
