package org.springblade.common.iot.cache.video.device;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

import java.time.Duration;

/**
 * <p>
 * 设备信息 KEY
 * <p>
 * [服务模块名:]业务类型[:业务字段][:value类型][:ID] -> obj
 * video:def_device_info:deviceIdentification:obj:1 -> {}
 *
 * @author mqttsnet
 * @date 2025/4/18 16:45 下午
 */
public class DeviceInfoCacheKeyBuilder implements CacheKeyBuilder {
    private String tenantId;

    /**
     * @param deviceIdentification 设备唯一标识
     * @return {@link CacheKey} key
     */
    public static CacheKey build(String deviceIdentification) {
        return new DeviceInfoCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).key(deviceIdentification);
    }

    @Override
    public DeviceInfoCacheKeyBuilder setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getTenant() {
        return String.valueOf(this.tenantId);
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Video.DEVICE_INFO;
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
