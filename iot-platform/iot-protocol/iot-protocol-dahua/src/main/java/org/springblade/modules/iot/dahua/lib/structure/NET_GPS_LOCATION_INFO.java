package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * GPS定位信息
 */
public class NET_GPS_LOCATION_INFO extends SdkStructure {
    public double dbLatitude;           // 纬度
    public double dbLongitude;          // 经度
    public int nAltitude;              // 海拔高度
    public int nSpeed;                 // 速度
    public int nAngle;                 // 角度
    public int nTime;                  // 时间
    public byte[] byReserved = new byte[256];  // 保留字段
}
