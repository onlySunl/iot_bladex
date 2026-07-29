package org.springblade.modules.iot.msg.manager.impl;

import com.mqttsnet.basic.base.manager.impl.SuperManagerImpl;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import org.springblade.modules.iot.msg.entity.DefMsgTemplate;
import org.springblade.modules.iot.msg.manager.DefMsgTemplateManager;
import org.springblade.modules.iot.msg.mapper.DefMsgTemplateMapper;
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
public class DefMsgTemplateManagerImpl extends SuperManagerImpl<DefMsgTemplateMapper, DefMsgTemplate> implements DefMsgTemplateManager {
    @Override
    public DefMsgTemplate getByCode(String code) {
        return getOne(Wraps.<DefMsgTemplate>lbQ().eq(DefMsgTemplate::getCode, code));
    }
}


