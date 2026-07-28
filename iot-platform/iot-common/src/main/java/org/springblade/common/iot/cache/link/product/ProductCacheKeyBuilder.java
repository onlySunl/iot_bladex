package org.springblade.common.iot.cache.link.product;

import java.time.Duration;

import org.springblade.basic.base.entity.SuperEntity;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.model.cache.CacheKeyBuilder;
import org.springblade.common.iot.cache.CacheKeyModular;
import org.springblade.common.iot.cache.CacheKeyTable;

/**
 * 产品信息 KEY
 * <p>
 * #product
 * <p>
 * [服务模块名:]业务类型[:业务字段][:value类型][:产品标识] -> obj
 * link:def_product:id:obj:1 -> {}
 *
 * @author mqttsnet
 * @date 2023/5/30 6:45 下午
 */
public class ProductCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build(String productIdentification) {
        return new ProductCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).key(productIdentification);
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Link.PRODUCT;
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
        return Duration.ofHours(24);
    }
}
