

package org.springblade.modules.iot.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.pojo.entity.IoTDeviceGroup;
import org.springblade.modules.iot.persistence.entity.vo.IoTDeviceGroupVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备分组Mapper接口
 *
 * @since 2025-12-30
 */
@Mapper
public interface IoTDeviceGroupMapper extends BaseMapper<IoTDeviceGroup> {

  /**
   * 查询设备分组
   *
   * @param id 设备分组ID
   * @return 设备分组
   */
  IoTDeviceGroup selectDevGroupById(Long id);

  /**
   * 查询设备分组列表
   *
   * @param admin 操作者
   * @return
   */
  List<IoTDeviceGroupVO> selectDevGroupList(String admin);

  /**
   * 新增设备分组
   *
   * @param ioTDeviceGroup 设备分组
   * @return 结果
   */
  int insertDevGroup(IoTDeviceGroup ioTDeviceGroup);

  /**
   * 修改设备分组
   *
   * @param ioTDeviceGroup 设备分组
   * @return 结果
   */
  int updateDevGroup(IoTDeviceGroup ioTDeviceGroup);

  /**
   * 删除设备分组
   *
   * @param id 设备分组ID
   * @return 结果
   */
  int deleteDevGroupById(Long id);

  /**
   * 根据分组id获取子节点个数
   *
   * @param id 设备分组ID
   * @return 结果
   */
  int queryChildrenCountById(Long id);

  /**
   * 根据设备id查询设备是否绑定这个账号下，10个以上分组
   *
   * @param iotId
   * @return
   */
  int queryDevCountBindGroup(@Param("iotId") String iotId, @Param("admin") String admin);
}
