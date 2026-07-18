
package org.springblade.modules.iot.temporal.kw.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.temporal.kw.model.KwThingModelMessage;
import org.apache.ibatis.annotations.Mapper;

@DS("kwDataSource")
@Mapper
public interface KwThingModelMessageMapper extends BaseMapperX<KwThingModelMessage> {
}
