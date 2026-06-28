package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description CLIENT_SetSplitWindowRect接口输出参数(设置窗口位置)
 * @date 2023/06/13 14:11:27
 */
public class DH_OUT_SPLIT_SET_RECT extends SdkStructure {
    public int              dwSize;

	public DH_OUT_SPLIT_SET_RECT() {
		this.dwSize = this.size();
	}
}

