package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CIENT_SetSplitWindowsInfo接口输出参数
*/
public class NET_OUT_SPLIT_SET_WINDOWS_INFO extends SdkStructure
{
    public int              dwSize;

    public NET_OUT_SPLIT_SET_WINDOWS_INFO() {
        this.dwSize = this.size();
    }
}

