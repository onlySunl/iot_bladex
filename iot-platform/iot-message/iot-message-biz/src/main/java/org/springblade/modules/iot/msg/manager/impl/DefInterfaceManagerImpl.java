package org.springblade.modules.iot.msg.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.database.mybatis.conditions.Wraps;
import org.springblade.core.mvc.manager.impl.SuperManagerImpl;
import org.springblade.modules.iot.msg.entity.DefInterface;
import org.springblade.modules.iot.msg.manager.DefInterfaceManager;
import org.springblade.modules.iot.msg.mapper.DefInterfaceMapper;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 通用业务实现类
 * 接口
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 16:45:45
 * @create [2022-07-04 16:45:45] [mqttsnet] 
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DefInterfaceManagerImpl extends SuperManagerImpl<DefInterfaceMapper, DefInterface> implements DefInterfaceManager {
    @Override
    public DefInterface getByType(String type) {
        return getOne(Wraps.<DefInterface>lbQ().eq(DefInterface::getCode, type));
    }
}


