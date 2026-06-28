package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_AttachSniffer 接口出参
*/
public class NET_OUT_ATTACH_SNIFFER extends SdkStructure
{
    public int              dwSize;

    public NET_OUT_ATTACH_SNIFFER() {
        this.dwSize = this.size();
    }
}

