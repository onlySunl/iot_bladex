package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 分割打开窗口入参
 */
public class NET_IN_SPLIT_OPEN_WINDOW extends SdkStructure {
    public int nChannel;                 // 通道号
    public int nWindowID;                // 窗口ID
    public int nLeft;                    // 窗口左边距
    public int nTop;                     // 窗口上边距
    public int nRight;                   // 窗口右边距
    public int nBottom;                  // 窗口下边距
    public byte[] byReserved = new byte[256];  // 保留字段
}
