package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 预案信息
 */
public class NET_COLLECTION_INFO extends SdkStructure {
    public int dwSize;
    public int nCollectionId;
    public byte[] szName = new byte[64];
    public byte[] szCreateTime = new byte[32];
    
    public NET_COLLECTION_INFO() {
        this.dwSize = this.size();
    }
}
