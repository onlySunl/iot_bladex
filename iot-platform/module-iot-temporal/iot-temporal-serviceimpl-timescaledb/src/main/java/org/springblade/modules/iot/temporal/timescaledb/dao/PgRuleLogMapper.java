package org.springblade.modules.iot.temporal.timescaledb.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.temporal.timescaledb.model.PgRuleLog;
import org.apache.ibatis.annotations.Mapper;

@DS("timescaledb")
@Mapper
public interface PgRuleLogMapper extends BaseMapperX<PgRuleLog> {
}
