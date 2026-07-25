package org.springblade.modules.iot.dal.mysql.modbus;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.entity.ModbusInfoDO;

/**
 * Modbus 信息 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface ModbusInfoMapper extends BladeMapper<ModbusInfoDO> {

    ModbusInfoDO findByProductKey(@Param("productKey") String productKey);
}
