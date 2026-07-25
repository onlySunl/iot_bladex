package org.springblade.modules.iot.temporal.timescaledb.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.modules.iot.temporal.timescaledb.model.PgTaskLog;
import org.springblade.core.mp.mapper.BladeMapper;
import org.apache.ibatis.annotations.Mapper;

@DS("timescaledb")
@Mapper
public interface PgTaskLogMapper extends BladeMapper<PgTaskLog> {
}
