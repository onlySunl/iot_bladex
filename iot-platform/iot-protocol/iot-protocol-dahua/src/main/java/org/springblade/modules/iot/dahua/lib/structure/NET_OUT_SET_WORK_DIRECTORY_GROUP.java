package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_SetWorkDirectoryGoup 接口输出参数
*/
public class NET_OUT_SET_WORK_DIRECTORY_GROUP extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_SET_WORK_DIRECTORY_GROUP() {
        this.dwSize = this.size();
    }
}

