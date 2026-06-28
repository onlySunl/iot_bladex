package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description EM_PRC_NETAPP_TYPE_GET_MOBILE_INTERFACE 入参
 * @date 2021/9/17
 */
public class NET_IN_NETAPP_GET_MOBILE_INTERFACE extends SdkStructure {
	/**
	 * 结构体大小
	 */
    public int              dwSize;

	public NET_IN_NETAPP_GET_MOBILE_INTERFACE() {
		this.dwSize = this.size();
	}
}

