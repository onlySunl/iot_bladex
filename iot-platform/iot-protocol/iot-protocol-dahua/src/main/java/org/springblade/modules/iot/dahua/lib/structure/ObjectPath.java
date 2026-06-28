package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * @author 251823
 * @description 图路径对象
 * @date 2021/02/23
 */
public class ObjectPath extends SdkStructure {
	/**
	 *  路径字节数组
	 */
    public byte[]           objectPath = new byte[MAX_PATH];
}

