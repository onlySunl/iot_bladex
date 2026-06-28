package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_RadiometryGetCurTemperAll 接口入参
*/
public class NET_IN_RADIOMETRY_GET_CUR_TEMPER_ALL_INFO extends SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 通道号，热成像通道有效
    */
    public int              nChannel;

    public NET_IN_RADIOMETRY_GET_CUR_TEMPER_ALL_INFO() {
        this.dwSize = this.size();
    }
}

