package org.springblade.modules.iot.msg.manager.impl;

import org.springblade.core.mvc.manager.impl.SuperManagerImpl;
import org.springblade.modules.iot.msg.entity.ExtendInterfaceLogging;
import org.springblade.modules.iot.msg.manager.ExtendInterfaceLoggingManager;
import org.springblade.modules.iot.msg.mapper.ExtendInterfaceLoggingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * 接口执行日志记录
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-09 23:58:59
 * @create [2022-07-09 23:58:59] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExtendInterfaceLoggingManagerImpl extends SuperManagerImpl<ExtendInterfaceLoggingMapper, ExtendInterfaceLogging> implements ExtendInterfaceLoggingManager {

}


