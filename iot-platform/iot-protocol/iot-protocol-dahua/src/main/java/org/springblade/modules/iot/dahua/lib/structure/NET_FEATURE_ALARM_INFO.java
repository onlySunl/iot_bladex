package org.springblade.modules.iot.dahua.lib.structure;
/**
 * 属性报警信息
*/
public class NET_FEATURE_ALARM_INFO extends SdkStructure
{
    /**
     * 报警模式
    */
    public byte[]           szAlarmMode = new byte[64];
    /**
     * 预留字段
    */
    public byte[]           szReserved = new byte[256];

    public NET_FEATURE_ALARM_INFO() {
    }
}

