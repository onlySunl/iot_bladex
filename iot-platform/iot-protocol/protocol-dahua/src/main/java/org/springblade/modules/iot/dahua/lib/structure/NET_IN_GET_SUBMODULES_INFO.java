package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 接口 CLIENT_GetSubModuleInfo 输入参数
*/
public class NET_IN_GET_SUBMODULES_INFO extends NetSDKLib.SdkStructure
{
    public int              dwSize;

    public NET_IN_GET_SUBMODULES_INFO() {
        this.dwSize = this.size();
    }
}

