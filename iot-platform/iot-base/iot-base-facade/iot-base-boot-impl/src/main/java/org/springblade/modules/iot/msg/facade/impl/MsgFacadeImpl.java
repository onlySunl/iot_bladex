package org.springblade.modules.iot.msg.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springblade.modules.iot.msg.biz.MsgBiz;
import org.springblade.modules.iot.msg.facade.MsgFacade;
import org.springblade.modules.iot.msg.vo.update.ExtendMsgPublishVO;
import org.springblade.modules.iot.msg.vo.update.ExtendMsgSendVO;
import org.springframework.stereotype.Service;

/**
 * 消息接口
 *
 * @author zuihou
 * @since 2024年09月20日10:37:50
 *
 */
@Service
@RequiredArgsConstructor
public class MsgFacadeImpl implements MsgFacade {
    private final MsgBiz msgBiz;

    /**
     * 根据模板发送消息
     *
     * @param data 发送内容
     * @return
     */
    @Override
    public Boolean sendByTemplate(ExtendMsgSendVO data) {
        return msgBiz.sendByTemplate(data, null);
    }

     /**
      * 发布站内信
      *
      * @param data 发送内容
      * @return
      */
    @Override
    public Boolean publish(ExtendMsgPublishVO data) {
        return msgBiz.publish(data, null);
    }
}
