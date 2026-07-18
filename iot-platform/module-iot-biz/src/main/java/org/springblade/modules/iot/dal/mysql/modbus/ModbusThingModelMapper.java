package org.springblade.modules.iot.dal.mysql.modbus;

import org.apache.ibatis.annotations.Mapper;
import org.springblade.modules.iot.entity.ModbusThingModelDO;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;

@Mapper
public interface ModbusThingModelMapper extends BaseMapperX<ModbusThingModelDO> {
    default ModbusThingModelDO findByProductKey(String productKey) {


        return selectOne(ModbusThingModelDO::getProductKey, productKey);
    }
}
