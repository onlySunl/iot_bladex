package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 分割模式信息
 */
public class NET_SPLIT_MODE_INFO extends SdkStructure {
    public int nMode;                    // 分割模式
    public int nSplitModeCount;          // 分割数量
    public int nCurrentChannels;         // 当前通道数
    public byte[] byReserved = new byte[256];  // 保留字段
}
