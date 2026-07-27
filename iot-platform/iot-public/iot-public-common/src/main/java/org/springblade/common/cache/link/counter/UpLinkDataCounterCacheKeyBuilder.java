package org.springblade.common.cache.link.counter;

import java.io.Serializable;
import java.time.Duration;

import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;
import org.springblade.common.entity.CustomBaseEntity;
import com.mqttsnet.basic.context.ContextUtil;
import com.mqttsnet.basic.model.cache.CacheHashKey;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;

/**
 * -----------------------------------------------------------------------------
 * File Name: UpLinkDataCounterCacheKeyBuilder.java
 * -----------------------------------------------------------------------------
 * Description:
 * 上行数据计数器 KEY
 * -----------------------------------------------------------------------------
 * [服务模块名:]业务类型[:业务字段][:value类型][:yyyyMMdd] -> number
 * link:def_up_link_data_counter:id:number:yyyyMMdd -> number
 *
 * @author ShiHuan Sun
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * <p>
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023-11-23 23:52
 */
public class UpLinkDataCounterCacheKeyBuilder implements CacheKeyBuilder {

    private Long tenantId;

    public static CacheHashKey build(Serializable key) {
        return new UpLinkDataCounterCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).hashKey(key);
    }

    /**
     * @param key   日期 yyyyMMdd
     * @param field 时分 HHmm
     * @return {@link CacheHashKey} hash key
     */
    public static CacheHashKey build(String key, String field) {
        return new UpLinkDataCounterCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).hashFieldKey(field, key);
    }

    @Override
    public String getTenant() {
        return String.valueOf(this.tenantId);
    }

    @Override
    public UpLinkDataCounterCacheKeyBuilder setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Link.UP_LINK_DATA_COUNTER;
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
        return ValueType.number;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofDays(90L);
    }
}
