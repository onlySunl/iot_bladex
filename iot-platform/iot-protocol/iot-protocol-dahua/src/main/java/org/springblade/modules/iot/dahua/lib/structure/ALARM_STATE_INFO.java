package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 报警状态信息
 */
public class ALARM_STATE_INFO extends SdkStructure {
    public int nAlarmType;              // 报警类型
    public int nAlarmChannel;           // 报警通道
    public int nAlarmState;             // 报警状态
    public byte[] byReserved = new byte[256];  // 保留字段
}
