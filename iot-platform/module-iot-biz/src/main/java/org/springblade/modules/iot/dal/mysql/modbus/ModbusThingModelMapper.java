package org.springblade.modules.iot.dal.mysql.modbus;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.entity.ModbusThingModelDO;

/**
 * Modbus 物模型 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface ModbusThingModelMapper extends BladeMapper<ModbusThingModelDO> {

    ModbusThingModelDO findByProductKey(@Param("productKey") String productKey);
}
