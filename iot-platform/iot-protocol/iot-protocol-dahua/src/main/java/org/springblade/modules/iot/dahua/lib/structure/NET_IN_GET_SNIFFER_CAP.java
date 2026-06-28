package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetSnifferCaps 接口入参
*/
public class NET_IN_GET_SNIFFER_CAP extends SdkStructure
{
    public int              dwSize;

    public NET_IN_GET_SNIFFER_CAP() {
        this.dwSize = this.size();
    }
}

