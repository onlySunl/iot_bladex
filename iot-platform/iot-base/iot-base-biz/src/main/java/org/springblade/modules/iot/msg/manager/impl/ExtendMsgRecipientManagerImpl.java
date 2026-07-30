package org.springblade.modules.iot.msg.manager.impl;

import org.springblade.basic.base.manager.impl.SuperManagerImpl;
import org.springblade.basic.database.mybatis.conditions.Wraps;
import org.springblade.modules.iot.msg.entity.ExtendMsgRecipient;
import org.springblade.modules.iot.msg.manager.ExtendMsgRecipientManager;
import org.springblade.modules.iot.msg.mapper.ExtendMsgRecipientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 消息接收人
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-10 11:41:17
 * @create [2022-07-10 11:41:17] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExtendMsgRecipientManagerImpl extends SuperManagerImpl<ExtendMsgRecipientMapper, ExtendMsgRecipient> implements ExtendMsgRecipientManager {
    @Override
    public List<ExtendMsgRecipient> listByMsgId(Long id) {
        return list(Wraps.<ExtendMsgRecipient>lbQ().eq(ExtendMsgRecipient::getMsgId, id));
    }
}


