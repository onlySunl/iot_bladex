package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_StartApp 接口输出参数
*/
public class NET_OUT_START_APP extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_START_APP() {
        this.dwSize = this.size();
    }
}

