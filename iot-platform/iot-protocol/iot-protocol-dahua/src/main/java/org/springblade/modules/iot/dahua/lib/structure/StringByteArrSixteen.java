package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description 字符串字节数组对象(长度16)
 * @date 2021/01/13
 */
public class StringByteArrSixteen extends SdkStructure {
	/**
	 * 二维数组内字符串对应字节数组
	 */
    public byte[]           data = new byte[CFG_COMMON_STRING_16];
}

