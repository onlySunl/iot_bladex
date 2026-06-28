package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * 设备状态回调信息
 */
public class NET_CB_ATTACH_DEVICE_STATE extends SdkStructure {
    public int nType;                   // 类型
    public int nState;                 // 状态
    public LLong hAttachHandle;        // 订阅句柄
    public byte[] byReserved = new byte[256];  // 保留字段
}
