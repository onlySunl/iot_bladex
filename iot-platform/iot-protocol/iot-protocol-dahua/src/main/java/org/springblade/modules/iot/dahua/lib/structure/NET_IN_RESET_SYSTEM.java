package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 恢复出厂设置入参
*/
public class NET_IN_RESET_SYSTEM extends SdkStructure
{
    public int              dwSize;

    public NET_IN_RESET_SYSTEM() {
        this.dwSize = this.size();
    }
}

