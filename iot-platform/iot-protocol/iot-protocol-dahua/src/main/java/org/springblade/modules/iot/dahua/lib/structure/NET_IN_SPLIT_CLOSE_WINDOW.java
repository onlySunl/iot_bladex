package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 分割关闭窗口入参
 */
public class NET_IN_SPLIT_CLOSE_WINDOW extends SdkStructure {
    public int nWindowID;                // 窗口ID
    public byte[] byReserved = new byte[256];  // 保留字段
}
