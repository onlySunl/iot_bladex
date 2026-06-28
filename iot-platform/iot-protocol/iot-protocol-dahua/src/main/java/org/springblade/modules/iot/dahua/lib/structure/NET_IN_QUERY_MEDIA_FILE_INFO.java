package org.springblade.modules.iot.dahua.lib.structure;
/**
 * CLIENT_QueryMediaFile 接口输入参数
*/
public class NET_IN_QUERY_MEDIA_FILE_INFO extends SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 查询ID号
    */
    public int              nFindID;
    /**
     * 开始查询偏移
    */
    public int              nOffset;
    /**
     * 需要查询的个数
    */
    public int              nCount;

    public NET_IN_QUERY_MEDIA_FILE_INFO() {
        this.dwSize = this.size();
    }
}

