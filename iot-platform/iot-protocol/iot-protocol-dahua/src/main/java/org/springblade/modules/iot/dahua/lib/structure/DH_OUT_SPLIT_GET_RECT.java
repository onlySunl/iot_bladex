package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description CLIENT_GetSplitWindowRect接口输出参数(获取窗口位置)
 * @date 2023/06/13 14:09:53
 */
public class DH_OUT_SPLIT_GET_RECT extends SdkStructure {
    public int              dwSize;
	/**
	 * 窗口位置, 0~8191
	 */
    public DH_RECT stuRect = new DH_RECT();

	public DH_OUT_SPLIT_GET_RECT() {
		this.dwSize = this.size();
	}
}

