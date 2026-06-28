package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 设置分割多源入参
 */
public class NET_IN_SPLIT_SET_MULTI_SOURCE extends SdkStructure {
    public int dwSize;
    public int nChannel;
    public NET_SPLIT_SOURCE[] pstuSources;
    public int nSourceCount;
    
    public NET_IN_SPLIT_SET_MULTI_SOURCE() {
        this.dwSize = this.size();
    }
}
