package org.springblade.core.cache.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springblade.basic.jackson.JsonUtil;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * 此时定义的序列化操作表示可以序列化所有类的对象，当然，这个对象所在的类一定要实现序列化接口
 *
 * @author mqttsnet
 * @date 2019-08-06 10:42
 */
public class RedisObjectSerializer extends GenericJackson2JsonRedisSerializer {

    public RedisObjectSerializer() {
        super(buildObjectMapper());
    }

    private static ObjectMapper buildObjectMapper() {
        ObjectMapper objectMapper = JsonUtil.newInstance();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
            ObjectMapper.DefaultTyping.NON_FINAL,
            JsonTypeInfo.As.PROPERTY);
        return objectMapper;
    }
}
