package org.springblade.modules.iot.protocol.qs.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.iot.domain.QsDeviceAlarm;
import org.springblade.modules.iot.protocol.qs.mapper.QsDeviceAlarmMapper;
import org.springframework.stereotype.Service;

/**
 * QS设备告警Service
 *
 * @author BladeX
 */
@Service
public class QsDeviceAlarmService extends ServiceImpl<QsDeviceAlarmMapper, QsDeviceAlarm>
    implements IService<QsDeviceAlarm> {

    /**
     * 根据设备ID查询告警列表
     */
    public IPage<QsDeviceAlarm> pageByDeviceId(Page<QsDeviceAlarm> page, Long deviceId) {
        LambdaQueryWrapper<QsDeviceAlarm> wrapper = new LambdaQueryWrapper<>();
        if (deviceId != null) {
            wrapper.eq(QsDeviceAlarm::getDeviceId, deviceId);
        }
        wrapper.orderByDesc(QsDeviceAlarm::getAlarmTime);
        return page(page, wrapper);
    }

}
