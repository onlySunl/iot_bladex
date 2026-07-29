package org.springblade.basic.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlContext;
import org.springblade.basic.serialize.DateStrFormat;
import org.springblade.basic.utils.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
public class JsonCoreUtils {

    private static ObjectMapper mapper;
    private static ObjectMapper originMapper;
    private static ObjectMapper notIncludeNullMapper;
    private static ObjectMapper processNumberMapper;
    private static ObjectMapper enumToCodeMapper;

    static {
        mapper = new ObjectMapper();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 允许出现特殊字符和转义符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 允许出现单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        mapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
        //为了支持按 属性名名序列化及反序列化问题 #v8-7957 tESTCtpEnum这种属性会反序列化成testctpEnum
        mapper.setPropertyNamingStrategy(new SeeyonPropertyNamingStrategy());
        mapper.setDateFormat(new DateStrFormat());
        //这行必须在setDateFormat后边. 否则特性打不开.
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Messageable.class, new EnumJsonSerializer());
//        simpleModule.addDeserializer(Enum.class, new EnumJsonDeserializer());
        mapper.registerModule(simpleModule);
        mapper.registerModule(new JavaTimeModule());
        mapper.setTimeZone(TimeZone.getDefault());

        enumToCodeMapper = new ObjectMapper();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        enumToCodeMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        enumToCodeMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 允许出现特殊字符和转义符
        enumToCodeMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 允许出现单引号
        enumToCodeMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        enumToCodeMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        enumToCodeMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
        //为了支持按 属性名名序列化及反序列化问题 #v8-7957 tESTCtpEnum这种属性会反序列化成testctpEnum
        enumToCodeMapper.setPropertyNamingStrategy(new SeeyonPropertyNamingStrategy());
//        SimpleModule simpleModule1 = new SimpleModule();
//        simpleModule1.addSerializer(Messageable.class, new EnumToCodeSerializer());
//        enumToCodeMapper.registerModule(simpleModule1);
        enumToCodeMapper.setTimeZone(TimeZone.getDefault());

        originMapper = new ObjectMapper();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        originMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        originMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 允许出现特殊字符和转义符
        originMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 允许出现单引号
        originMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        originMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        originMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
        //为了支持按 属性名名序列化及反序列化问题 #v8-7957 tESTCtpEnum这种属性会反序列化成testctpEnum
        originMapper.setPropertyNamingStrategy(new SeeyonPropertyNamingStrategy());
        originMapper.setTimeZone(TimeZone.getDefault());

