package org.springblade.modules.iot.dahua.lib.structure;


import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author ： 260611
 * @description ： 冲洗信息
 * @since ： Created in 2021/11/29 10:47
 */

public class NET_PTZ_WASH_INFO extends SdkStructure {
    /**
     * 喷水枪的水平偏转角度
     */
    public float            fAnagle;
    /**
     * 喷水枪距离污点距离
     */
    public float            fDistance;
    /**
     * 保留字节
     */
    public byte[]           szReserved = new byte[256];
}

