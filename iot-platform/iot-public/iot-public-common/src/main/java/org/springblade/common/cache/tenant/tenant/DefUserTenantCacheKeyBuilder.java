package org.springblade.common.cache.tenant.tenant;

import org.springblade.common.entity.CustomBaseEntity;
import com.mqttsnet.basic.model.cache.CacheKey;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 系统用户 KEY
 * <p>
 * #base_user
 * <p>
 * [服务模块名:]业务类型[:业务字段][:value类型][:岗位id] -> obj
 * base:base_employee:id:obj:1 -> {}
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class DefUserTenantCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build(Long id) {
        return new DefUserTenantCacheKeyBuilder().key(id);
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Base.DEF_USER_TENANT;
    }


    @Override
    public String getModular() {
        return CacheKeyModular.SYSTEM;
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
