package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description 通用名字字符串字节数组对象
 * @date 2021/01/13
 */
public class MaxNameByteArrInfo extends SdkStructure {
	/**
	 * 二维数组内字符串对应字节数组
	 */
    public byte[]           name = new byte[MAX_NAME_LEN];
}

