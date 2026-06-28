package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;


/**
 * @author 260611
 * @description 删除标定点信息入参
 * @date 2023/05/24 10:24:52
 */
public class NET_IN_DELETE_CALIBRATEINFO_INFO extends SdkStructure {
	/**
	 * 结构体大小
	 */
    public int              dwSize;
	/**
	 * 标定点ID
	 */
    public int              nID;

	public NET_IN_DELETE_CALIBRATEINFO_INFO() {
		this.dwSize = this.size();
	}
}

