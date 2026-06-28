package org.springblade.modules.iot.dahua.lib.structure;


/**
 * @author 260611
 * @description 订阅可视域输出参数
 * @origin autoTool
 * @date 2023/05/30 10:04:52
 */
public class NET_OUT_VIEW_RANGE_STATE extends SdkStructure {
    public int              dwSize;

	public NET_OUT_VIEW_RANGE_STATE() {
		this.dwSize = this.size();
	}
}

