package org.springblade.modules.iot.msg.facade.impl;
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
