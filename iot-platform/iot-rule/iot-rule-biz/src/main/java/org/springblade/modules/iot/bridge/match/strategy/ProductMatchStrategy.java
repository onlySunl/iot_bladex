package org.springblade.modules.iot.bridge.match.strategy;

import cn.hutool.core.collection.CollUtil;
import org.springblade.common.iot.bridge.BridgeMessageEnvelope;
import org.springblade.common.iot.constant.BizConstant;
import org.springblade.modules.iot.bridge.matcher.BridgeMatchConfig;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 产品标识维度策略。配置列表为空 → skip;含 {@code *} / {@code all}({@link BizConstant#ALL}) → hit;
 * 否则 envelope.productIdentification 必须在列表内才 hit。
 *
 * @author mqttsnet
 * @since 2026-05-06
 */
@Component
@Order(BridgeMatchOrder.PRODUCT)
public class ProductMatchStrategy implements BridgeMatchStrategy {

    private static final String STRATEGY = "PRODUCT";

    @Override
    public String name() {
        return STRATEGY;
    }

    @Override
    public boolean appliesTo(BridgeMatchConfig cfg) {
        return cfg != null && CollUtil.isNotEmpty(cfg.getProductIdentifications());
    }

    @Override
    public MatchResult match(BridgeMessageEnvelope env, BridgeMatchConfig cfg) {
        return MatchResult.safe(STRATEGY, () -> {
            String actual = Optional.ofNullable(env)
                    .map(BridgeMessageEnvelope::getProductIdentification)
                    .orElse(null);
            return BridgeMatchConfig.matchesAny(cfg.getProductIdentifications(), actual)
                    ? MatchResult.hit("matched: " + actual)
                    : MatchResult.miss("'" + actual + "' not in " + cfg.getProductIdentifications());
        });
    }
}
