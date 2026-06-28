package org.springblade.modules.iot.dahua.lib.method;

import com.sun.jna.IntegerType;
import com.sun.jna.Native;
import org.springblade.modules.iot.dahua.lib.Utils;

public class LLong extends IntegerType {
    private static final long serialVersionUID = 1L;

    /**
     * Size of a native long, in bytes.
     */
    public static int size;

    static {
        size = Native.LONG_SIZE;
        if (Utils.getOsPrefix().toLowerCase().equals("linux-amd64")
                || Utils.getOsPrefix().toLowerCase().equals("win32-amd64")
                || Utils.getOsPrefix().toLowerCase().equals("mac-64")) {
            size = 8;
        } else if (Utils.getOsPrefix().toLowerCase().equals("linux-i386")
                || Utils.getOsPrefix().toLowerCase().equals("win32-x86")) {
            size = 4;
        }
    }

    /**
     * Create a zero-valued LLong.
     */
    public LLong() {
        this(0);
    }

    /**
     * Create a LLong with the given value.
     */
    public LLong(long value) {
        super(size, value);

    }
}