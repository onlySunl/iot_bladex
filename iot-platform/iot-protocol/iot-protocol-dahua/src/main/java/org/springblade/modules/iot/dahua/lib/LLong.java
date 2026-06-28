package org.springblade.modules.iot.dahua.lib;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import java.io.Serializable;

/**
 * 长整型指针封装
 */
public class LLong implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static int size;
    
    private long value;
    
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
        this.value = value;
    }
    
    public long longValue() {
        return value;
    }
    
    public static LLong create(long value) {
        return new LLong(value);
    }
    
    public static LLong create(Pointer p) {
        if (p == null) {
            return new LLong(0);
        }
        if (size == 8) {
            return new LLong(p.getLong(0));
        } else {
            return new LLong(p.getInt(0));
        }
    }
    
    public boolean isValid() {
        return value != 0;
    }
    
    public static LLong INVALID = new LLong(0);
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LLong lLong = (LLong) o;
        return value == lLong.value;
    }
    
    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }
}
