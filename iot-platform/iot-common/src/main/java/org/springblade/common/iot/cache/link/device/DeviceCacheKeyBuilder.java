package org.springblade.common.iot.cache.link.device;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 设备档案 KEY
 * <p>
 * #device
 * <p>
 * [服务模块名:]业务类型[:业务字段][:value类型][:设备唯一标识] -> obj
 * link:def_device:deviceIdentification:obj:1 -> {}
 *
 * @author mqttsnet
 * @date 2023/5/30 6:45 下午
 */
public class DeviceCacheKeyBuilder implements CacheKeyBuilder {

    private String tenantId;

    /**
     * @param value deviceIdentification or clientId
     * @return {@link  CacheKey} key
     */
    public static CacheKey build(String value) {
        return new DeviceCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).key(value);
    }

    @Override
    public String getTenant() {
        return String.valueOf(this.tenantId);
    }

    @Override
    public DeviceCacheKeyBuilder setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Link.DEVICE;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.LINK;
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
        return Duration.ofHours(72);
    }
}
