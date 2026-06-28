package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * GPS信息
 */
public class GPS_Info extends SdkStructure {
    public double dbLatitude;           // 纬度
    public double dbLongitude;           // 经度
    public int nAltitude;               // 海拔高度
    public int nSpeed;                  // 速度
    public int nAngle;                  // 角度
    public byte[] byReserved = new byte[256];  // 保留字段
}
