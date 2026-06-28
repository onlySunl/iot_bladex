package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

import static org.springblade.modules.iot.dahua.lib.NET_DEVICE_NAME_LEN;

/**
 * className：NET_TEMPERATURE_INFO
 * description：温度信息
 * author：251589
 * createTime：2021/2/25 14:33
 *
 * @version v1.0
 */

public class NET_TEMPERATURE_INFO extends SdkStructure {
    /**
     * dwSize;
     */
    public int              dwSize;
    /**
     *  传感器名称
     */
    public byte[]           szName = new byte[NET_DEVICE_NAME_LEN];
    /**
     *  温度
     */
    public float            fTemperature;

    public NET_TEMPERATURE_INFO(){
        this.dwSize = this.size();
    }
}

