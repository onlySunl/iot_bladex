package org.springblade.core.groovy.entity;

import java.util.HashMap;

/**
 * 执行参数
 *
 * @author mqttsnet 2025/03/18 12:40
 */
public class ExecuteParams extends HashMap<String, Object> {

    /**
     * 通过key获取value，并转换为对应的类型
     *
     * @param key key
     * @return T
     */
    @SuppressWarnings("all")
    public <T> T getValue(String key) {
        return (T) get(key);
    }

}
