package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;


/**
 * @author 251823
 * @description CLIENT_QueryNextLog 输入参数
 * @date 2023/06/12 09:23:40
 */
public class NET_IN_QUERYNEXTLOG extends SdkStructure {
    public int              dwSize;
	/**
	 * 需要查询的日志条数
	 */
    public int              nGetCount;

	public NET_IN_QUERYNEXTLOG() {
		this.dwSize = this.size();
	}
}

