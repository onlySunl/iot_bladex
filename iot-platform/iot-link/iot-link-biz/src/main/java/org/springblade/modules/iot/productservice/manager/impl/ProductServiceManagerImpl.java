package org.springblade.modules.iot.productservice.manager.impl;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springblade.basic.base.manager.impl.SuperManagerImpl;
import org.springblade.basic.database.mybatis.conditions.query.QueryWrap;
import org.springblade.modules.iot.productservice.entity.ProductServices;
import org.springblade.modules.iot.productservice.manager.ProductServiceManager;
import org.springblade.modules.iot.productservice.mapper.ProductServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 产品模型服务表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceManagerImpl extends SuperManagerImpl<ProductServiceMapper, ProductServices> implements ProductServiceManager {

    private final ProductServiceMapper serviceMapper;

    @Override
    public ProductServices findOneByProductServiceId(Long serviceId) {
        return serviceMapper.selectById(serviceId);
    }

    @Override
    public List<ProductServices> selectProductServicesList(ProductServices find) {
        QueryWrap<ProductServices> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(ObjectUtil.isNotNull(find.getProductId()), ProductServices::getProductId, find.getProductId());
        queryWrap.lambda().eq(ObjectUtil.isNotNull(find.getServiceStatus()), ProductServices::getServiceStatus, find.getServiceStatus());
        return serviceMapper.selectList(queryWrap);
    }

    @Override
    public List<ProductServices> checkCode(Long productId, String serviceCode) {
        QueryWrap<ProductServices> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(productId != null, ProductServices::getProductId, productId);
        queryWrap.lambda().eq(CharSequenceUtil.isNotBlank(serviceCode), ProductServices::getServiceCode, serviceCode);
        return serviceMapper.selectList(queryWrap);
    }
}


