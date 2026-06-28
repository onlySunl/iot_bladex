package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_ResetPicMap 接口输出参数
*/
public class NET_OUT_RESET_PIC_MAP extends SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_RESET_PIC_MAP() {
        this.dwSize = this.size();
    }
}

