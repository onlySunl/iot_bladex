package org.springblade.modules.iot.dahua.lib.structure;
/**
 * 文件信息
*/
public class NET_QUERY_MEDIA_FILE extends SdkStructure
{
    /**
     * 文件信息,参见结构体定义 {@link NET_QUERY_MEDIA_FILE_INFO}
    */
    public NET_QUERY_MEDIA_FILE_INFO stuFileInfo = new NET_QUERY_MEDIA_FILE_INFO();
    /**
     * 文件Id号
    */
    public int              nId;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[124];

    public NET_QUERY_MEDIA_FILE() {
    }
}

