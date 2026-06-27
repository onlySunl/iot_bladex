package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_AttachRecordManagerState 出参
*/
public class NET_OUT_RECORDMANAGER_ATTACH_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_RECORDMANAGER_ATTACH_INFO() {
        this.dwSize = this.size();
    }
}

