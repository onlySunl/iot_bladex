package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * 开始刻录会话出参
 */
public class NET_OUT_START_BURN_SESSION extends SdkStructure {
    public LLong hSession;              // 会话句柄
    public byte[] byReserved = new byte[256];  // 保留字段
}
