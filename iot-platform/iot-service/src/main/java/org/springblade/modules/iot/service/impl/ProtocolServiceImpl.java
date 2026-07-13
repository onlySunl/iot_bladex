package org.springblade.modules.iot.service.impl;

import org.springblade.core.mp.service.impl.BladeServiceImpl;
import org.springblade.modules.iot.mapper.ProtocolMapper;
import org.springblade.modules.iot.pojo.entity.Protocol;
import org.springblade.modules.iot.service.IProtocolService;
import org.springframework.stereotype.Service;

/**
 * 协议定义 Service 实现
 *
 * @author blade-iot
 */
@Service
public class ProtocolServiceImpl extends BladeServiceImpl<ProtocolMapper, Protocol> implements IProtocolService {
}
