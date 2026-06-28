package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 开始刻录会话入参
 */
public class NET_IN_START_BURN_SESSION extends SdkStructure {
    public int nChannel;                 // 通道号
    public byte[] byReserved = new byte[256];  // 保留字段
}
