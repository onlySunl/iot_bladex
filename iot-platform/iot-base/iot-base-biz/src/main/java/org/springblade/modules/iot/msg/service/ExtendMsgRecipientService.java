package org.springblade.modules.iot.msg.service;

import org.springblade.core.mvc.service.SuperService;
import org.springblade.modules.iot.msg.entity.ExtendMsgRecipient;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 消息接收人
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-10 11:41:17
 * @create [2022-07-10 11:41:17] [mqttsnet] 
 */
public interface ExtendMsgRecipientService extends SuperService<Long, ExtendMsgRecipient> {
    /**
     * 根据消息ID查询接收人
     *
     * @param id
     * @return
     */
    List<ExtendMsgRecipient> listByMsgId(Long id);
}


