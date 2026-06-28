package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * SCADA报警点信息列表
 */
public class NET_SCADA_NOTIFY_POINT_ALARM_INFO_LIST extends SdkStructure {
    public int nAlarmCount;             // 报警数量
    public int nOffset;                 // 偏移量
    public byte[] byReserved = new byte[256];  // 保留字段
}
