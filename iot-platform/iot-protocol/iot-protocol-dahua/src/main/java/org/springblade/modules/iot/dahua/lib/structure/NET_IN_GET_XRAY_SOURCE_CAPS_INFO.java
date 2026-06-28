package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetXRaySourceCaps 接口输入参数
*/
public class NET_IN_GET_XRAY_SOURCE_CAPS_INFO extends SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_IN_GET_XRAY_SOURCE_CAPS_INFO() {
        this.dwSize = this.size();
    }
}

