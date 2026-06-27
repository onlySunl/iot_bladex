package org.springblade.modules.iot.protocol.qs.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.iot.domain.QsDevice;
import org.springblade.modules.iot.protocol.qs.mapper.QsDeviceMapper;
import org.springframework.stereotype.Service;

/**
 * QS设备Service
 *
 * @author BladeX
 */
@Service
public class QsDeviceService extends ServiceImpl<QsDeviceMapper, QsDevice> implements IService<QsDevice> {

    /**
     * 根据设备编码查询
     */
    public QsDevice getByDeviceCode(String deviceCode) {
        return getOne(new LambdaQueryWrapper<QsDevice>()
            .eq(QsDevice::getDeviceCode, deviceCode));
    }

    /**
     * 根据设备名称查询
     */
    public QsDevice getByDeviceName(String deviceName) {
        return getOne(new LambdaQueryWrapper<QsDevice>()
            .like(QsDevice::getDeviceName, deviceName));
    }

    /**
     * 分页查询设备列表
     */
    public IPage<QsDevice> pageDevices(Page<QsDevice> page, QsDevice device) {
        LambdaQueryWrapper<QsDevice> wrapper = new LambdaQueryWrapper<>();
        if (device.getDeviceName() != null) {
            wrapper.like(QsDevice::getDeviceName, device.getDeviceName());
        }
        if (device.getDeviceCode() != null) {
            wrapper.eq(QsDevice::getDeviceCode, device.getDeviceCode());
        }
        if (device.getDeviceType() != null) {
            wrapper.eq(QsDevice::getDeviceType, device.getDeviceType());
        }
        if (device.getRegionId() != null) {
            wrapper.eq(QsDevice::getRegionId, device.getRegionId());
        }
        wrapper.orderByDesc(QsDevice::getCreateTime);
        return page(page, wrapper);
    }

}
