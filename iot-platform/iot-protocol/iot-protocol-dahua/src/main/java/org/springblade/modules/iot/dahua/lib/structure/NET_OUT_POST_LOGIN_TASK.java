package org.springblade.modules.iot.dahua.lib.structure;
/**
 * CLIENT_PostLoginTask 输出参数
*/
public class NET_OUT_POST_LOGIN_TASK extends SdkStructure
{
    /**
     * 赋值为结构体大小
    */
    public int              dwSize;

    public NET_OUT_POST_LOGIN_TASK() {
        this.dwSize = this.size();
    }
}

