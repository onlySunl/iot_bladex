package org.springblade.modules.iot.msg.manager.impl;

import cn.hutool.core.map.MapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.database.mybatis.conditions.Wraps;
import org.springblade.core.mvc.manager.impl.SuperManagerImpl;
import org.springblade.modules.iot.msg.entity.DefInterfaceProperty;
import org.springblade.modules.iot.msg.manager.DefInterfacePropertyManager;
import org.springblade.modules.iot.msg.mapper.DefInterfacePropertyMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通用业务实现类
 * 接口属性
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [mqttsnet] 
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DefInterfacePropertyManagerImpl extends SuperManagerImpl<DefInterfacePropertyMapper, DefInterfaceProperty> implements DefInterfacePropertyManager {
    @Override
    public Map<String, Object> listByInterfaceId(Long id) {
        List<DefInterfaceProperty> propertyList = list(Wraps.<DefInterfaceProperty>lbQ().eq(DefInterfaceProperty::getInterfaceId, id));
        Map<String, Object> param = MapUtil.newHashMap();
        propertyList.forEach(item -> param.put(item.getKey(), item.getValue()));
        return param;
    }
}


