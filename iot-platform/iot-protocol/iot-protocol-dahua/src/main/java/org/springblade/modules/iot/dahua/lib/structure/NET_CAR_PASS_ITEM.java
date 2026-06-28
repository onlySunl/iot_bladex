package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 车辆通行记录
 */
public class NET_CAR_PASS_ITEM extends SdkStructure {
    public int nChannel;                // 通道号
    public byte[] szPlate = new byte[32];    // 车牌号
    public int nPlateColor;             // 车牌颜色
    public int nVehicleColor;           // 车身颜色
    public int nPassTime;               // 通过时间
    public byte[] byReserved = new byte[256];  // 保留字段
}
