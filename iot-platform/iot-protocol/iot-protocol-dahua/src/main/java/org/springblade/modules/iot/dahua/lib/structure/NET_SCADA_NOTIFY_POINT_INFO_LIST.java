package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * SCADA通知点信息列表
 */
public class NET_SCADA_NOTIFY_POINT_INFO_LIST extends SdkStructure {
    public int nPointCount;             // 点数量
    public int nOffset;                 // 偏移量
    public byte[] byReserved = new byte[256];  // 保留字段
}
