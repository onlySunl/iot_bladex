package org.springblade.modules.iot.productversionchangelog.manager.impl;

import java.util.List;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.database.mybatis.BladeServiceImpl;
import org.springblade.common.base.request.PageParams;
import org.springblade.common.database.mybatis.conditions.Wraps;
import org.springblade.common.database.mybatis.conditions.query.LbQueryWrap;
import org.springblade.modules.iot.productversionchangelog.entity.ProductVersionChangeLog;
import org.springblade.modules.iot.productversionchangelog.manager.ProductVersionChangeLogManager;
import org.springblade.modules.iot.productversionchangelog.mapper.ProductVersionChangeLogMapper;
import org.springblade.modules.iot.productversionchangelog.vo.query.ProductVersionChangeLogPageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 产品物模型版本变更日志通用业务实现。
 *
 * @author mqttsnet
 * @see ProductVersionChangeLogManager
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductVersionChangeLogManagerImpl
    extends BladeServiceImpl<ProductVersionChangeLogMapper, ProductVersionChangeLog>
    implements ProductVersionChangeLogManager {

    private final ProductVersionChangeLogMapper productVersionChangeLogMapper;

    @Override
    public IPage<ProductVersionChangeLog> getPage(PageParams<ProductVersionChangeLogPageQuery> params) {
        IPage<ProductVersionChangeLog> page = params.buildPage(ProductVersionChangeLog.class);
        ProductVersionChangeLogPageQuery model = params.getModel();

        LbQueryWrap<ProductVersionChangeLog> wrap = Wraps.lbQ();
        wrap.eq(StrUtil.isNotBlank(model.getProductIdentification()),
                ProductVersionChangeLog::getProductIdentification, model.getProductIdentification())
            .eq(StrUtil.isNotBlank(model.getVersionNo()),
                ProductVersionChangeLog::getVersionNo, model.getVersionNo())
            .eq(model.getChangeType() != null,
                ProductVersionChangeLog::getChangeType, model.getChangeType())
            .orderByDesc(ProductVersionChangeLog::getCreatedTime);

        return productVersionChangeLogMapper.selectPage(page, wrap);
    }

    @Override
    public List<ProductVersionChangeLog> listByProductIdentification(String productIdentification) {
        LbQueryWrap<ProductVersionChangeLog> wrap = Wraps.lbQ();
        wrap.eq(ProductVersionChangeLog::getProductIdentification, productIdentification)
            .orderByDesc(ProductVersionChangeLog::getCreatedTime);
        return productVersionChangeLogMapper.selectList(wrap);
    }
}
