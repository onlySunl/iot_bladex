package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 获取电视墙预案列表入参
 */
public class NET_IN_WM_GET_COLLECTIONS extends SdkStructure {
    public int dwSize;
    public int nWallId;
    public int nMaxCount;
    
    public NET_IN_WM_GET_COLLECTIONS() {
        this.dwSize = this.size();
    }
}
