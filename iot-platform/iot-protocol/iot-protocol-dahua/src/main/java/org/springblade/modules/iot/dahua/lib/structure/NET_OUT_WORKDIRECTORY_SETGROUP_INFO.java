package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * 设置工作目录组名 出参
*/
public class NET_OUT_WORKDIRECTORY_SETGROUP_INFO extends SdkStructure
{
    public int              dwSize;

    public NET_OUT_WORKDIRECTORY_SETGROUP_INFO() {
        this.dwSize = this.size();
    }
}

