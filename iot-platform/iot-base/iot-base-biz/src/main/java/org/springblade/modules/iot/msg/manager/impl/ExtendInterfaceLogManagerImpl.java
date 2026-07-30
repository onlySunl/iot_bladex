package org.springblade.modules.iot.msg.manager.impl;

import org.springblade.basic.base.manager.impl.SuperManagerImpl;
import org.springblade.basic.database.mybatis.conditions.Wraps;
import org.springblade.modules.iot.msg.entity.ExtendInterfaceLog;
import org.springblade.modules.iot.msg.manager.ExtendInterfaceLogManager;
import org.springblade.modules.iot.msg.mapper.ExtendInterfaceLogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 通用业务实现类
 * 接口执行日志
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-09 23:58:59
 * @create [2022-07-09 23:58:59] [mqttsnet] 
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExtendInterfaceLogManagerImpl extends SuperManagerImpl<ExtendInterfaceLogMapper, ExtendInterfaceLog> implements ExtendInterfaceLogManager {
    @Override
    public ExtendInterfaceLog getByInterfaceId(Long interfaceId) {
        return getOne(Wraps.<ExtendInterfaceLog>lbQ().eq(ExtendInterfaceLog::getInterfaceId, interfaceId));
    }

    @Override
    public void incrSuccessCount(Long id) {
        baseMapper.incrSuccessCount(id, LocalDateTime.now());
    }

    @Override
    public void incrFailCount(Long id) {
        baseMapper.incrFailCount(id, LocalDateTime.now());
    }
}


