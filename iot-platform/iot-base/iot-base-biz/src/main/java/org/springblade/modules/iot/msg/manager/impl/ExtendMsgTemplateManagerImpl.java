package org.springblade.modules.iot.msg.manager.impl;

import org.springblade.core.database.mybatis.conditions.Wraps;
import org.springblade.core.mvc.manager.impl.SuperManagerImpl;
import org.springblade.modules.iot.msg.entity.ExtendMsgTemplate;
import org.springblade.modules.iot.msg.manager.ExtendMsgTemplateManager;
import org.springblade.modules.iot.msg.mapper.ExtendMsgTemplateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * 消息模板
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [mqttsnet] 
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExtendMsgTemplateManagerImpl extends SuperManagerImpl<ExtendMsgTemplateMapper, ExtendMsgTemplate> implements ExtendMsgTemplateManager {
    @Override
    public ExtendMsgTemplate getByCode(String code) {
        return getOne(Wraps.<ExtendMsgTemplate>lbQ().eq(ExtendMsgTemplate::getCode, code));
    }
}


