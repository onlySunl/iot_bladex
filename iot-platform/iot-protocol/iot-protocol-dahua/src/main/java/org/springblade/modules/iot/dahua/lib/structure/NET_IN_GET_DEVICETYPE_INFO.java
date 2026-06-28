package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;


/**
 * @author 251823
 * @description CLIENT_GetDeviceType 入参
 * @date 2023/03/15 20:18:25
 */
public class NET_IN_GET_DEVICETYPE_INFO extends SdkStructure {
	/**
	 * 结构体大小
	 */
    public int              dwSize;

	public NET_IN_GET_DEVICETYPE_INFO() {
		this.dwSize = this.size();
	}
}

