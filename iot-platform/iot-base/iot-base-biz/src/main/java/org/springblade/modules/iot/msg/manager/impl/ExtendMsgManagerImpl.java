package org.springblade.modules.iot.msg.manager.impl;

import org.springblade.basic.base.manager.impl.SuperManagerImpl;
import org.springblade.modules.iot.msg.entity.ExtendMsg;
import org.springblade.modules.iot.msg.manager.ExtendMsgManager;
import org.springblade.modules.iot.msg.mapper.ExtendMsgMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * 消息
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-10 11:41:17
 * @create [2022-07-10 11:41:17] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExtendMsgManagerImpl extends SuperManagerImpl<ExtendMsgMapper, ExtendMsg> implements ExtendMsgManager {

}


