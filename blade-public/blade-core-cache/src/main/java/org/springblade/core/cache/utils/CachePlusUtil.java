package org.springblade.core.cache.utils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import cn.hutool.core.collection.CollUtil;
import org.springblade.core.cache.redis2.CacheResult;
import org.springblade.core.cache.repository.CachePlusOps;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.basic.model.cache.CacheHashKey;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.basic.utils.BeanPlusUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 缓存工具类：对 {@link CachePlusOps} 的 cache-aside 封装。
 *
 * <p><b>存储格式分两类，同一 key 不可混用：</b>
 * <ul>
 *   <li>单对象方法（{@link #getObjectFromCache}/{@link #getOrLoad}/{@link #hGetOrLoad}）走框架默认
 *       序列化器（带 {@code @class} 多态头），读取后按目标类型 BeanUtil 适配；</li>
 *   <li>List 方法（{@link #getOrLoadList}/{@link #hGetOrLoadList}）走 {@link JsonUtil} 纯 JSON、
 *       按 elementClass 反序列化，借此规避“根 List + {@code @class} 塞不进数组”的反序列化坑。</li>
 * </ul>
 * 用 List 方法写入、再用单对象方法读取（或反之）会失败；List 方法读到旧的 {@code @class} 数据时解析
 * 失败 → 回源 → 用纯 JSON 覆写，可自愈。
 *
 * @program: thinglinks-util
 * @description: 缓存工具类
 * @packagename: org.springblade.basic.cache.utils
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-07-13 20:42
 **/
@RequiredArgsConstructor
@Component
@Slf4j
public class CachePlusUtil {

    private final CachePlusOps cachePlusOps;

    /**
     * Generic method to retrieve an object from cache and convert it to the required type.
     *
     * @param key         The cache key used to fetch data.
     * @param targetClass The class type to which the cached object needs to be converted.
     * @param <T>         The type of the object that needs to be returned.
     * @return Returns an Optional containing the converted object if present in cache,
     * otherwise returns an empty Optional.
     */
    public <T> Optional<T> getObjectFromCache(String key, Class<T> targetClass) {
        try {
            CacheResult<Object> objectCacheResult = cachePlusOps.get(key);

            return Optional.ofNullable(objectCacheResult)
                .map(CacheResult::getRawValue)
                .map(value -> BeanPlusUtil.toBeanIgnoreError(value, targetClass));

        } catch (Exception e) {
            log.warn("Failed to get object from cache for key: {}", key, e);
            return Optional.empty();
        }
    }

    /**
     * 普通 key 的 cache-aside
     *
     * <p>等价于 {@link CachePlusOps#get(CacheKey, Function, boolean...)} 但额外做 BeanUtil 类型适配，
     * 让调用方拿到的就是目标类型而不是 raw Object。
     *
     * @param key             cache key
     * @param loader          cache miss 时回源逻辑（如 manager 查 DB）
     * @param targetClass     业务对象类型，用于 BeanUtil 适配
     * @param cacheNullValues true 时缓存 null 防穿透
     * @param <T>             业务对象类型
     * @return 封装为 Optional 的最终值
     */
    public <T> Optional<T> getOrLoad(CacheKey key,
                                     Function<CacheKey, T> loader,
                                     Class<T> targetClass,
                                     boolean cacheNullValues) {
        try {
            CacheResult<T> result = cachePlusOps.get(key, loader, cacheNullValues);
            return Optional.ofNullable(result)
                .map(CacheResult::getRawValue)
                .map(v -> targetClass.isInstance(v) ? targetClass.cast(v) : BeanPlusUtil.toBeanIgnoreError(v, targetClass));
        } catch (Exception e) {
            log.warn("Failed to get-or-load cache for key: {}", key, e);
            return Optional.empty();
        }
    }

    /**
     * List 版本的 cache-aside ── 跟 {@link #getOrLoad} 同模式,但目标值是 {@code List<E>}。
     *
     * <p>反序列化时 Jackson 默认会把每个元素还原为 {@code LinkedHashMap}(泛型擦除),
     * 这里按 {@code elementClass} 逐项 BeanUtil 适配,调用方拿到的就是 {@code List<E>}。
     *
     * @param key             cache key
     * @param loader          cache miss 时回源(返回完整 List<E>)
     * @param elementClass    List 元素类型,用于 BeanUtil 适配
     * @param cacheNullValues true 时缓存空 List 防穿透;false 时空 List 不写
     * @param <E>             元素类型
     * @return 已适配的 List;loader 返 null / 异常返空 List
     */
    public <E> List<E> getOrLoadList(CacheKey key,
                                     Function<CacheKey, List<E>> loader,
                                     Class<E> elementClass,
                                     boolean cacheNullValues) {
        try {
            CacheResult<Object> cached = cachePlusOps.get(key);
            if (cached != null && !cached.isNullVal() && cached.getRawValue() != null) {
                List<E> list = JsonUtil.parseArray(cached.getRawValue().toString(), elementClass);
                if (list != null) {
                    return list;
                }
            }
        } catch (Exception e) {
            log.warn("[getOrLoadList] 读取/解析失败 key={}, 回源 loader", key, e);
        }
        // miss / 解析失败 → loader 回源 + 纯 JSON 回填(真 cache-aside、可自愈)
        // 不复用框架的 get(key, loader)：那条路径会先把根 List 反序列化成 Object 触发 @class 数组坑、且把失败当命中，loader 不会被调用
        try {
            List<E> loaded = loader == null ? null : loader.apply(key);
            if (CollUtil.isNotEmpty(loaded)) {
                cachePlusOps.set(key, JsonUtil.toJson(loaded));
            } else if (cacheNullValues) {
                cachePlusOps.set(key, JsonUtil.toJson(Collections.emptyList()));
            }
            return loaded != null ? loaded : Collections.emptyList();
        } catch (Exception e) {
            log.error("[getOrLoadList] loader 回源失败 key={}, 降级空列表", key, e);
            return Collections.emptyList();
        }
    }

    /**
     * Hash 结构【单对象】的 cache-aside 模板：先查 hash 缓存，未命中则用 loader 回源 + 自动 hSet 写回。
     *
     * <p>{@link CachePlusOps#get(CacheKey, Function, boolean...)} 仅支持普通 key；
     * Hash 字段没有同款 loader 重载，因此把这套“hGet → 判空 → 回源 DB → hSet”的样板沉到工具类，
     * 避免每个调用方手写一遍。
     *
     * <p>读取按 {@code targetClass} 做 BeanUtil 适配（与 {@link #getOrLoad} 一致），避免把反序列化出来的
     * {@code LinkedHashMap} 或 null 哨兵盲转成 {@code T} 而在调用处抛 {@code ClassCastException}。
     * <b>仅用于单对象</b>；缓存值是 {@code List} 时请改用 {@link #hGetOrLoadList}（否则会踩 {@code @class} 数组坑）。
     *
     * @param hashKey         Redis Hash 主键 + field
     * @param loader          cache miss 时的回源逻辑（如调 Manager 查 DB）
     * @param targetClass     业务对象类型，用于 BeanUtil 适配
     * @param cacheNullValues true 时连 null 也写入 Hash（防穿透）；false 时仅在 loader 返回非空才写
     * @param <T>             业务对象类型
     * @return Optional 包裹的最终值（loader 返回 null 或命中 null 哨兵时为 empty）
     */
    public <T> Optional<T> hGetOrLoad(CacheHashKey hashKey,
                                      Function<CacheHashKey, T> loader,
                                      Class<T> targetClass,
                                      boolean cacheNullValues) {
        try {
            CacheResult<Object> result = cachePlusOps.hGet(hashKey);
            if (result != null && result.isNullVal()) {
                // 命中 null 哨兵：DB 已确认无此值，直接返回 empty、不再回源（防穿透真正生效）
                return Optional.empty();
            }
            if (result != null && result.getRawValue() != null) {
                Object raw = result.getRawValue();
                T cached = targetClass.isInstance(raw) ? targetClass.cast(raw) : BeanPlusUtil.toBeanIgnoreError(raw, targetClass);
                return Optional.ofNullable(cached);
            }
            T loaded = loader == null ? null : loader.apply(hashKey);
            if (loaded != null) {
                cachePlusOps.hSet(hashKey, loaded);
            } else if (cacheNullValues) {
                cachePlusOps.hSet(hashKey, null, true);
            }
            return Optional.ofNullable(loaded);
        } catch (Exception e) {
            log.warn("Failed to hGet-or-load cache for hashKey: {}", hashKey, e);
            return Optional.empty();
        }
    }

    /**
     * Hash 结构的 List cache-aside ── {@link #getOrLoadList} 的 hash 版。
     *
     * <p>同样以纯 JSON 字符串存 field、按 {@code elementClass} 反序列化,规避"根 List + 多态 typing(@class
     * 塞不进数组)"的反序列化坑;miss / 解析失败走 loader 回源 + hSet 回填(真 cache-aside、可自愈)。
     *
     * @param hashKey         Redis Hash 主键 + field
     * @param loader          cache miss 时回源(返回完整 List&lt;E&gt;)
     * @param elementClass    List 元素类型
     * @param cacheNullValues true 时空 List 也回填防穿透
     * @param <E>             元素类型
     * @return 已适配的 List;loader 返 null / 异常返空 List
     */
    public <E> List<E> hGetOrLoadList(CacheHashKey hashKey,
                                      Function<CacheHashKey, List<E>> loader,
                                      Class<E> elementClass,
                                      boolean cacheNullValues) {
        try {
            CacheResult<Object> cached = cachePlusOps.hGet(hashKey);
            if (cached != null && !cached.isNullVal() && cached.getRawValue() != null) {
                List<E> list = JsonUtil.parseArray(cached.getRawValue().toString(), elementClass);
                if (CollUtil.isNotEmpty(list)) {
                    return list;
                }
            }
        } catch (Exception e) {
            log.warn("[hGetOrLoadList] 读取/解析失败 hashKey={}, 回源 loader", hashKey, e);
        }
        try {
            List<E> loaded = loader == null ? null : loader.apply(hashKey);
            if (CollUtil.isNotEmpty(loaded)) {
                cachePlusOps.hSet(hashKey, JsonUtil.toJson(loaded));
            } else if (cacheNullValues) {
                cachePlusOps.hSet(hashKey, JsonUtil.toJson(Collections.emptyList()));
            }
            return loaded != null ? loaded : Collections.emptyList();
        } catch (Exception e) {
            log.error("[hGetOrLoadList] loader 回源失败 hashKey={}, 降级空列表", hashKey, e);
            return Collections.emptyList();
        }
    }

}
