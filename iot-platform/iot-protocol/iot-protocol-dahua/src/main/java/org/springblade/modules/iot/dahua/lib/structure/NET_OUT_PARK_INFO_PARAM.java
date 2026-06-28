package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 停车场信息查询出参
 */
public class NET_OUT_PARK_INFO_PARAM extends SdkStructure {
    public int dwSize;
    public int nTotal;
    public int nFree;
    
    public NET_OUT_PARK_INFO_PARAM() {
        this.dwSize = this.size();
    }
}
