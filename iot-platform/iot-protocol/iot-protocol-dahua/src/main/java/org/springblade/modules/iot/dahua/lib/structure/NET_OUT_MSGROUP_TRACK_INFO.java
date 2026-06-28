package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description 手动选定目标跟踪出参
 * @date 2022/09/14 14:02:06
 */
public class NET_OUT_MSGROUP_TRACK_INFO extends SdkStructure {
	/**
	 * 结构体大小
	 */
    public int              dwSize;

	public NET_OUT_MSGROUP_TRACK_INFO() {
		this.dwSize = this.size();
	}
}

