package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 获取窗口列表出参
 */
public class NET_OUT_SPLIT_GET_WINDOWS extends SdkStructure {
    public int nWindowCount;             // 窗口数量
    public int nWindowIDs;                // 窗口ID数组指针
    public byte[] byReserved = new byte[256];  // 保留字段
}
