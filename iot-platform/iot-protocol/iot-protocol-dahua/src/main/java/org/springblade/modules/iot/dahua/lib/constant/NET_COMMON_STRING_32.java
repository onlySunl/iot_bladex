package org.springblade.modules.iot.dahua.lib.constant;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 通用字符串结构体 - 32字节
 */
public class NET_COMMON_STRING_32 extends SdkStructure {
    public byte[] szString = new byte[32];
}
