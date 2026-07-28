package org.springblade.common.iot.cache.video.sip;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 异步请求结果缓存 Key 构建器（租户维度）。
 * <p>
 * Key:   lc:video:{tenantId}:def_deferred_result:id:obj:{commandType_requestId}
 * TTL:   30s
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026-04-11
 */
public class DeferredResultCacheKeyBuilder implements CacheKeyBuilder {

    private Long tenantId;

    public static CacheKey build(String commandTypeRequestId) {
        return new DeferredResultCacheKeyBuilder()
                .setTenantId(ContextUtil.getTenantId())
                .key(commandTypeRequestId);
    }

    @Override
    public DeferredResultCacheKeyBuilder setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getTenant() {
        return this.tenantId != null ? String.valueOf(this.tenantId) : null;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Video.DEFERRED_RESULT;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.VIDEO;
    }

    @Override
    public String getField() {
        return SuperEntity.ID_FIELD;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.obj;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofSeconds(30);
    }
}
