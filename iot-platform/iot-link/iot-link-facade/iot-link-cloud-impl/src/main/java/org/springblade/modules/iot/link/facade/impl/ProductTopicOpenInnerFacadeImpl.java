package org.springblade.modules.iot.link.facade.impl;

import org.springblade.basic.base.R;
import org.springblade.modules.iot.link.api.inner.ProductTopicOpenInnerApi;
import org.springblade.modules.iot.link.facade.ProductTopicOpenInnerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品 Topic 开放接口 Facade ── cloud 实现(走 Feign)。
 *
 * @author mqttsnet
 * @since 2026-05-06
 */
@Service
public class ProductTopicOpenInnerFacadeImpl implements ProductTopicOpenInnerFacade {

    @Lazy
    @Autowired
    private ProductTopicOpenInnerApi productTopicOpenInnerApi;

    @Override
    public R<List<String>> findTopicsByIds(List<Long> ids) {
        return productTopicOpenInnerApi.findTopicsByIds(ids);
    }
}
