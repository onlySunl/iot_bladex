package org.springblade.modules.iot.qs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.iot.domain.QsDevice;
import org.springblade.modules.iot.qs.mapper.QsDeviceMapper;
import org.springblade.modules.iot.qs.service.QsDeviceService;
import org.springframework.stereotype.Service;

/**
 * QS设备Service实现
 *
 * @author BladeX
 */
@Service
public class QsDeviceServiceImpl extends ServiceImpl<QsDeviceMapper, QsDevice> implements QsDeviceService {

}
