package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * 总线订阅入参
 */
public class NET_IN_BUS_ATTACH extends SdkStructure {
    public byte[] szBusName;             // 总线名称
    public LLong hAttachHandle;          // 订阅句柄
    public byte[] byReserved = new byte[256];  // 保留字段
}
