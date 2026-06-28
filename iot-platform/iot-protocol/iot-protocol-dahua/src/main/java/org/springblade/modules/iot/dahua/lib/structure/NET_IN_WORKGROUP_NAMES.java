package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_QueryDevInfo , NET_QUERY_WORKGROUP_NAMES 命令输入参数
*/
public class NET_IN_WORKGROUP_NAMES extends SdkStructure
{
    public int              dwSize;

    public NET_IN_WORKGROUP_NAMES() {
        this.dwSize = this.size();
    }
}

