package org.springblade.modules.iot.dahua.lib;

import com.sun.jna.*;
import com.sun.jna.ptr.IntByReference;
import org.springblade.modules.iot.dahua.lib.constant.NetSDKConstants;
import org.springblade.modules.iot.dahua.lib.method.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 大华 NetSDK JNA 接口封装
 * 
 * 文件结构：
 * - NetSDKLib.java: 核心接口定义
 * - constant/NetSDKConstants.java: 常量定义
 * - enumeration/: 枚举定义
 * - structure/: 结构体定义
 * - method/: 方法和回调接口定义
 * 
 * @author BladeX
 */
public interface NetSDKLib extends
    CallbackInterfaces,
    InitLoginMethods,
    ConfigMethods,
    RecordMethods,
    AlarmMethods,
    PTZMethods,
    StreamMethods,
    OtherMethods,
    Library {

    /**
     * SDK 实例加载
     */
    NetSDKLib NETSDK_INSTANCE = Native.load(
        LibraryLoad.getLoadLibrary("dhnetsdk"),
        NetSDKLib.class
    );

    NetSDKLib CONFIG_INSTANCE = Native.load(
        LibraryLoad.getLibraryLoad("dhconfigsdk"),
        NetSDKLib.class
    );

    /**
     * SDK结构体基类
     */
    class SdkStructure extends Structure {
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

    /**
     * 长整型指针封装
     */
    class LLong extends IntegerType implements Serializable {
        private static final long serialVersionUID = 1L;
        public static int size;

        static {
            size = Native.LONG_SIZE;
            String prefix = Utils.getOsPrefix();
            if (prefix.equalsIgnoreCase("linux-amd64")
                    || prefix.equalsIgnoreCase("win32-amd64")
                    || prefix.equalsIgnoreCase("mac-64")) {
                size = 8;
            } else if (prefix.equalsIgnoreCase("linux-i386")
                    || prefix.equalsIgnoreCase("win32-x86")) {
                size = 4;
            }
        }

        public LLong() {
            this(0);
        }

        public LLong(long value) {
            super(size, value);
        }
    }

}
