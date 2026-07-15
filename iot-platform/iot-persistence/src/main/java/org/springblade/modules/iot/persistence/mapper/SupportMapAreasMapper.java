

package org.springblade.modules.iot.persistence.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.SupportMapAreas;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SupportMapAreasMapper extends BladeMapper<SupportMapAreas> {

  /**
   * 根据经纬度查询区域id
   *
   * @param lon 经度
   * @param lat 维度
   * @return
   */
  SupportMapAreas selectMapAreas(@Param("lon") String lon, @Param("lat") String lat);
}
