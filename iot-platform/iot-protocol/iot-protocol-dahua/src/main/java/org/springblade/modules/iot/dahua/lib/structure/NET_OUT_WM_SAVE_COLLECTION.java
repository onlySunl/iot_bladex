package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 保存电视墙预案出参
 */
public class NET_OUT_WM_SAVE_COLLECTION extends SdkStructure {
    public int dwSize;
    
    public NET_OUT_WM_SAVE_COLLECTION() {
        this.dwSize = this.size();
    }
}
