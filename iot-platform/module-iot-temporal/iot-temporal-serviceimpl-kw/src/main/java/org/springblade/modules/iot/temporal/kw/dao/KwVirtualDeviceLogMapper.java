
package org.springblade.modules.iot.temporal.kw.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.modules.iot.common.mapper.BaseMapperX;
import org.springblade.modules.iot.temporal.kw.model.KwVirtualDeviceLog;
import org.apache.ibatis.annotations.Mapper;

@DS("kwDataSource")
@Mapper
public interface KwVirtualDeviceLogMapper extends BaseMapperX<KwVirtualDeviceLog> {
}
