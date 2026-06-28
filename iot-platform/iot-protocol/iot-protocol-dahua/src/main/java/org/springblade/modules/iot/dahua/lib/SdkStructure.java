package org.springblade.modules.iot.dahua.lib;

import com.sun.jna.Structure;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * SDK结构体基类
 */
public class SdkStructure extends Structure {
    @Override
    protected List<String> getFieldOrder() {
        List<String> fieldOrderList = new ArrayList<>();
        for (Class<?> cls = getClass();
             !cls.equals(SdkStructure.class);
             cls = cls.getSuperclass()) {
            Field[] fields = cls.getDeclaredFields();
            int modifiers;
            for (Field field : fields) {
                modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) || !Modifier.isPublic(modifiers)) {
                    continue;
                }
                fieldOrderList.add(field.getName());
            }
        }
        return fieldOrderList;
    }

    @Override
    public int fieldOffset(String name) {
        return super.fieldOffset(name);
    }
}
