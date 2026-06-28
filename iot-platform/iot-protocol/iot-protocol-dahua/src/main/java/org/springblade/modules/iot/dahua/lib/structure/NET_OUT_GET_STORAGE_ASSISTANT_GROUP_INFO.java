package org.springblade.modules.iot.dahua.lib.structure;
/**
 * CLIENT_GetStorageAssistantGroupInfos 接口输出参数
*/
public class NET_OUT_GET_STORAGE_ASSISTANT_GROUP_INFO extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 盘组的个数
    */
    public int              nGroupNums;
    /**
     * 盘组信息,参见结构体定义 {@link NET_GROUP_INFO}
    */
    public NET_GROUP_INFO[] stuGroupInfo = new NET_GROUP_INFO[128];

    public NET_OUT_GET_STORAGE_ASSISTANT_GROUP_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuGroupInfo.length; i++){
            stuGroupInfo[i] = new NET_GROUP_INFO();
        }
    }
}

