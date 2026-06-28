package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * 订阅状态出参
 */
public class NET_OUT_ATTACH_STATE extends SdkStructure {
    public LLong hAttachHandle;          // 订阅句柄
    public byte[] byReserved = new byte[256];  // 保留字段
}
