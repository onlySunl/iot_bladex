package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 窗口电源控制出参
 */
public class NET_OUT_WM_POWER_CTRL extends SdkStructure {
    public int dwSize;
    
    public NET_OUT_WM_POWER_CTRL() {
        this.dwSize = this.size();
    }
}
