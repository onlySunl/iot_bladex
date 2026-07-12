package org.springblade.modules.iot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ProtocolServiceImpl extends ServiceImpl<ProtocolMapper, Protocol> implements IProtocolService {
}
