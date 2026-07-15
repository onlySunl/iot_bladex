

package org.springblade.modules.iot.persistence.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.IoTDeviceTags;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IoTDeviceTagsMapper extends BladeMapper<IoTDeviceTags> {

  /**
   * 根据分组id查询设备id集合
   *
   * @param groupId
   * @return
   */
  int selectDevIds(String groupId);

  int deleteByValueId(String groupId);

  IoTDeviceTags getOne(String iotId);

  List<String> selectDevGroupName(String iotId);
}
