package org.springblade.modules.iot.dahua.lib;

import com.sun.jna.IntegerType;
import com.sun.jna.Native;
import java.io.Serializable;

/**
 * 长整型指针封装
 */
public class LLong extends IntegerType implements Serializable {
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
