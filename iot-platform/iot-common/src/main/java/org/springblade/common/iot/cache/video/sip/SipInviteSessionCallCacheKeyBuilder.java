package org.springblade.common.iot.cache.video.sip;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.model.cache.CacheHashKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

import java.io.Serializable;
import java.time.Duration;

/**
 * <p>
 * SIP Session Call 信息 Hash KEY
 * <p>
 * [服务模块名:]业务类型[:业务字段][:value类型][:ID] -> obj
 * video:def_sip_session_call:deviceIdentification:obj:1 -> {}
 *
 * @author mqttsnet
 * @date 2025/4/18 16:45 下午
 */
public class SipInviteSessionCallCacheKeyBuilder implements CacheKeyBuilder {

    private String tenantId;

    /**
     * @param key deviceIdentification
     * @return {@link CacheHashKey} hash key
     */
    public static CacheHashKey builder(Serializable key) {
        return new SipInviteSessionCallCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).hashKey(key);
    }

    /**
     * @param key   deviceIdentification
     * @param field callId
     * @return {@link CacheHashKey} hash key
     */
    public static CacheHashKey builder(String key, String field) {
        return new SipInviteSessionCallCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).hashFieldKey(field, key);
    }

    @Override
    public SipInviteSessionCallCacheKeyBuilder setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }


    @Override
    public String getTenant() {
        return String.valueOf(this.tenantId);
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Video.SIP_SESSION_CALL;
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
        return Duration.ofHours(1);
    }
}
