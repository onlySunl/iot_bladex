package org.springblade.common.cache.link.ota;

import java.time.Duration;

import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;
import org.springblade.common.entity.CustomBaseEntity;
import com.mqttsnet.basic.context.ContextUtil;
import com.mqttsnet.basic.model.cache.CacheKey;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;

/**
 * OTA升级记录 KEY
 * <p>
 * #ota_upgrade_records
 * <p>
 * [服务模块名:]业务类型[:业务字段][:value类型][:主键ID] -> obj
 * link:def_ota_upgrade_records:id:obj:1 -> {}
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/12/23
 */
public class OtaUpgradeRecordsCacheKeyBuilder implements CacheKeyBuilder {

    private Long tenantId;

    /**
     * @param id OTA升级记录ID
     * @return {@link  CacheKey} key
     */
    public static CacheKey build(String id) {
        return new OtaUpgradeRecordsCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).key(id);
    }

    @Override
    public String getTenant() {
        return String.valueOf(this.tenantId);
    }

    @Override
    public OtaUpgradeRecordsCacheKeyBuilder setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Link.OTA_UPGRADE_RECORDS;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.LINK;
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
        return Duration.ofHours(24);
    }
}
