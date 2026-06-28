package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;


/**
 * @author 251823
 * @description CLIENT_SetStateManager 接口出参
 * @date 2023/05/11 14:19:51
 */
public class NET_OUT_SET_STATEMANAGER_INFO extends SdkStructure {
	/**
	 * 结构体大小
	 */
    public int              dwSize;

	public NET_OUT_SET_STATEMANAGER_INFO() {
		this.dwSize = this.size();
	}
}

