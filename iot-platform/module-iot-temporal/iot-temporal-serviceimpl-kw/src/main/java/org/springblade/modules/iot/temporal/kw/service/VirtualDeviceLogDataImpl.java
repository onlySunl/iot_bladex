
package org.springblade.modules.iot.temporal.kw.service;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.IVirtualDeviceLogData;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.temporal.kw.dao.KwVirtualDeviceLogMapper;
import org.springblade.modules.iot.temporal.kw.model.KwVirtualDeviceLog;

import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDeviceLog;
import com.kaiwudb.util.KWTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class VirtualDeviceLogDataImpl implements IVirtualDeviceLogData {

    @Autowired
    private KwVirtualDeviceLogMapper virtualDeviceLogMapper;

    @Override
    public PageResult<VirtualDeviceLog> findByVirtualDeviceId(Long virtualDeviceId, int page, int size) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(page);
        pageParam.setPageSize(size);
        PageResult<KwVirtualDeviceLog> result = virtualDeviceLogMapper.selectPage(pageParam,
                Wrappers.lambdaQuery(KwVirtualDeviceLog.class)
                        .eq(KwVirtualDeviceLog::getVirtualDeviceId, virtualDeviceId)
                        .orderByDesc(KwVirtualDeviceLog::getTime)
        );

        return new PageResult<>(result.getList().stream().map(r ->
                        new VirtualDeviceLog(r.getTime().getTime(), virtualDeviceId,
                                r.getVirtualDeviceName(),
                                r.getDeviceTotal(), r.getResult(), r.getTime().getTime()))
                .collect(Collectors.toList()), result.getTotal());
    }

    @Override
    public void add(VirtualDeviceLog log) {
        KwVirtualDeviceLog deviceLog = BeanUtil.copy(log, KwVirtualDeviceLog.class);
        deviceLog.setTime(new KWTimestamp(System.currentTimeMillis()));
        virtualDeviceLogMapper.insert(deviceLog);
    }
}
