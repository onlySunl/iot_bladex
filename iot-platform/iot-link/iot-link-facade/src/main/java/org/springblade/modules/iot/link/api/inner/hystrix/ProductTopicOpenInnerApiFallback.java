package org.springblade.modules.iot.link.api.inner.hystrix;

import org.springblade.common.base.R;
import org.springblade.modules.iot.link.api.inner.ProductTopicOpenInnerApi;

import java.util.List;

/**
 * {@link ProductTopicOpenInnerApi} 降级处理 ── 超时返回 timeout R。
 *
 * @author mqttsnet
 * @since 2026-05-06
 */
public class ProductTopicOpenInnerApiFallback implements ProductTopicOpenInnerApi {

    @Override
    public R<List<String>> findTopicsByIds(List<Long> ids) {
        return R.timeout();
    }
}
