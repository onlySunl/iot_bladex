package org.springblade.common.iot.cache.video.isup;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.model.cache.CacheHashKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

import java.io.Serializable;
import java.time.Duration;

/**
 * ISUP 连接缓存 Key 构建器（租户维度，Hash 结构）。
 * <p>
 * Key:   lc:video:{tenantId}:def_isup_conn:id:obj:{tenantId}
 * Field: deviceSerial
 * Value: IsupConnectionCache
 * TTL:   2h
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026-04-11
 */
public class IsupConnCacheKeyBuilder implements CacheKeyBuilder {

    private Long tenantId;

    /**
     * 构建 Hash Key（用于 hGetAll / del）
     */
    public static CacheHashKey builder(Serializable tenantKey) {
        return new IsupConnCacheKeyBuilder()
                .setTenantId(ContextUtil.getTenantId())
                .hashKey(tenantKey);
    }

    /**
     * 构建 Hash Field Key（用于 hGet / hSet / hDel）
     */
    public static CacheHashKey builder(Serializable tenantKey, String deviceSerial) {
        return new IsupConnCacheKeyBuilder()
                .setTenantId(ContextUtil.getTenantId())
                .hashFieldKey(deviceSerial, tenantKey);
    }

    @Override
    public IsupConnCacheKeyBuilder setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getTenant() {
        return this.tenantId != null ? String.valueOf(this.tenantId) : null;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Video.ISUP_CONN;
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
        return Duration.ofHours(2);
    }
}
