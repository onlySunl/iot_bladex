package org.springblade.modules.iot.msg.manager;

import org.springblade.core.mvc.manager.SuperManager;
import org.springblade.modules.iot.msg.entity.ExtendMsgRecipient;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 消息接收人
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-10 11:41:17
 * @create [2022-07-10 11:41:17] [mqttsnet]
 */
public interface ExtendMsgRecipientManager extends SuperManager<ExtendMsgRecipient> {

    /**
     * 根据消息ID查询接收人
     *
     * @param id
     * @return
     */
    List<ExtendMsgRecipient> listByMsgId(Long id);
}


