package org.springblade.modules.iot.msg.api.fallback;

import org.springblade.basic.base.R;
import org.springblade.modules.iot.msg.api.MsgApi;
import org.springblade.modules.iot.msg.vo.update.ExtendMsgPublishVO;
import org.springblade.modules.iot.msg.vo.update.ExtendMsgSendVO;
import org.springframework.stereotype.Component;

/**
 * 熔断
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Component
public class MsgApiFallback implements MsgApi {
    @Override
    public R<Boolean> sendByTemplate(ExtendMsgSendVO data) {
        return R.timeout();
    }

     @Override
    public R<Boolean> publish(ExtendMsgPublishVO data) {
        return R.timeout();
    }
}
