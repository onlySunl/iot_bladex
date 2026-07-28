package org.springblade.modules.iot.manager.bridge;

import org.springblade.basic.base.manager.SuperManager;
import org.springblade.modules.iot.entity.bridge.SubscriptionSource;
import org.springblade.modules.iot.vo.query.bridge.SubscriptionSourcePageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 数据桥接-订阅源
 * </p>
 *
 * @author mqttsnet
 * @date 2026-04-28
 */
public interface SubscriptionSourceManager extends SuperManager<SubscriptionSource> {

    List<SubscriptionSource> getSubscriptionSourceList(SubscriptionSourcePageQuery query);

    SubscriptionSource getByCode(String sourceCode);

    /**
     * 取启用中的订阅源（启动时拉到内存）
     */
    List<SubscriptionSource> getEnabledSources();
}
