
package org.springblade.modules.iot.temporal.kw.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.temporal.kw.model.KwTaskLog;
import org.apache.ibatis.annotations.Mapper;

@DS("kwDataSource")
@Mapper
public interface KwTaskLogMapper extends BaseMapperX<KwTaskLog> {
}
