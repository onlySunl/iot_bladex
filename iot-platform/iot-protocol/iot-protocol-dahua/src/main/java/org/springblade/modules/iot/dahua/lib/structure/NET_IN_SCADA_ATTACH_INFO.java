package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * SCADA订阅信息入参
 */
public class NET_IN_SCADA_ATTACH_INFO extends SdkStructure {
    public byte[] szDeviceID;            // 设备ID
    public byte[] szPointName;           // 数据点名称
    public LLong hAttachHandle;          // 订阅句柄
    public byte[] byReserved = new byte[256];  // 保留字段
}
