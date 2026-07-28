package org.springblade.basic.jackson;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

/**
 * 为了支持按 属性名名序列化及反序列化问题 #v8-7957 tESTCtpEnum这种属性会反序列化成testctpEnum
 */
public class SeeyonPropertyNamingStrategy extends PropertyNamingStrategy {

    private String getNameByMethod(AnnotatedMethod method, String defaultName) {
        String name = method.getName();
        if (name.startsWith("get") || name.startsWith("set")) {
            if (name.length() > 3) {
                char[] chars = name.substring(3).toCharArray();
                char c = chars[0];
                chars[0] = Character.toLowerCase(c);
                return new String(chars);
            }
        } else {
            if (name.length() > 2) {
                char[] chars = name.substring(2).toCharArray();
                char c = chars[0];
                chars[0] = Character.toLowerCase(c);
                return new String(chars);
            }
        }

        return defaultName;
    }

    @Override
    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method,
                                      String defaultName) {
        return getNameByMethod(method, defaultName);
    }

    @Override
    public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method,
                                      String defaultName) {
        return getNameByMethod(method, defaultName);
    }

}
