package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * 自动控制信息
*/
public class NET_SLEEPING_CONTROL_AUTO_CTRL extends SdkStructure
{
    /**
     * 低电休眠开关
    */
    public int              bPowerSaveEnable;
    /**
     * 自动进入休眠模式的电量百分比阈值，取值范围[1,100]
    */
    public int              nSleepElectricity;
    /**
     * 保留字节
    */
    public byte[]           szResvered = new byte[256];

    public NET_SLEEPING_CONTROL_AUTO_CTRL() {
    }
}

