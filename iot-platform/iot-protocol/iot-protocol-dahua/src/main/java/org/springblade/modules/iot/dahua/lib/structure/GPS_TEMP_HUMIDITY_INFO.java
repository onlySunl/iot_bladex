package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 温湿度信息
 */
public class GPS_TEMP_HUMIDITY_INFO extends SdkStructure {
    public int nTemperature;            // 温度
    public int nHumidity;              // 湿度
    public byte[] byReserved = new byte[256];  // 保留字段
}
