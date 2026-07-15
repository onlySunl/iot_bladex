package org.springblade.modules.iot.dal.mysql.modbus;

import org.springblade.modules.iot.framework.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.dal.dataobject.modbus.ModbusThingModelDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ModbusThingModelMapper extends BaseMapperX<ModbusThingModelDO> {
    default ModbusThingModelDO findByProductKey(String productKey) {
        return selectOne(ModbusThingModelDO::getProductKey, productKey);
    }
}
