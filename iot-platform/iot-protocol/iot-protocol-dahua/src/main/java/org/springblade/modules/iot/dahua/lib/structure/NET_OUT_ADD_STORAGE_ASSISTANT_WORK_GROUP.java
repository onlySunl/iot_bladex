package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_AddStorageAssistantWorkGroup 接口输出参数
*/
public class NET_OUT_ADD_STORAGE_ASSISTANT_WORK_GROUP extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_ADD_STORAGE_ASSISTANT_WORK_GROUP() {
        this.dwSize = this.size();
    }
}

