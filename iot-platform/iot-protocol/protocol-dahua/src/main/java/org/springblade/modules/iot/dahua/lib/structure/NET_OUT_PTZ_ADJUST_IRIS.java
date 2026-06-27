package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_PTZAdjustIris 接口输出参数
*/
public class NET_OUT_PTZ_ADJUST_IRIS extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_PTZ_ADJUST_IRIS() {
        this.dwSize = this.size();
    }
}

