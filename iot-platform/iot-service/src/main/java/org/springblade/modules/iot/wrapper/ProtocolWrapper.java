package org.springblade.modules.iot.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.pojo.entity.Protocol;
import org.springblade.modules.iot.pojo.vo.ProtocolVO;
import org.springframework.stereotype.Component;

/**
 * 协议定义 Wrapper
 *
 * @author blade-iot
 */
@Component
public class ProtocolWrapper extends BaseEntityWrapper< Protocol,ProtocolVO> {

    public static ProtocolWrapper build() {
        return new ProtocolWrapper();
    }


    @Override
    public ProtocolVO entityVO(Protocol entity) {
        ProtocolVO vo =  Func.copyProperties(entity, ProtocolVO.class);
        vo.setStatusName(entity.getStatus() != null && entity.getStatus() == 1 ? "启用" : "禁用");
        return  vo;
    }
}
