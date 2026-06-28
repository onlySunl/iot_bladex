package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * 订阅事件恢复入参
 */
public class NET_IN_ATTACH_EVENT_RESTORE extends SdkStructure {
    public LLong hAttachHandle;          // 订阅句柄
    public byte[] byReserved = new byte[256];  // 保留字段
}
