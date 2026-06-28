package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 分割打开窗口出参
 */
public class NET_OUT_SPLIT_OPEN_WINDOW extends SdkStructure {
    public int nWindowID;                // 窗口ID
    public byte[] byReserved = new byte[256];  // 保留字段
}
