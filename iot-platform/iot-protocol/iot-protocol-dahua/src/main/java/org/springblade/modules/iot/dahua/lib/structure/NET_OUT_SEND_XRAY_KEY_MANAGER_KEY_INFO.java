package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_SendXRayKeyManagerKey 的输出参数
*/
public class NET_OUT_SEND_XRAY_KEY_MANAGER_KEY_INFO extends SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_SEND_XRAY_KEY_MANAGER_KEY_INFO() {
        this.dwSize = this.size();
    }
}

