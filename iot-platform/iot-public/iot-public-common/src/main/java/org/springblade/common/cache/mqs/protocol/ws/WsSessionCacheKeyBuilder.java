package org.springblade.common.cache.mqs.protocol.ws;

import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.common.cache.ContextUtil;
import org.springblade.common.cache.CacheKey;
import org.springblade.common.cache.CacheKeyBuilder;
import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * -----------------------------------------------------------------------------
 * File Name: WsSessionCacheKeyBuilder.java
 * -----------------------------------------------------------------------------
 * Description:
 * Ws Session KEY
 * -----------------------------------------------------------------------------
 * [服务模块名:]业务类型[:业务字段][:value类型][:yyyyMMddHH] -> obj
 * mqs:def_protocol_ws_session:clientId:obj:1 -> {}
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
 * @date 2023-11-23 11:52
 */
public class WsSessionCacheKeyBuilder implements CacheKeyBuilder {

    public static CacheKey build() {
        return new WsSessionCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).key();
    }

    public static CacheKey build(String clientId) {
        return new WsSessionCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).key(clientId);
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Mqs.PROTOCOL_WS_SESSION;
    }


    @Override
    public String getModular() {
        return CacheKeyModular.MQS;
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
        return Duration.ofHours(1L);
    }
}
