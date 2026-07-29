package org.springblade.modules.iot.productcommandresponse.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.text.CharSequenceUtil;
import org.springblade.core.mvc.manager.impl.SuperManagerImpl;
import org.springblade.core.database.mybatis.conditions.query.QueryWrap;
import org.springblade.basic.utils.BeanPlusUtil;
import org.springblade.modules.iot.productcommandresponse.entity.ProductCommandResponse;
import org.springblade.modules.iot.productcommandresponse.manager.ProductCommandResponseManager;
import org.springblade.modules.iot.productcommandresponse.mapper.ProductCommandResponseMapper;
import org.springblade.modules.iot.productcommandresponse.vo.save.ProductCommandResponseSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 产品模型服务命令属性响应参数
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductCommandResponseManagerImpl extends SuperManagerImpl<ProductCommandResponseMapper, ProductCommandResponse> implements ProductCommandResponseManager {

    private final ProductCommandResponseMapper productCommandResponseMapper;

    @Override
    public List<ProductCommandResponse> checkCode(Long serviceId, Long commandId, String parameterCode) {
        QueryWrap<ProductCommandResponse> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(null != serviceId, ProductCommandResponse::getServiceId, serviceId);
        queryWrap.lambda().eq(null != commandId, ProductCommandResponse::getCommandId, commandId);
        queryWrap.lambda().eq(CharSequenceUtil.isNotBlank(parameterCode), ProductCommandResponse::getParameterCode, parameterCode);
        return productCommandResponseMapper.selectList(queryWrap);
    }

    @Override
    public List<ProductCommandResponseSaveVO> selectCommandResponses(List<Long> commandIds) {
        QueryWrap<ProductCommandResponse> queryWrap = new QueryWrap<>();
        queryWrap.lambda().in(CollectionUtil.isNotEmpty(commandIds), ProductCommandResponse::getCommandId, commandIds);
        return BeanPlusUtil.toBeanList(productCommandResponseMapper.selectList(queryWrap), ProductCommandResponseSaveVO.class);
    }
}


