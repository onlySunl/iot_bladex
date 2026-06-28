package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 保存电视墙预案入参
 */
public class NET_IN_WM_SAVE_COLLECTION extends SdkStructure {
    public int dwSize;
    public int nWallId;
    public int nCollectionId;
    public byte[] szName = new byte[64];
    
    public NET_IN_WM_SAVE_COLLECTION() {
        this.dwSize = this.size();
    }
}
