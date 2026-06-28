package org.springblade.modules.iot.dahua.lib.structure;
/**
 * CLIENT_DockEventsReply 接口输出参数
*/
public class NET_OUT_DOCK_EVENTS_REPLY extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_DOCK_EVENTS_REPLY() {
        this.dwSize = this.size();
    }
}

