package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description EM_MSGROUP_OPERATE_SLAVE_POSITION_TO_MASTER 出参
 * @date 2022/09/14 14:09:33
 */
public class NET_OUT_MSGROUP_SLAVE_POSITION_TO_MASTER_INFO extends SdkStructure {
	/**
	 * 结构体大小
	 */
    public int              dwSize;
	/**
	 * 从机画面上的物体坐标在主机画面上的位置，8192坐标系
	 */
    public NET_RECT stuRect = new NET_RECT();

	public NET_OUT_MSGROUP_SLAVE_POSITION_TO_MASTER_INFO() {
		this.dwSize = this.size();
	}
}