        processNumberMapper = new ObjectMapper();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        processNumberMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        processNumberMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 允许出现特殊字符和转义符
        processNumberMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 允许出现单引号
        processNumberMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        processNumberMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        processNumberMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
        //为了支持按 属性名名序列化及反序列化问题 #v8-7957 tESTCtpEnum这种属性会反序列化成testctpEnum
        processNumberMapper.setPropertyNamingStrategy(new SeeyonPropertyNamingStrategy());
        processNumberMapper.setDateFormat(new DateStrFormat());
        //这行必须在setDateFormat后边. 否则特性打不开.
        processNumberMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        SimpleModule module = new SimpleModule();
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);
        module.addSerializer(Double.class, ToStringSerializer.instance);
        module.addSerializer(Double.TYPE, ToStringSerializer.instance);
        module.addSerializer(Float.class, ToStringSerializer.instance);
        module.addSerializer(Float.TYPE, ToStringSerializer.instance);
        module.addSerializer(Short.class, ToStringSerializer.instance);
        module.addSerializer(Short.TYPE, ToStringSerializer.instance);
        module.addSerializer(BigDecimal.class, ToStringSerializer.instance);
        processNumberMapper.registerModule(module);
        processNumberMapper.setTimeZone(TimeZone.getDefault());

        notIncludeNullMapper = new ObjectMapper();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        notIncludeNullMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        notIncludeNullMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        notIncludeNullMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
        // 允许出现特殊字符和转义符
        notIncludeNullMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 允许出现单引号
        notIncludeNullMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        notIncludeNullMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        //为了支持按 属性名名序列化及反序列化问题 #v8-7957 tESTCtpEnum这种属性会反序列化成testctpEnum
        notIncludeNullMapper.setPropertyNamingStrategy(new SeeyonPropertyNamingStrategy());
        //序列化时不包含null字段
        notIncludeNullMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        notIncludeNullMapper.setDateFormat(new DateStrFormat());
        //这行必须在setDateFormat后边. 否则特性打不开.
        notIncludeNullMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        notIncludeNullMapper.setTimeZone(TimeZone.getDefault());

    }

    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            log.warn("write to json string error:" + object, e);
            return null;
        }
    }

    public static String toJsonEnumToCode(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return enumToCodeMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.warn("write to json string error:" + object, e);
            return null;
        }
    }

    public static String toJson(Object object, boolean longToString) {
        if (longToString) {
            return toJsonLongToString(object);
        }
        return toJson(object);
    }

    public static String toJsonLongToString(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return processNumberMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.warn("write to json string error:" + object, e);
            return null;
        }
    }

    public static String toJsonDecimalToString(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return processNumberMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.warn("write to json string error:" + object, e);
            return null;
        }
    }

    public static String toJsonNotIncludeNull(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return notIncludeNullMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.warn("write to json string error:" + object, e);
            return null;
        }
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return readValue(jsonString, clazz);
        } catch (IOException e) {
            log.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }


    public static <T> T fromJson(String jsonString, Class<T> clazz, Class generalClazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(clazz, generalClazz);
            return readValue(jsonString, javaType);
        } catch (IOException e) {
            log.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }


    public static Map<String, String> toStringMap(String jsonString) {
        if (StringUtils.isEmpty(jsonString)) {
            return Collections.emptyMap();
        }

        Map<String, String> jsonMap = new HashMap<>();

        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            jsonNode.fieldNames().forEachRemaining(
                    name -> jsonMap.put(name,
                            jsonNode.get(name).isTextual() ? jsonNode.get(name).textValue() : jsonNode.get(name).toString()
                    )
            );

        } catch (Exception e) {
            return null;
        }

        return jsonMap;
    }

    public static <K, V> Map<K, V> toMap(String jsonString, Class<K> keyClass, Class<V> valueClass) {
        if (StringUtils.isEmpty(jsonString)) {
            return Collections.emptyMap();
        }
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(LinkedHashMap.class, keyClass, valueClass);
            return readValue(jsonString, javaType);
        } catch (Exception e) {
            log.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    public static Map<String, Object> toMap(String jsonString) {
        if (StringUtils.isEmpty(jsonString)) {
            return Collections.emptyMap();
        }

        try {
            return readValue(jsonString, Map.class);
        } catch (Exception e) {
            return null;
        }

    }

    public static List<String> toStringList(String jsonString) {
        if (StringUtils.isEmpty(jsonString)) {
            return Collections.emptyList();
        }

        List<String> jsonList = new ArrayList<>();

        try {
            ArrayNode arrayNode = (ArrayNode) mapper.readTree(jsonString);
            for (JsonNode node : arrayNode) {
                jsonList.add(node.toString());
            }

        } catch (Exception e) {
            log.warn("{}, parse json string error: {}", e.getMessage(), jsonString);
            return null;
        }

        return jsonList;
    }

    public static List toList(String jsonString) {
        if (StringUtils.isEmpty(jsonString)) {
            return Collections.emptyList();
        }

        try {
            return readValue(jsonString, List.class);
        } catch (Exception e) {
            log.warn("{}, parse json string error: {}", e.getMessage(), jsonString);
            return null;
        }

    }

    public static <T> List<T> toList(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return Collections.emptyList();
        }
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            return readValue(jsonString, javaType);
        } catch (Exception e) {
            log.warn("parse json string error:" + jsonString, e);
            return null;
        }

    }

    private static <T> T readValue(String content, JavaType valueType) throws JsonProcessingException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        TypeBindings typeBindings = valueType.getBindings();
        Thread.currentThread().setContextClassLoader(typeBindings.getBoundType(typeBindings.size() - 1).getRawClass().getClassLoader());
        try {
            return mapper.readValue(content, valueType);
        } catch (Throwable t) {
            return originMapper.readValue(content, valueType);
        } finally {
            Thread.currentThread().setContextClassLoader(classLoader);
        }
    }

    private static <T> T readValue(String content, Class<T> valueType) throws JsonProcessingException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(valueType.getClassLoader());
        try {
            return mapper.readValue(content, valueType);
        } catch (Throwable t) {
            return originMapper.readValue(content, valueType);
        } finally {
            Thread.currentThread().setContextClassLoader(classLoader);
        }
    }

    public static <T> T parse(String jsonStr, Class<T> clazz) throws Exception {
        return mapper.readValue(jsonStr, clazz);
    }

    public static <T> T getValue(Map map, String path, Class<T> klass) throws Exception {
        OgnlContext ctx = (OgnlContext) Ognl.createDefaultContext(map);
        // 获取指定路径的值
        T value = (T) Ognl.getValue(path, ctx, ctx.getRoot());
        return value;
    }

}
