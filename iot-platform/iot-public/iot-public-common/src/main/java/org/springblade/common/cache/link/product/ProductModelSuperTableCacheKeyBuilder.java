package org.springblade.common.cache.link.product;

import java.time.Duration;
import java.util.Random;

import org.springblade.common.cache.CacheKeyModular;
import org.springblade.common.cache.CacheKeyTable;
import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.common.cache.ContextUtil;
import org.springblade.common.cache.CacheKey;
import org.springblade.common.cache.CacheKeyBuilder;

/**
 * 产品模型 TD 超级表 / 子表结构缓存 KEY ── 按 (productIdentification, versionNo, serviceCode[, deviceIdentification]) 维度切分。
 * TD 超表名本身含 versionNo(见 ProductTdsNamer#superTableName),不同版本对应不同表 / 字段描述,
 * 缓存须按 versionNo 切分才能与 TD 表实际结构对齐。
 *
 * @author mqttsnet
 * @date 2023/5/30 6:45 下午
 */
public class ProductModelSuperTableCacheKeyBuilder implements CacheKeyBuilder {

    private static final Random RANDOM = new Random();

    /**
     * 产品维度构建(超表结构缓存):(pi, versionNo, serviceCode)。
     *
     * @param versionNo 版本序号(与 TD 超表名拼接的版本一致)
     */
    public static CacheKey build(String productIdentification, String versionNo, String serviceCode) {
        return new ProductModelSuperTableCacheKeyBuilder()
            .setTenantId(ContextUtil.getTenantId())
            .key(productIdentification, versionNo, serviceCode);
    }

    /**
     * 设备维度构建(子表结构缓存):(pi, versionNo, serviceCode, deviceIdentification)。
     *
     * @param versionNo 版本序号(取设备 boundProductVersionNo)
     */
    public static CacheKey build(String productIdentification, String versionNo, String serviceCode, String deviceIdentification) {
        return new ProductModelSuperTableCacheKeyBuilder()
            .setTenantId(ContextUtil.getTenantId())
            .key(productIdentification, versionNo, serviceCode, deviceIdentification);
    }

    @Override
    public String getTable() {
        return CacheKeyTable.Link.PRODUCT_MODEL_SUPER_TABLE;
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
        // 基础 10 分钟 + 0~5 分钟随机,避免缓存雪崩
        Duration baseDuration = Duration.ofMinutes(10);
        int randomMinutes = RANDOM.nextInt(6);
        Duration randomDuration = Duration.ofMinutes(randomMinutes);
        return baseDuration.plus(randomDuration);
    }
}
