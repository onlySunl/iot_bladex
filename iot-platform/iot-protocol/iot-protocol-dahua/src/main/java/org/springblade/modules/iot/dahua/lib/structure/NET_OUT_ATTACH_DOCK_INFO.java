package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_AttachDockInfo 接口输出参数
*/
public class NET_OUT_ATTACH_DOCK_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_ATTACH_DOCK_INFO() {
        this.dwSize = this.size();
    }
}

