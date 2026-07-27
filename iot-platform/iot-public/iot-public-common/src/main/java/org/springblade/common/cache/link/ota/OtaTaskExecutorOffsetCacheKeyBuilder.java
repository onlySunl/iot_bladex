package org.springblade.common.cache.link.ota;

import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;
import org.springblade.common.entity.CustomBaseEntity;
import com.mqttsnet.basic.context.ContextUtil;
import com.mqttsnet.basic.model.cache.CacheKey;
import com.mqttsnet.basic.model.cache.CacheKeyBuilder;

import java.time.Duration;

/**
 * -----------------------------------------------------------------------------
 * File Name: OtaTaskExecutorOffsetCacheKeyBuilder.java
 * -----------------------------------------------------------------------------
 * Description:
 * OTA升级任务执行器偏移量 KEY
 * 毫秒时间戳 VALUE
 * -----------------------------------------------------------------------------
 * [服务模块名:]业务类型[:业务字段][:value类型] -> obj
 * link:def_ota_upgrade_task_executor_offset:obj:1 -> {}
 *
 * @author ShiHuan Sun
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2024-03-06 17:53
 */
public class OtaTaskExecutorOffsetCacheKeyBuilder implements CacheKeyBuilder {

    private Long tenantId;

    public static CacheKey build() {
        return new OtaTaskExecutorOffsetCacheKeyBuilder().setTenantId(ContextUtil.getTenantId()).key();
    }

    @Override
    public String getTenant() {
        return String.valueOf(this.tenantId);
    }

    @Override
    public OtaTaskExecutorOffsetCacheKeyBuilder setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Link.OTA_UPGRADE_TASK_EXECUTOR_OFFSET;
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
        return Duration.ofMinutes(30L);
    }
}
