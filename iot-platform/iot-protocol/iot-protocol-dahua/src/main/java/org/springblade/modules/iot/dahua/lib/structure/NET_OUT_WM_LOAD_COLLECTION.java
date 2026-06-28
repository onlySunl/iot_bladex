package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 加载电视墙预案出参
 */
public class NET_OUT_WM_LOAD_COLLECTION extends SdkStructure {
    public int dwSize;
    
    public NET_OUT_WM_LOAD_COLLECTION() {
        this.dwSize = this.size();
    }
}
