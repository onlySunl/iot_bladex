package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 测温数据
 */
public class NET_RADIOMETRY_DATA extends SdkStructure {
    public int nChannel;                // 通道号
    public int nTemperature;           // 温度值
    public int nMaxTemperature;        // 最高温度
    public int nMinTemperature;        // 最低温度
    public int nAvgTemperature;        // 平均温度
    public byte[] byReserved = new byte[256];  // 保留字段
}
