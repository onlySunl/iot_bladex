package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;


/**
 * @author 260611
 * @description CLIENT_GetVTOManagerRelation 入参
 * @origin autoTool
 * @date 2023/10/19 14:16:46
 */
public class NET_IN_GET_VTO_MANAGER_RELATION extends SdkStructure {
	/**
	 * / 结构体大小
	 */
    public int              dwSize;
	/**
	 * / 开始查询的记录偏移量
	 */
    public int              nOffset;

	public NET_IN_GET_VTO_MANAGER_RELATION() {
		this.dwSize = this.size();
	}
}

