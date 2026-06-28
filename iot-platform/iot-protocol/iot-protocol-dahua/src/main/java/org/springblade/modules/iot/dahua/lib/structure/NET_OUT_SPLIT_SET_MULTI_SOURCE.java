package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 设置分割多源出参
 */
public class NET_OUT_SPLIT_SET_MULTI_SOURCE extends SdkStructure {
    public int dwSize;
    
    public NET_OUT_SPLIT_SET_MULTI_SOURCE() {
        this.dwSize = this.size();
    }
}
