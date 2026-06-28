package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_SetTourSource 接口输出参数(设置窗口轮巡显示源)
*/
public class NET_OUT_SET_TOUR_SOURCE extends SdkStructure
{
    public int              dwSize;

    public NET_OUT_SET_TOUR_SOURCE() {
        this.dwSize = this.size();
    }
}

