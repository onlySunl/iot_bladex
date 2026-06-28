package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_DoWakeUpLowPowerDevcie 接口输入参数
*/
public class NET_IN_WAKE_UP_INFO extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_IN_WAKE_UP_INFO() {
        this.dwSize = this.size();
    }
}

