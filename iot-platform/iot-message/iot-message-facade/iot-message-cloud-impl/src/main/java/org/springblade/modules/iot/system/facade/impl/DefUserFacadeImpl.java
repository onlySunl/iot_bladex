package org.springblade.modules.iot.system.facade.impl;

import com.mqttsnet.basic.base.R;
import org.springblade.modules.iot.model.constant.EchoApi;
import org.springblade.modules.iot.system.api.DefUserApi;
import org.springblade.modules.iot.system.facade.DefUserFacade;
import org.springblade.modules.iot.system.vo.result.tenant.DefUserDetailsResultVO;
import org.springblade.modules.iot.system.vo.result.tenant.DefUserResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author tangyh
 * @since 2024/9/20 23:33
 */
@Service(EchoApi.DEF_USER_ID_CLASS)
@RequiredArgsConstructor
public class DefUserFacadeImpl implements DefUserFacade {
    @Autowired
    @Lazy  // 一定要延迟加载，否则thinglinks-gateway-server无法启动
    private DefUserApi defUserApi;

    @Override
    public R<List<Long>> findAllUserId() {
        return defUserApi.findAllUserId();
    }

    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return defUserApi.findByIds(ids);
    }

    @Override
    public R<List<DefUserDetailsResultVO>> getDefUserByIds(List<Long> ids) {
        return defUserApi.getDefUserByIds(ids);
    }

    @Override
    public R<List<DefUserResultVO>> queryIds(List<Long> ids) {
        return defUserApi.queryIds(ids);
    }
}

