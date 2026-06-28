package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 获取窗口列表入参
 */
public class NET_IN_SPLIT_GET_WINDOWS extends SdkStructure {
    public int nChannel;                 // 通道号
    public byte[] byReserved = new byte[256];  // 保留字段
}
