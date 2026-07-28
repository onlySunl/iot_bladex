package org.springblade.common.iot.cache.video.media;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 媒体服务器信息 KEY
 * <p>
 * #MediaServer
 * <p>
 * [服务模块名:]业务类型[:业务字段][:value类型][:媒体唯一标识] -> obj
 * video:def_media_server:mediaIdentification:obj:1 -> {}
 *
 * @author mqttsnet
 * @date 2025/4/18 16:45 下午
 */
public class MediaServerCacheKeyBuilder implements CacheKeyBuilder {
    private Long tenantId;

    /**
     * @param mediaServerType     媒体类型
     * @param mediaIdentification 媒体唯一标识
     * @return {@link CacheKey} key
     */
    public static CacheKey build(String mediaServerType, String mediaIdentification) {
        return new MediaServerCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).key(mediaServerType, mediaIdentification);
    }

    @Override
    public MediaServerCacheKeyBuilder setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getTenant() {
        return String.valueOf(this.tenantId);
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Video.MEDIA_SERVER;
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
