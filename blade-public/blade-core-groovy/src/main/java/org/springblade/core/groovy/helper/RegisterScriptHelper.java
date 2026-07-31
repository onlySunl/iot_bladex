package org.springblade.core.groovy.helper;

import org.springblade.basic.model.cache.CacheKey;
import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * 手动注册脚本助手
 *
 * @author mqttsnet 2025/03/30 21:09
 */
public interface RegisterScriptHelper {


    /**
     * <p>
     * 刷新groovy脚本缓存
     * </p>
     *
     * @param cacheKey   key
     * @param content    脚本内容
     * @param allowCover 是否允许覆盖
     * @return true / false
     * @throws Exception 异常
     */
    boolean flushGroovyScriptCache(@NonNull CacheKey cacheKey, @NonNull String content, boolean allowCover) throws Exception;


    /**
     * <p>
     * 注册groovy脚本 并注册到脚本注册中心
     * </p>
     *
     * @param cacheKey   key
     * @param content    脚本内容
     * @param allowCover 是否允许覆盖
     * @return true / false
     * @throws Exception 异常
     */
    boolean registerScript(@NonNull CacheKey cacheKey, @NonNull String content, boolean allowCover) throws Exception;

    /**
     * <p>
     * 批量注册groovy脚本，key为脚本Key，value 为脚本内容
     * </p>
     *
     * @param scriptMap  脚本信息map
     * @param allowCover 是否允许覆盖
     * @return true / false
     * @throws Exception 异常
     */
    boolean batchRegisterScript(@NonNull Map<CacheKey, String> scriptMap, boolean allowCover) throws Exception;


    /**
     * <p>
     * 按key清除脚本
     * </p>
     *
     * @param cacheKey key
     */
    Boolean clear(@NonNull CacheKey cacheKey);

}
