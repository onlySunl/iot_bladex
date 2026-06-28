package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 视频统计摘要
 */
public class NET_VIDEOSTAT_SUMMARY extends SdkStructure {
    public int nChannel;                 // 通道号
    public int nState;                   // 状态
    public int nPeopleNum;               // 人数
    public int nVehicleNum;             // 车辆数
    public byte[] byReserved = new byte[256];  // 保留字段
}
