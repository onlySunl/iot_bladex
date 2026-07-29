package org.springblade.modules.iot.productcommandrequest.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.text.CharSequenceUtil;
import org.springblade.core.mvc.manager.impl.SuperManagerImpl;
import org.springblade.core.database.mybatis.conditions.query.QueryWrap;
import org.springblade.basic.utils.BeanPlusUtil;
import org.springblade.modules.iot.productcommandrequest.entity.ProductCommandRequest;
import org.springblade.modules.iot.productcommandrequest.manager.ProductCommandRequestManager;
import org.springblade.modules.iot.productcommandrequest.mapper.ProductCommandRequestMapper;
import org.springblade.modules.iot.productcommandrequest.vo.save.ProductCommandRequestSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 产品模型服务命令属性请求参数
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductCommandRequestManagerImpl extends SuperManagerImpl<ProductCommandRequestMapper, ProductCommandRequest> implements ProductCommandRequestManager {

    private final ProductCommandRequestMapper productCommandRequestMapper;

    @Override
    public List<ProductCommandRequest> checkCode(Long serviceId, Long commandId, String parameterCode) {
        QueryWrap<ProductCommandRequest> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(null != serviceId, ProductCommandRequest::getServiceId, serviceId);
        queryWrap.lambda().eq(null != commandId, ProductCommandRequest::getCommandId, commandId);
        queryWrap.lambda().eq(CharSequenceUtil.isNotBlank(parameterCode), ProductCommandRequest::getParameterCode, parameterCode);
        return productCommandRequestMapper.selectList(queryWrap);
    }

    @Override
    public List<ProductCommandRequestSaveVO> selectCommandRequests(List<Long> commandIds) {
        QueryWrap<ProductCommandRequest> queryWrap = new QueryWrap<>();
        queryWrap.lambda().in(CollectionUtil.isNotEmpty(commandIds), ProductCommandRequest::getCommandId, commandIds);
        return BeanPlusUtil.toBeanList(productCommandRequestMapper.selectList(queryWrap), ProductCommandRequestSaveVO.class);
    }
}


