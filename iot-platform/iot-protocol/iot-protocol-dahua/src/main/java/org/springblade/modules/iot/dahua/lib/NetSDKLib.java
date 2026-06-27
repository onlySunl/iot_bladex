package org.springblade.modules.iot.dahua.lib;

import com.sun.jna.*;
import com.sun.jna.ptr.IntByReference;
import org.springblade.modules.iot.dahua.lib.enumeration.*;
import org.springblade.modules.iot.dahua.lib.method.*;
import org.springblade.modules.iot.dahua.lib.structure.*;
import org.springblade.modules.iot.dahua.lib.constant.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * NetSDK JNA接口封装
 * <p>
 * 文件结构：
 * - constant/NetSDKConstants.java: 常量定义
 * - enumeration/: 枚举定义
 * - structure/: 结构体定义
 * - method/NetSDKMethods*.java: 方法定义（已拆分）
 */
public interface NetSDKLib extends
        NetSDKMethods1,
        NetSDKMethods2,
        NetSDKMethods3,
        NetSDKMethods4,
        NetSDKMethods5,
        NetSDKMethods6,
        NetSDKMethods7,
        NetSDKMethods8,
        Library {

    NetSDKLib NETSDK_INSTANCE = Native.load(LibraryLoad.getLoadLibrary("dhnetsdk"), NetSDKLib.class);

    NetSDKLib CONFIG_INSTANCE = Native.load(LibraryLoad.getLoadLibrary("dhconfigsdk"), NetSDKLib.class);

    /**
     * SDK结构体基类
     */


    /**
     * 长整型指针封装
     */
    class LLong extends IntegerType implements Serializable {
        private static final long serialVersionUID = 1L;
        public static int size;

        static {
            size = Native.LONG_SIZE;
            if (Utils.getOsPrefix().equalsIgnoreCase("linux-amd64")
                    || Utils.getOsPrefix().equalsIgnoreCase("win32-amd64")
                    || Utils.getOsPrefix().equalsIgnoreCase("mac-64")) {
                size = 8;
            } else if (Utils.getOsPrefix().equalsIgnoreCase("linux-i386")
                    || Utils.getOsPrefix().equalsIgnoreCase("win32-x86")) {
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

    // 使用 static import 导入常量和枚举
}
