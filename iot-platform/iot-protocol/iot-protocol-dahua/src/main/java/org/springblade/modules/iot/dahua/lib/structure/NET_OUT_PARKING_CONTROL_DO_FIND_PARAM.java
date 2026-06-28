package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 停车场控制查询出参
 */
public class NET_OUT_PARKING_CONTROL_DO_FIND_PARAM extends SdkStructure {
    public int dwSize;
    public NET_PARKING_CONTROL_RECORD[] pstuRecord = new NET_PARKING_CONTROL_RECORD[32];
    public int nRetCount;
    
    public NET_OUT_PARKING_CONTROL_DO_FIND_PARAM() {
        this.dwSize = this.size();
    }
}
