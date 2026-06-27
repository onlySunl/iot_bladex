package org.springblade.modules.iot.dahua.lib.method;

import com.sun.jna.Structure;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public  class SdkStructure extends Structure {
    @Override
    protected List<String> getFieldOrder() {
        List<String> fieldOrderList = new ArrayList<>();
        for (Class<?> cls = getClass(); !cls.equals(SdkStructure.class); cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
                    fieldOrderList.add(field.getName());
                }
            }
        }
        return fieldOrderList;
    }
}
