package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 窗口电源控制入参
 */
public class NET_IN_WM_POWER_CTRL extends SdkStructure {
    public int dwSize;
    public int nWallId;
    public int nWindowId;
    public boolean bPowerOn;
    
    public NET_IN_WM_POWER_CTRL() {
        this.dwSize = this.size();
    }
}
