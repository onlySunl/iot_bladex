package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 加载电视墙预案入参
 */
public class NET_IN_WM_LOAD_COLLECTION extends SdkStructure {
    public int dwSize;
    public int nWallId;
    public int nCollectionId;
    
    public NET_IN_WM_LOAD_COLLECTION() {
        this.dwSize = this.size();
    }
}
