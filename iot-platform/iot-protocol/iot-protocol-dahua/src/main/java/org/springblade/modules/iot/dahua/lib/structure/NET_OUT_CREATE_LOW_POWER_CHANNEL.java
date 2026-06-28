package org.springblade.modules.iot.dahua.lib.structure;
/**
 * CLIENT_CreateLowPowerChannel 接口输出参数
*/
public class NET_OUT_CREATE_LOW_POWER_CHANNEL extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_CREATE_LOW_POWER_CHANNEL() {
        this.dwSize = this.size();
    }
}

