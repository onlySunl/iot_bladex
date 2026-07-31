package org.springblade.common.iot.cache.video.sip;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

import java.time.Duration;

/**
 * MESSAGE 消息订阅缓存 Key 构建器（租户维度）。
 * <p>
 * Key:   lc:video:{tenantId}:def_msg_subscribe:id:obj:{cmdType_sn}
 * TTL:   30s
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026-04-11
 */
public class MsgSubscribeCacheKeyBuilder implements CacheKeyBuilder {

    private String tenantId;

    public static CacheKey build(String cmdTypeSn) {
        return new MsgSubscribeCacheKeyBuilder()
                .setTenantId(ContextUtil.getTenantId())
                .key(cmdTypeSn);
    }

    @Override
    public MsgSubscribeCacheKeyBuilder setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getTenant() {
        return this.tenantId != null ? String.valueOf(this.tenantId) : null;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Video.MSG_SUBSCRIBE;
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
