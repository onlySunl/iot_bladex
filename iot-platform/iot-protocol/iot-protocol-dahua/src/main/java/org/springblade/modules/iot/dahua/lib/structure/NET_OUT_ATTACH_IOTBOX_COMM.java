package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_AttachIotboxComm 接口出参
*/
public class NET_OUT_ATTACH_IOTBOX_COMM extends SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_ATTACH_IOTBOX_COMM() {
        this.dwSize = this.size();
    }
}

