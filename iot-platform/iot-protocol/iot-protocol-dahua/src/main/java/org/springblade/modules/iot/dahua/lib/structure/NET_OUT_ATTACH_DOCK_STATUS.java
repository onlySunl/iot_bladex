package org.springblade.modules.iot.dahua.lib.structure;
/**
 * CLIENT_AttachDockStatus 接口输出参数
*/
public class NET_OUT_ATTACH_DOCK_STATUS extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_ATTACH_DOCK_STATUS() {
        this.dwSize = this.size();
    }
}

