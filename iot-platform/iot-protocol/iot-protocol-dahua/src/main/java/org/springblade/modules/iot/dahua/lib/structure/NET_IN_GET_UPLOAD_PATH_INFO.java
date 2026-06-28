package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_GetUploadPath接口入参
*/
public class NET_IN_GET_UPLOAD_PATH_INFO extends SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 文件路径
    */
    public byte[]           szInName = new byte[128];

    public NET_IN_GET_UPLOAD_PATH_INFO() {
        this.dwSize = this.size();
    }
}

