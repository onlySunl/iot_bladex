package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * @author 251823
 * @description  历史接种日期字符串对应字节数组
 * @date 2021/08/15
 */
public class VaccinateDateByteArr extends SdkStructure {
	/**
	 *  历史接种日期字符串对应字节数组
	 */
    public byte[]           vaccinateDateByteArr = new byte[32];
}

