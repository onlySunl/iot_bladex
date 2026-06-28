package org.springblade.modules.iot.dahua.lib.structure;
/**
 * CLIENT_UpgraderInstall接口的 EM_UPGRADE_INSTALL_EXECUTE命令出参
*/
public class NET_OUT_INSTALL_EXECUTE extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_INSTALL_EXECUTE() {
        this.dwSize = this.size();
    }
}

