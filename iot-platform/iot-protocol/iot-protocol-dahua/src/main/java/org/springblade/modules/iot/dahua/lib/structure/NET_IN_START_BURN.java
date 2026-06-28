package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * 开始刻录入参
 */
public class NET_IN_START_BURN extends SdkStructure {
    public int nChannel;                 // 通道号
    public LLong hSession;              // 会话句柄
    public byte[] byReserved = new byte[256];  // 保留字段
}
