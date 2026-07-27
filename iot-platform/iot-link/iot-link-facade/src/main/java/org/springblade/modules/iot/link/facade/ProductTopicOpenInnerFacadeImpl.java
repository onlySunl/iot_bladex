package org.springblade.modules.iot.link.facade;

import org.springblade.common.base.R;
import org.springblade.modules.iot.producttopic.service.ProductTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品 Topic 开放接口 Facade ── boot 单体实现(直调 Service)。
 *
 * @author mqttsnet
 * @since 2026-05-06
 */
@Slf4j
@Service
public class ProductTopicOpenInnerFacadeImpl implements ProductTopicOpenInnerFacade {

    @Autowired
    private ProductTopicService productTopicService;

    @Override
    public R<List<String>> findTopicsByIds(List<Long> ids) {
        try {
            return R.success(productTopicService.findTopicsByIds(ids));
        } catch (Exception e) {
            log.error("查询产品Topic失败,ids:{}", ids, e);
            return R.fail("查询产品Topic失败: " + e.getMessage());
        }
    }
}
