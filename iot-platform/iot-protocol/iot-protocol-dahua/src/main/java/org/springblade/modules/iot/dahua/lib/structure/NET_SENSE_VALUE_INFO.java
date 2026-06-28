package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * 传感器类型报警的数值与emSenseMethod关联
*/
public class NET_SENSE_VALUE_INFO extends SdkStructure
{
    /**
     * 数值
    */
    public double           dValue;
    /**
     * 单位
    */
    public byte[]           szUnit = new byte[32];
    /**
     * 预留
    */
    public byte[]           szReserved = new byte[128];

    public NET_SENSE_VALUE_INFO() {
    }
}

