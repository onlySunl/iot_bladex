package org.springblade.common.cache.video.stream;

import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;
import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.common.cache.ContextUtil;
import org.springblade.common.cache.CacheKey;
import org.springblade.common.cache.CacheKeyBuilder;

import java.time.Duration;

/**
 * 流自动恢复重试计数缓存 Key 构建器（租户维度）。
 * <p>
 * Key:   lc:video:{tenantId}:def_stream_recovery:id:obj:{deviceId:channelId}
 * TTL:   5min
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026-04-11
 */
public class StreamRecoveryCacheKeyBuilder implements CacheKeyBuilder {

    private Long tenantId;

    public static CacheKey build(String deviceChannelKey) {
        return new StreamRecoveryCacheKeyBuilder()
                .setTenantId(ContextUtil.getTenantId())
                .key(deviceChannelKey);
    }

    @Override
    public StreamRecoveryCacheKeyBuilder setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getTenant() {
        return this.tenantId != null ? String.valueOf(this.tenantId) : null;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Video.STREAM_RECOVERY;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.VIDEO;
    }

    @Override
    public String getField() {
        return CustomBaseEntity.ID_FIELD;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.obj;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofMinutes(5);
    }
}
