package org.springblade.modules.iot.device.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springblade.core.database.mybatis.BladeServiceImpl;
import org.springblade.core.tool.utils.StringUtils;
import org.springblade.modules.iot.device.entity.DeviceCommand;
import org.springblade.modules.iot.device.manager.DeviceCommandManager;
import org.springblade.modules.iot.device.mapper.DeviceCommandMapper;
import org.springblade.modules.iot.device.vo.query.DeviceCommandPageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 通用业务实现类
 * 设备命令下发及响应表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-10-20 17:27:25
 * @create [2023-10-20 17:27:25] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DeviceCommandManagerImpl extends BladeServiceImpl<DeviceCommandMapper, DeviceCommand> implements DeviceCommandManager {
    private final DeviceCommandMapper deviceCommandMapper;

    /**
     * Fetch a list of device command result VOs.
     *
     * @param query the query parameters
     * @return a list of DeviceCommandResultVOs
     */
    @Override
    public List<DeviceCommand> getDeviceCommandResultVOList(DeviceCommandPageQuery query) {
        QueryWrapper<DeviceCommand> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Objects.nonNull(query.getId()), DeviceCommand::getId, query.getId());
        queryWrapper.lambda().eq(StringUtils.isNotBlank(query.getDeviceIdentification()), DeviceCommand::getDeviceIdentification, query.getDeviceIdentification());
        queryWrapper.lambda().eq(query.getCommandType() != null, DeviceCommand::getCommandType, query.getCommandType());

        return deviceCommandMapper.selectList(queryWrapper);
    }
}


