package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 停车场控制查询入参
 */
public class NET_IN_PARKING_CONTROL_DO_FIND_PARAM extends SdkStructure {
    public int dwSize;
    public int nStartIndex;
    public int nCount;
    
    public NET_IN_PARKING_CONTROL_DO_FIND_PARAM() {
        this.dwSize = this.size();
    }
}
