package org.springblade.modules.iot.productproperty.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springblade.core.database.mybatis.BladeServiceImpl;
import org.springblade.common.database.mybatis.conditions.query.QueryWrap;
import org.springblade.modules.iot.productproperty.entity.ProductProperty;
import org.springblade.modules.iot.productproperty.manager.ProductPropertyManager;
import org.springblade.modules.iot.productproperty.mapper.ProductPropertyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 产品模型服务属性表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductPropertyManagerImpl extends BladeServiceImpl<ProductPropertyMapper, ProductProperty> implements ProductPropertyManager {

    private final ProductPropertyMapper productPropertyMapper;

    @Override
    public List<ProductProperty> findAllByServiceId(Long serviceId) {
        QueryWrap<ProductProperty> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(ObjectUtil.isNotNull(serviceId), ProductProperty::getServiceId, serviceId);
        return productPropertyMapper.selectList(queryWrap);
    }

    @Override
    public List<ProductProperty> checkCode(Long serviceId, String propertyCode) {
        QueryWrap<ProductProperty> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(null != serviceId, ProductProperty::getServiceId, serviceId);
        queryWrap.lambda().eq(CharSequenceUtil.isNotBlank(propertyCode), ProductProperty::getPropertyCode, propertyCode);
        return productPropertyMapper.selectList(queryWrap);
    }

    @Override
    public List<ProductProperty> findAllByServiceIds(List<Long> serviceIds) {
        QueryWrap<ProductProperty> queryWrap = new QueryWrap<>();
        queryWrap.lambda().in(CollectionUtil.isNotEmpty(serviceIds), ProductProperty::getServiceId, serviceIds);
        return productPropertyMapper.selectList(queryWrap);
    }
}


