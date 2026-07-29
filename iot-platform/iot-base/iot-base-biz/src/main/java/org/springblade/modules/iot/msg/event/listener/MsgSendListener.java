package org.springblade.modules.iot.msg.event.listener;

import org.springblade.modules.iot.msg.biz.MsgBiz;
import org.springblade.modules.iot.msg.event.MsgEventVO;
import org.springblade.modules.iot.msg.event.MsgSendEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 消息发送事件监听
 * 目的： 解耦
 *
 * @author mqttsnet
 * @date 2020年03月18日17:39:59
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MsgSendListener {
    private final MsgBiz msgBiz;

    @Async
    @TransactionalEventListener({MsgSendEvent.class})
    public void handleMsg(MsgSendEvent event) {
        MsgEventVO msgEventVO = (MsgEventVO) event.getSource();
        msgEventVO.write();
        msgBiz.execSend(msgEventVO.getMsgId());
    }

}
