package org.springblade.modules.iot.dahua.lib.structure;


/**
 * @author 251823
 * @description CLIENT_StartQueryLog 输出参数
 * @date 2023/06/12 09:21:42
 */
public class NET_OUT_START_QUERYLOG extends SdkStructure {
    public int              dwSize;

	public NET_OUT_START_QUERYLOG() {
		this.dwSize = this.size();
	}
}

