package org.springblade.modules.iot.msg.facade;


import org.springblade.modules.iot.msg.vo.update.ExtendMsgPublishVO;
import org.springblade.modules.iot.msg.vo.update.ExtendMsgSendVO;

/**
 * 消息接口
 *
 * @author zuihou
 * @since 2024年09月20日10:37:50
 *
 */
public interface MsgFacade {

    /**
     * 根据模板发送消息
     *
     * @param data 发送内容
     * @return
     */
    Boolean sendByTemplate(ExtendMsgSendVO data);

     /**
      * 发布站内信
      *
      * @param data 发送内容
      * @return
      */
    Boolean publish(ExtendMsgPublishVO data);
}
