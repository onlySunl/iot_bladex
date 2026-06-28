package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetStoragePortInfo 入参
*/
public class NET_IN_GET_PORTINFO extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_IN_GET_PORTINFO() {
        this.dwSize = this.size();
    }
}

