package org.springblade.core.groovy.loader;

import org.springblade.basic.exception.BizException;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.core.groovy.entity.ScriptEntry;
import org.springblade.core.groovy.entity.ScriptQuery;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * 脚本加载器
 *
 * @author mqttsnet 2025/03/18 11:32
 */
public interface ScriptLoader {

    /**
     * <p>
     * 加载脚本，如果缓存中不存在，则从数据源查找，找到后将脚本编译为Class
     * </p>
     *
     * @param query 查询对象
     * @return {@link ScriptEntry} 脚本实体
     * @throws Exception 异常
     */
    ScriptEntry load(@NonNull ScriptQuery query) throws Exception;

    /**
     * <p>
     * 加载脚本，如果缓存中不存在，则从数据源查找，找到后将脚本编译为Class
     * </p>
     *
     * @param cacheKey 缓存Key
     * @return {@link ScriptEntry} 脚本实体
     * @throws Exception 异常
     */
    ScriptEntry load(@NonNull CacheKey cacheKey) throws Exception;

    /**
     * <p>
     * 从数据源预加载所有的脚本（不会将脚本编译为Class）
     * </p>
     *
     * @return {@link List<ScriptEntry>} 脚本实体列表
     * @throws Exception 异常
     * @author mqttsnet 2024/9/18 3:57 下午
     */
    List<ScriptEntry> load() throws Exception;


    /**
     * 编译脚本内容
     *
     * @param scriptContent 脚本内容
     * @return {@link ScriptEntry} 包含编译后类的脚本实体
     * @throws BizException 当内容为空或编译失败时抛出
     */
    ScriptEntry compileScript(@NonNull String scriptContent) throws Exception;


}
