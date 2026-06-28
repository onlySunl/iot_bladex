package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 订阅状态入参
 */
public class NET_IN_ATTACH_STATE extends SdkStructure {
    public int nChannel;                 // 通道号
    public int nType;                    // 状态类型
    public byte[] byReserved = new byte[256];  // 保留字段
}
