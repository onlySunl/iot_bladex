package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 停车场控制开始查询出参
 */
public class NET_OUT_PARKING_CONTROL_START_FIND_PARAM extends SdkStructure {
    public int dwSize;
    public int nFound;
    
    public NET_OUT_PARKING_CONTROL_START_FIND_PARAM() {
        this.dwSize = this.size();
    }
}
