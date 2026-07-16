package org.springblade.modules.iot.api.modbus;

import org.springblade.modules.iot.framework.tenant.core.util.TenantUtils;
import org.springblade.modules.iot.api.modbus.dto.ModbusInfo;
import org.springblade.modules.iot.convert.ModbusInfoConvert;
import org.springblade.modules.iot.entity.ModbusInfoDO;
import org.springblade.modules.iot.dal.mysql.modbus.ModbusInfoMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;


@Service
public class ModbusInfoApiImpl implements ModbusInfoApi {
    @Resource
    private ModbusInfoMapper modbusInfoMapper;
    @Override
    public ModbusInfo findByProductKey(String productKey) {
        ModbusInfoDO t = TenantUtils.executeIgnoreResult(() -> modbusInfoMapper.findByProductKey(productKey));
        return ModbusInfoConvert.INSTANCE.convert(t);
    }
}
