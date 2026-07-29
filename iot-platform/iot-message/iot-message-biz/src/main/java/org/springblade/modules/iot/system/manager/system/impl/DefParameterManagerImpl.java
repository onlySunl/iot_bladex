package org.springblade.modules.iot.system.manager.system.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.mqttsnet.basic.base.manager.impl.SuperCacheManagerImpl;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.database.mybatis.conditions.query.LbQueryWrap;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;
import com.mqttsnet.basic.utils.CollHelper;
import org.springblade.modules.iot.common.cache.tenant.base.DictParameterKeyBuilder;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.system.entity.system.DefParameter;
import org.springblade.modules.iot.system.manager.system.DefParameterManager;
import org.springblade.modules.iot.system.mapper.system.DefParameterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 通用业务实现类
 * 参数配置
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-13
 * @create [2021-10-13] [mqttsnet] 
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefParameterManagerImpl extends SuperCacheManagerImpl<DefParameterMapper, DefParameter> implements DefParameterManager {
    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new DictParameterKeyBuilder();
    }

    @Override
    @DS(DsConstant.DEFAULTS)
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        return CollHelper.uniqueIndex(find(ids), DefParameter::getId, DefParameter::getName);
    }

    public List<DefParameter> find(Set<Serializable> ids) {
        // 强转， 防止数据库隐式转换，  若你的id 是string类型，请勿强转
        return super.listByIds(ids.stream().filter(Objects::nonNull).map(Convert::toLong).toList());
    }

    @Override
    @DS(DsConstant.DEFAULTS)
    public Map<String, String> findParamMapByKey(List<String> paramsKeys) {
        if (CollUtil.isEmpty(paramsKeys)) {
            return Collections.emptyMap();
        }
        LbQueryWrap<DefParameter> query = Wraps.<DefParameter>lbQ().in(DefParameter::getKey, paramsKeys).eq(DefParameter::getState, true);
        List<DefParameter> list = super.list(query);

        //key 是类型
        return CollHelper.uniqueIndex(list, DefParameter::getKey, DefParameter::getValue);
    }
}
