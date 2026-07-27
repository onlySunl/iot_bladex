package org.springblade.modules.iot.productcommand.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.text.CharSequenceUtil;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.common.database.mybatis.conditions.query.QueryWrap;
import org.springblade.modules.iot.productcommand.entity.ProductCommand;
import org.springblade.modules.iot.productcommand.manager.ProductCommandManager;
import org.springblade.modules.iot.productcommand.mapper.ProductCommandMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 产品模型设备服务命令表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductCommandManagerImpl extends BaseServiceImpl<ProductCommandMapper, ProductCommand> implements ProductCommandManager {

    private final ProductCommandMapper productCommandMapper;

    @Override
    public List<ProductCommand> checkCode(Long serviceId, String commandCode) {
        QueryWrap<ProductCommand> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(null != serviceId, ProductCommand::getServiceId, serviceId);
        queryWrap.lambda().eq(CharSequenceUtil.isNotBlank(commandCode), ProductCommand::getCommandCode, commandCode);
        return productCommandMapper.selectList(queryWrap);
    }

    @Override
    public List<ProductCommand> findAllByServiceIds(List<Long> serviceIds) {
        QueryWrap<ProductCommand> queryWrap = new QueryWrap<>();
        queryWrap.lambda().in(CollectionUtil.isNotEmpty(serviceIds), ProductCommand::getServiceId, serviceIds);
        return productCommandMapper.selectList(queryWrap);
    }
}


