package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_MonitorWallSetScene接口输出参数(设置电视墙场景)
*/
public class NET_OUT_MONITORWALL_SET_SCENE extends SdkStructure
{
    public int              dwSize;

    public NET_OUT_MONITORWALL_SET_SCENE() {
        this.dwSize = this.size();
    }
}

