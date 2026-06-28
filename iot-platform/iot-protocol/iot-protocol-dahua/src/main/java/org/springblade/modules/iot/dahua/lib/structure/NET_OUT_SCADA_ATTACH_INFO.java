package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * SCADA订阅信息出参
 */
public class NET_OUT_SCADA_ATTACH_INFO extends SdkStructure {
    public LLong hAttachHandle;          // 订阅句柄
    public byte[] byReserved = new byte[256];  // 保留字段
}
