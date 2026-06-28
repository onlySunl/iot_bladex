package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_GetCalibrateStatus 接口输入参数
*/
public class NET_IN_GET_CALIBRATE_STATUS extends SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_IN_GET_CALIBRATE_STATUS() {
        this.dwSize = this.size();
    }
}

