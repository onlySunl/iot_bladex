package org.springblade.modules.iot.qs.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.iot.domain.QsDevice;
import org.springblade.modules.iot.qs.mapper.QsDeviceMapper;

/**
 * QS设备Service
 *
 * @author BladeX
 */
public interface QsDeviceService extends IService<QsDevice> {

}
