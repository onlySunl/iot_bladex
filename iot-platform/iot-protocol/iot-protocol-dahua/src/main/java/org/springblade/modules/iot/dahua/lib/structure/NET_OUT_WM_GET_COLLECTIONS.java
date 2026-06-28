package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.structure.NET_COLLECTION_INFO;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 获取电视墙预案列表出参
 */
public class NET_OUT_WM_GET_COLLECTIONS extends SdkStructure {
    public int dwSize;
    public NET_COLLECTION_INFO[] pstuCollections;
    public int nMaxCount;
    public int nRetCount;
    
    public NET_OUT_WM_GET_COLLECTIONS() {
        this.dwSize = this.size();
    }
}
