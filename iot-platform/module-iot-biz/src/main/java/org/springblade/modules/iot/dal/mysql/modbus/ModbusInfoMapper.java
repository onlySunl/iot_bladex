package org.springblade.modules.iot.dal.mysql.modbus;

import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.entity.ModbusInfoDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ModbusInfoMapper extends BaseMapperX<ModbusInfoDO> {

    default ModbusInfoDO findByProductKey(String productKey) {
        return selectOne(ModbusInfoDO::getProductKey, productKey);
    }
}
