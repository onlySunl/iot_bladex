package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_QueryMediaFileClose 接口输出参数
*/
public class NET_OUT_QUERY_MEDIA_FILE_CLOSE_INFO extends SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_QUERY_MEDIA_FILE_CLOSE_INFO() {
        this.dwSize = this.size();
    }
}

