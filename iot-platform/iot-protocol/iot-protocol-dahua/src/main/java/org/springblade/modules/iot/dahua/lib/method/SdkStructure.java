package org.springblade.modules.iot.dahua.lib.method;

import com.sun.jna.Structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class SdkStructure extends Structure {
    @Override
    protected List<String> getFieldOrder() {
        List<String> fieldOrderList = new ArrayList<String>();
        for (Class<?> cls = getClass();
             !cls.equals(NetSDKLib.SdkStructure.class);
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
        //            System.out.println(fieldOrderList);

        return fieldOrderList;
    }
}
