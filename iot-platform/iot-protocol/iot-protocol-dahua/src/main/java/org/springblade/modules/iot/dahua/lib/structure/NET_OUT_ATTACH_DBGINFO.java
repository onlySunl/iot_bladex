package org.springblade.modules.iot.dahua.lib.structure;
/**
 * 订阅日志回调出参
*/
public class NET_OUT_ATTACH_DBGINFO extends SdkStructure
{
    public int              dwSize;

    public NET_OUT_ATTACH_DBGINFO() {
        this.dwSize = this.size();
    }
}

