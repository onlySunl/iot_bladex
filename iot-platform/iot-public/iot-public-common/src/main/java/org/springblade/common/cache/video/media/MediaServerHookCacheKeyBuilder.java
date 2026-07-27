package org.springblade.common.cache.video.media;

import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;
import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.common.cache.ContextUtil;
import org.springblade.common.cache.CacheKey;
import org.springblade.common.cache.CacheKeyBuilder;

import java.time.Duration;

/**
 * 媒体服务器信息 KEY
 * <p>
 * #MediaServer Hook 心跳KEY
 * <p>
 * [服务模块名:]业务类型[:业务字段][:value类型][:媒体唯一标识] -> obj
 * video:def_media_server:mediaIdentification:obj:1 -> {}
 *
 * @author mqttsnet
 * @date 2025/4/18 16:45 下午
 */
public class MediaServerHookCacheKeyBuilder implements CacheKeyBuilder {
    private Long tenantId;

    /**
     * @param mediaServerType     媒体类型
     * @param mediaIdentification 媒体唯一标识
     * @return {@link CacheKey} key
     */
    public static CacheKey build(String mediaServerType, String mediaIdentification) {
        return new MediaServerHookCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).key(mediaServerType, mediaIdentification);
    }

    @Override
    public MediaServerHookCacheKeyBuilder setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getTenant() {
        return String.valueOf(this.tenantId);
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Video.MEDIA_SERVER_HOOK;
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
        return Duration.ofSeconds(60);
    }
}
