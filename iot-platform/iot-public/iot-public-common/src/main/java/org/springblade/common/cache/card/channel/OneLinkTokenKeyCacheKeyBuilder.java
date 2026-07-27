package org.springblade.common.cache.card.channel;

import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;
import org.springblade.common.cache.link.counter.DownLinkDataCounterCacheKeyBuilder;
import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.common.cache.ContextUtil;
import org.springblade.common.cache.CacheKey;
import org.springblade.common.cache.CacheKeyBuilder;

import java.time.Duration;

/**
 * OneLink TokenKey 缓存KEY
 * <p>
 * 移动 OneLink 平台TokenKey 缓存KEY
 * <p>
 * [服务模块名:]业务类型[:业务字段][:value类型][:channelName] -> obj
 * card:def_channel_one_link_token_key:channelName:obj:1 -> {}
 *
 * @author mqttsnet
 * @date 2024/06/30 6:45 下午
 */
public class OneLinkTokenKeyCacheKeyBuilder implements CacheKeyBuilder {
    private Long tenantId;

    public static CacheKey build() {
        return new DownLinkDataCounterCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).key();
    }

    public static CacheKey build(String channelName) {
        return new DownLinkDataCounterCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).key(channelName);
    }

    @Override
    public String getTenant() {
        return String.valueOf(this.tenantId);
    }

    @Override
    public OneLinkTokenKeyCacheKeyBuilder setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Card.DEF_CHANNEL_ONE_LINK_TOKEN_KEY;
    }


    @Override
    public String getModular() {
        return CacheKeyModular.CARD;
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
        return Duration.ofMinutes(50L);
    }
}
