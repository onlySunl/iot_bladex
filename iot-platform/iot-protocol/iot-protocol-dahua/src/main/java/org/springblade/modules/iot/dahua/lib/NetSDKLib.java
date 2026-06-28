package org.springblade.modules.iot.dahua.lib;

import com.sun.jna.*;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.*;
import com.sun.jna.win32.*;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinNT.*;
import com.sun.jna.platform.win32.Kernel32Lib;
import org.springblade.modules.iot.dahua.lib.constant.NetSDKConstants;
import org.springblade.modules.iot.dahua.lib.method.*;
import org.springblade.modules.iot.dahua.lib.structure.*;
import org.springblade.modules.iot.dahua.lib.enumeration.*;
import org.springblade.modules.iot.dahua.lib.Utils;
import org.springblade.modules.iot.dahua.lib.LastError;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 大华 NetSDK JNA 接口封装
 */
public interface NetSDKLib extends
    InitLoginMethods,
    ConfigMethods,
    RecordMethods,
    AlarmMethods,
    PTZMethods,
    StreamMethods,
    OtherMethods,
    Library {

    NetSDKLib NETSDK_INSTANCE = Native.load(
        LibraryLoad.getLoadLibrary("dhnetsdk"),
        NetSDKLib.class
    );

    NetSDKLib CONFIG_INSTANCE = Native.load(
        LibraryLoad.getLoadLibrary("dhconfigsdk"),
        NetSDKLib.class
    );

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


}
