package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 停车场信息查询入参
 */
public class NET_IN_PARK_INFO_PARAM extends SdkStructure {
    public int dwSize;
    public int nChannel;
    
    public NET_IN_PARK_INFO_PARAM() {
        this.dwSize = this.size();
    }
}
