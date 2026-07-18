package org.springblade.modules.iot.api.modbus;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.springblade.modules.iot.api.modbus.dto.ModbusThingModel;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springblade.modules.iot.common.utils.TenantUtils;
import org.springblade.modules.iot.entity.ModbusThingModelDO;
import org.springblade.modules.iot.dal.mysql.modbus.ModbusThingModelMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class ModbusThingModelApiImpl implements ModbusThingModelApi {

    @Resource
    private ModbusThingModelMapper modbusThingModelMapper;
    @Override
    public ModbusThingModel findByProductKey(String productKey) {
        ModbusThingModelDO model = TenantUtils.executeIgnoreResult(() -> modbusThingModelMapper.findByProductKey(productKey));
        if (model != null) {
            ModbusThingModel thingModel = BeanUtil.toBean(model, ModbusThingModel.class, CopyOptions.create().setIgnoreProperties("model"));
            thingModel.setModel(JsonUtils.parseObject(model.getModel(), ModbusThingModel.Model.class));
            return thingModel;
        }
        return null;
    }

    @Override
    public ModbusThingModel save(ModbusThingModel thingModel) {
        ModbusThingModelDO to = BeanUtil.copyProperties(thingModel, ModbusThingModelDO.class, "model");
        to.setModel(JsonUtils.toJsonString(thingModel.getModel()));
        modbusThingModelMapper.insertOrUpdate(to);
        thingModel.setId(to.getId());
        return thingModel;
    }
}
