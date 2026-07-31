package org.springblade.common.iot.cache.video.sip;

import java.time.Duration;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

/**
 * SIP 事务订阅缓存 Key 构建器（租户维度）。
 * <p>
 * Key:   lc:video:{tenantId}:def_sip_subscribe:id:obj:{callId_cSeq}
 * Value: SipSubscribeCache {tenantId, deviceId, channelId, status, responseCode, errorMsg}
 * TTL:   30s（SIP 事务超时）
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026-04-11
 */
public class SipSubscribeCacheKeyBuilder implements CacheKeyBuilder {

    private String tenantId;

    /**
     * 构建 Key（不含 field，用于 DEL 等操作）
     */
    public static CacheKey build(String callIdCSeq) {
        return new SipSubscribeCacheKeyBuilder()
            .setTenantId(ContextUtil.getTenantId())
            .key(callIdCSeq);
    }

    /**
     * 构建完整 Key
     */
    public static CacheKey buildKey(String callIdCSeq) {
        return new SipSubscribeCacheKeyBuilder()
            .setTenantId(ContextUtil.getTenantId())
            .key(callIdCSeq);
    }

    @Override
    public SipSubscribeCacheKeyBuilder setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getTenant() {
        return this.tenantId != null ? String.valueOf(this.tenantId) : null;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Video.SIP_SUBSCRIBE;
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
