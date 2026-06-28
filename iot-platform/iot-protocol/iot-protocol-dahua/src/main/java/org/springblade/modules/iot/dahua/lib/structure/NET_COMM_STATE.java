package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 通用状态信息
 */
public class NET_COMM_STATE extends SdkStructure {
    public int nChannel;                 // 通道号
    public int nState;                   // 状态值
    public byte[] byReserved = new byte[256];  // 保留字段
}
