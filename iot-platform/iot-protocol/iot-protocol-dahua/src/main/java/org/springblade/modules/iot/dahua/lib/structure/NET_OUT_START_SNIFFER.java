package org.springblade.modules.iot.dahua.lib.structure;
/**
 * CLIENT_StartSniffer 接口输出参数
*/
public class NET_OUT_START_SNIFFER extends SdkStructure
{
    public int              dwSize;

    public NET_OUT_START_SNIFFER() {
        this.dwSize = this.size();
    }
}

