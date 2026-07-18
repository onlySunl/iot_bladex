package org.springblade.modules.iot.dal.mysql.modbus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.modules.iot.entity.ComponentDO;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.entity.ModbusThingModelDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ModbusThingModelMapper extends BaseMapperX<ModbusThingModelDO> {
    default ModbusThingModelDO findByProductKey(String productKey) {


        return selectOne(ModbusThingModelDO::getProductKey, productKey);
    }
}
