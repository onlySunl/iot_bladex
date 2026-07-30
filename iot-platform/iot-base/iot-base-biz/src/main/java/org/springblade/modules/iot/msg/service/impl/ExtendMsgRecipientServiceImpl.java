package org.springblade.modules.iot.msg.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.common.iot.constant.DsConstant;
import org.springblade.core.mvc.service.impl.SuperServiceImpl;
import org.springblade.modules.iot.msg.entity.ExtendMsgRecipient;
import org.springblade.modules.iot.msg.manager.ExtendMsgRecipientManager;
import org.springblade.modules.iot.msg.service.ExtendMsgRecipientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 消息接收人
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-10 11:41:17
 * @create [2022-07-10 11:41:17] [mqttsnet] 
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ExtendMsgRecipientServiceImpl extends SuperServiceImpl<ExtendMsgRecipientManager, Long, ExtendMsgRecipient> implements ExtendMsgRecipientService {
    @Override
    public List<ExtendMsgRecipient> listByMsgId(Long id) {
        return superManager.listByMsgId(id);
    }

}


