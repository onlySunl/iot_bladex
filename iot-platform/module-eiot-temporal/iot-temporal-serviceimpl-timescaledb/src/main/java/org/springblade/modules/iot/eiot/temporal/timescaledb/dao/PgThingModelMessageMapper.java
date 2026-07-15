package org.springblade.modules.iot.temporal.timescaledb.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.modules.iot.temporal.timescaledb.model.PgThingModelMessage;
import org.springblade.modules.iot.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@DS("timescaledb")
@Mapper
public interface PgThingModelMessageMapper extends BaseMapperX<PgThingModelMessage> {
}
