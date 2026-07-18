package org.springblade.modules.iot.dict.core;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 字典工具类
 * 注意：需要注入具体的字典查询服务来初始化
 */
@Slf4j
public class DictFrameworkUtils {

    private static final Duration CACHE_EXPIRE = Duration.ofMinutes(10L);

    /**
     * 缓存字典数据标签列表
     */
    private static final LoadingCache<String, List<String>> GET_DICT_DATA_LIST_CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(CACHE_EXPIRE)
            .build(new CacheLoader<String, List<String>>() {
                @Override
                public List<String> load(String dictType) {
                    // TODO: 实现字典数据查询
                    return List.of();
                }
            });

    /**
     * 缓存字典数据值解析
     */
    private static final LoadingCache<KeyValue, String> PARSE_DICT_DATA_CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(CACHE_EXPIRE)
            .build(new CacheLoader<KeyValue, String>() {
                @Override
                public String load(KeyValue kv) {
                    // TODO: 实现字典数据解析
                    return null;
                }
            });

    /**
     * 获取字典数据标签
     */
    @SneakyThrows
    public static String getDictDataLabel(String dictType, String value) {
        // TODO: 实现字典标签获取
        return value;
    }

    /**
     * 获取字典数据标签列表
     */
    @SneakyThrows
    public static List<String> getDictDataLabelList(String dictType) {
        return GET_DICT_DATA_LIST_CACHE.get(dictType);
    }

    /**
     * 解析字典数据值
     */
    @SneakyThrows
    public static String parseDictDataValue(String dictType, String label) {
        return PARSE_DICT_DATA_CACHE.get(new KeyValue(dictType, label));
    }

    @Data
    private static class KeyValue {
        private final String key;
        private final String value;

        public KeyValue(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
