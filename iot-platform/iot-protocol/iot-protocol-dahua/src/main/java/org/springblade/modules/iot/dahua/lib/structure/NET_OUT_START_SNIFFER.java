package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_StartSniffer 接口输出参数
*/
public class NET_OUT_START_SNIFFER extends NetSDKLib.SdkStructure
{
    public int              dwSize;

    public NET_OUT_START_SNIFFER() {
        this.dwSize = this.size();
    }
}

