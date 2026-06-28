package org.springblade.modules.iot.dahua.lib.structure;
/**
 * 恢复出厂设置出参
*/
public class NET_OUT_RESET_SYSTEM extends SdkStructure
{
    public int              dwSize;

    public NET_OUT_RESET_SYSTEM() {
        this.dwSize = this.size();
    }
}

