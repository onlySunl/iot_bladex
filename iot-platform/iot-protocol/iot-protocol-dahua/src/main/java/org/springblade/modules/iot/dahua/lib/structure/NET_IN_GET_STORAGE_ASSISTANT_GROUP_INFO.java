package org.springblade.modules.iot.dahua.lib.structure;
/**
 * CLIENT_GetStorageAssistantGroupInfos 接口输入参数
*/
public class NET_IN_GET_STORAGE_ASSISTANT_GROUP_INFO extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_IN_GET_STORAGE_ASSISTANT_GROUP_INFO() {
        this.dwSize = this.size();
    }
}

