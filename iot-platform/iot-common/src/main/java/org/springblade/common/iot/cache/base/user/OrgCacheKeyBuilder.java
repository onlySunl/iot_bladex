package org.springblade.common.iot.cache.base.user;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 组织 KEY
 * <p>
 * #base_org
 * <p>
 * [服务模块名:]业务类型[:业务字段][:value类型][:组织id] -> obj
 * base:base_org:id:obj:1 -> {}
 *
 * @author mqttsnet
 * @date 2020/9/20 6:45 下午
 */
public class OrgCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getTable() {
        return CacheKeyTable.Base.BASE_ORG;
    }


    @Override
    public String getModular() {
        return CacheKeyModular.BASE;
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
        return Duration.ofHours(24);
    }
}
