package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_ASGManagerClearStatistics 出参
*/
public class NET_OUT_ASGMANAGER_CLEAR_STATISTICS extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_ASGMANAGER_CLEAR_STATISTICS() {
        this.dwSize = this.size();
    }
}

