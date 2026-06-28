package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_UpgraderInstall接口的 EM_UPGRADE_INSTALL_CANCEL命令入参
*/
public class NET_IN_INSTALL_CANCEL extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_IN_INSTALL_CANCEL() {
        this.dwSize = this.size();
    }
}

