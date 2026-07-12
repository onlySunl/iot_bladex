package org.springblade.modules.iot.wrapper;

import org.springblade.core.boot.ctrl.BladeWrapper;
import org.springblade.modules.iot.pojo.entity.Protocol;
import org.springblade.modules.iot.pojo.vo.ProtocolVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 协议定义 Wrapper
 *
 * @author blade-iot
 */
@Component
public class ProtocolWrapper extends BladeWrapper<ProtocolVO, Protocol> {

    public static ProtocolWrapper build() {
        return new ProtocolWrapper();
    }

    @Override
    protected void init(ProtocolVO vo, Protocol entity) {
        BeanUtils.copyProperties(entity, vo);
        vo.setStatusName(entity.getStatus() != null && entity.getStatus() == 1 ? "启用" : "禁用");
    }
}
