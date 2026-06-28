package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * 添加设备回调信息
 */
public class NET_CB_ATTACH_ADD_DEVICE extends SdkStructure {
    public byte[] szDeviceID = new byte[64];   // 设备ID
    public int nStatus;                 // 状态
    public LLong hAttachHandle;         // 订阅句柄
    public byte[] byReserved = new byte[256];  // 保留字段
}
