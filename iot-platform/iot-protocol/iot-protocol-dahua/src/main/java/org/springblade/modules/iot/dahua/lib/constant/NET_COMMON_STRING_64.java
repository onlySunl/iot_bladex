package org.springblade.modules.iot.dahua.lib.constant;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 通用字符串结构体 - 64字节
 */
public class NET_COMMON_STRING_64 extends SdkStructure {
    public byte[] szString = new byte[64];
}
