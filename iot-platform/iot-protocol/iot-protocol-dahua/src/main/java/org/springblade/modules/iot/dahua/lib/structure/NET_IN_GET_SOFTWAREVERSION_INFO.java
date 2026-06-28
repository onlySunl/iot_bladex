package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_GetSoftwareVersion 入参
*/
public class NET_IN_GET_SOFTWAREVERSION_INFO extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_IN_GET_SOFTWAREVERSION_INFO() {
        this.dwSize = this.size();
    }
}

